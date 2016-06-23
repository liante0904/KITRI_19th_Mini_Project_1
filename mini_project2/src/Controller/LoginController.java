package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ServiceDao;
import Dto.MemberDto;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceDao service = new ServiceDao();
		HttpSession hs = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String code="";
		if(request.getParameter("code")!=null){
			code = request.getParameter("code");
		}
		if(code.equals("login")){
			int num = Integer.parseInt(request.getParameter("num"));
			String name = request.getParameter("name");
			int type = Integer.parseInt(request.getParameter("type"));
			boolean flag = service.login(num, name, type);
			if(flag){
				System.out.println("�α��� ����");
				hs.setAttribute("num", num);
				if(type==1){
					String url = "jsp/listSub.jsp";
					move(request, response, url);
					//������ �������� �̵�
				} else if(type==2){
					//���� �������� �̵�
					String url = "jsp/listOpen.jsp";
					move(request, response, url);
				} else if(type==3){
					//�л� �������� �̵�
					String url = "jsp/listStudent_Open.jsp";
					move(request, response, url);
				}
			} else{
				System.out.println("�α��� ����");
				request.setAttribute("error", "0");
				String url = "jsp/loginForm.jsp";
				move(request, response, url);
			}
		}
		else if(code.equals("join")){
			MemberDto m = new MemberDto();
			m.setNum(Integer.parseInt(request.getParameter("num")));
			m.setName(request.getParameter("name"));
			m.setTel(request.getParameter("tel"));
			m.setEmail(request.getParameter("email"));
			m.setDept(request.getParameter("dept"));
			m.setType(Integer.parseInt(request.getParameterValues("ty")[0]));
			boolean flag = service.join(m);
			if(flag){
				//ȸ������ ����
				String url = "jsp/loginForm.jsp";
				response.sendRedirect(url);
			}
			
		}
		else if(code.equals("logout")){
			hs.invalidate();
			//���� �������� �̵�.
			//������� ���� ��� �˻�. ���� ������ logout�ҷ�����
			move(request, response, "jsp/loginForm.jsp");
		}
		else if(code.equals("checkId")){
			int num = 0;
			if(request.getParameter("num")!=null){
				num = Integer.parseInt(request.getParameter("num"));
			}
			PrintWriter out = response.getWriter();
			System.out.println("checkId, num= "+num);
			MemberDto mem = service.getMember(num);
			if(mem.getName()==null){
				out.print("��밡��");
			}
			else{
				out.print("���Ұ���");
			}
		}
		else if(code.equals("join_ok")){
			String url = "jsp/join.jsp";
			move(request, response, url);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
