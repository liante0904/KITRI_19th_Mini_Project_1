package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ServiceDao;
import Dto.OpenDto;
import Dto.RegL;
import Dto.RegisterDto;
import Dto.SubjectDto;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/main.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceDao service = new ServiceDao();
		HttpSession hs = request.getSession();
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String code="";
		if(request.getParameter("code")!=null){
			code = request.getParameter("code");
		}
		if(hs.getAttribute("num")==null){
			code = "sError";
		}
		if(code.equals("addSub")){
			String name = request.getParameter("sub_name");
			boolean chk = service.addSub(name);
			if(chk){
				System.out.println("개설성공");
				//과목개설 성공
			}
		}
		else if(code.equals("listSub")){
			List<SubjectDto> sdList = service.listSub();
			PrintWriter out = response.getWriter();
			out.println("[");
			for(int i=0; i<sdList.size(); i++){
				out.print("{");
				out.print("num:"+sdList.get(i).getNum());
				out.print(", name:'"+sdList.get(i).getName());
				out.print("', flag:"+sdList.get(i).getFlag());
				out.print("}");
				if(i!=(sdList.size()-1)){
					out.println(",");
				}
			}
			out.println("]");
		}
		else if(code.equals("subInfo")){
			int num = Integer.parseInt(request.getParameter("num"));
			SubjectDto sd = service.infoSub(num);
			request.setAttribute("num", sd.getNum());
			request.setAttribute("name", sd.getName());
			move(request, response, "jsp/addOpen.jsp");

		}
		else if(code.equals("addOpen")){
			//int num = Integer.parseInt(request.getParameter("open_num"));
			int subject_num = Integer.parseInt(request.getParameter("open_sub_num"));
			String room = request.getParameter("open_room");
			String sub_day = request.getParameterValues("open_sub_day")[0];
			String sub_time = request.getParameterValues("open_sub_time")[0];
			int prof_num = (int) hs.getAttribute("num"); //추가할때는 세션에 있는 num값 가져오기. 교수만 생성가능하므로
			boolean flag = service.addOpen(new OpenDto(0, subject_num, room, sub_day, sub_time, prof_num));
			if(flag){
				//과목개설 성공
				move(request, response, "jsp/listOpen.jsp");
			}
		}
		else if(code.equals("listOpen")){
			int prof_num = (int) hs.getAttribute("num"); //교수 자기 자신의 목록이므로 세션값 가져오기
			List<OpenDto> odList = service.listOpen(prof_num);
			PrintWriter out = response.getWriter();
			out.println("[");
			for(int i=0; i<odList.size(); i++){
				out.print("{");
				out.print("num:"+odList.get(i).getNum());
				out.print(", subject_num:"+odList.get(i).getSubject_num());
				out.print(", room:'"+odList.get(i).getRoom());
				out.print("', subject_name:'"+odList.get(i).getSubject_name());
				out.print("', sub_day:'"+odList.get(i).getSub_day());
				out.print("', sub_time:'"+odList.get(i).getSub_time());
				out.print("', prof_num:"+odList.get(i).getProf_num());
				out.print("}");
				if(i!=(odList.size()-1)){
					out.println(",");
				}
			}
			out.println("]");
		}
		else if(code.equals("infoSub")){
			SubjectDto sd = service.infoSub(Integer.parseInt(request.getParameter("num")));
			request.setAttribute("num", sd.getNum());
			request.setAttribute("name", sd.getName());
			String url = "jsp/addOpen.jsp";
			move(request, response, url);
		}
		else if(code.equals("listOpenAll")){
			List<OpenDto> odList = service.listOpenAll((int)hs.getAttribute("num"));
			PrintWriter out = response.getWriter();
			out.println("[");
			for(int i=0; i<odList.size(); i++){
				out.print("{");
				out.print("num:"+odList.get(i).getNum());
				out.print(", subject_num:"+odList.get(i).getSubject_num());
				out.print(", room:'"+odList.get(i).getRoom());
				out.print("', sub_day:'"+odList.get(i).getSub_day());
				out.print("', sub_time:'"+odList.get(i).getSub_time());
				out.print("', prof_num:"+odList.get(i).getProf_num());
				out.print(", prof_name:'"+odList.get(i).getProf_name());
				out.print("', subject_name:'"+odList.get(i).getSubject_name());
				out.print("'}");
				if(i!=(odList.size()-1)){
					out.println(",");
				}
			}
			out.println("]");
		}
		else if(code.equals("mySublist")){
			int num = (int)hs.getAttribute("num");
			List<RegL> regList = service.listReg(num);
			PrintWriter out = response.getWriter();
	
			out.println("[");
			for(int i=0; i<regList.size(); i++){
				out.print("{");
				out.print("reg_num:"+regList.get(i).getReg_num());
				out.print(", sub_num:"+regList.get(i).getSub_num());
				out.print(", prof_name:'"+regList.get(i).getProf_name());
				out.print("', room:'"+regList.get(i).getRoom());
				out.print("', sub_day:'"+regList.get(i).getSub_day());
				out.print("', sub_time:'"+regList.get(i).getSub_time());
				out.print("', sub_name:'"+regList.get(i).getSub_name());
				out.print("'}");
				if(i!=(regList.size()-1)){
					out.println(",");
				}
			}
			out.println("]");
		}
		else if(code.equals("delRegAll")){
			int num = Integer.parseInt(request.getParameter("num"));
			boolean flag = service.delRegAll(num);
			if(flag){
				//전체 삭제 성공
				boolean flag2 = service.delOpen(num);
				if(flag2){
					//Open삭제
					PrintWriter out = response.getWriter();
					out.println("{flag:true}");
				}
			}
			
		}
		else if(code.equals("regList")){
			int num = Integer.parseInt(request.getParameter("num"));
			List<String> ls = service.regList(num);
			PrintWriter out = response.getWriter();
			out.println("[");
			for(int i=0; i<ls.size(); i++){
				out.println("{");
				out.print("name:'"+ls.get(i));
				out.println("'}");
				if(i!=(ls.size()-1)){
					out.println(",");
				}
			}
			out.println("]");
		}
		else if(code.equals("addReg")){
			RegisterDto rd = new RegisterDto(0, Integer.parseInt(request.getParameter("sub_num")), Integer.parseInt(request.getParameter("num")), "", 1);
			service.addReg(rd);
		}
		else if(code.equals("delReg")){
			boolean flag =service.delReg(Integer.parseInt(request.getParameter("num")));
			if(flag){
				//Open삭제
				PrintWriter out = response.getWriter();
				out.println("{flag:true}");
			}
		}
		else if(code.equals("regCHK")){
			//day, time 가져오기
			int num = Integer.parseInt(request.getParameter("num"));
			System.out.println("cont num:"+num);
			OpenDto od = service.infoOpen(Integer.parseInt(request.getParameter("sub_num")));
			System.out.println(od.getSub_day()+":"+od.getSub_time());
			boolean flag = service.regCHK(num, od.getSub_day(), od.getSub_time());
			PrintWriter out = response.getWriter();
			System.out.println("flag:"+flag);
			if(flag){
			out.println("{flag:true}");}
			else out.println("{flag:false}");
		}
		else if(code.equals("modOpenInfo")){
			int num = Integer.parseInt(request.getParameter("opennum"));
			OpenDto od = service.infoOpen(num);
		}
		else if(code.equals("sError")){
			System.out.println("로그인 실패");
			request.setAttribute("error", "1");
			String url = "jsp/loginForm.jsp";
			move(request, response, url);
		}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private void move(HttpServletRequest request, HttpServletResponse response, String url){
		RequestDispatcher rd = request.getRequestDispatcher(url);
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
