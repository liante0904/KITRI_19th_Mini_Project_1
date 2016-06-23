package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connect.DBConnect;
import Dto.MemberDto;
import Dto.OpenDto;
import Dto.RegL;
import Dto.RegisterDto;
import Interface.Student;

public class StudentDao implements Student {
   private DBConnect db;
   private Connection conn=null;
   private Statement stmt=null;
   private ResultSet rs=null;
   private PreparedStatement pstmt = null;
   private String sql="";
   public StudentDao(){
      db = DBConnect.getInstance();
   }
   
   
   @Override
   public int addReg(RegisterDto rd) {
      // TODO Auto-generated method stub
      conn = db.getConnection();
      sql = "INSERT INTO Register(num, subject_num, stud_num, sub_year, quarter) VALUES (seq_register.nextval, ?, ?, ?, ?)";
      PreparedStatement pstmt;
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, rd.getSubject_num());
         pstmt.setInt(2, rd.getStud_num());
         pstmt.setString(3, "2015");
         pstmt.setInt(4, rd.getQuarter());
         int chk = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         closeDB();
      }
      
      return 0;
   }

   @Override
   public boolean delReg(int num) {
      // TODO Auto-generated method stub
      conn = db.getConnection();
      String sql = "DELETE FROM Register WHERE NUM = "+ num;
      try {
         stmt = conn.createStatement();
         stmt.executeUpdate(sql);
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         return false;
      } finally {
         closeDB();
      }
      return true;
   }
   
   public boolean delRegAll(int num){
	      // TODO Auto-generated method stub
	      conn = db.getConnection();
	      String sql = "DELETE FROM Register WHERE subject_num = "+ num;
	      try {
	         stmt = conn.createStatement();
	         stmt.executeUpdate(sql);
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	         return false;
	      } finally {
	         closeDB();
	      }
	      return true;
   }

   @Override
   public List<RegL> listReg(int num) {
      // TODO Auto-generated method stub
      conn = db.getConnection();
      ArrayList<RegL> list = new ArrayList<RegL>();
      String sql = "SELECT r.NUM, r.SUBJECT_NUM, m.NAME, o.ROOM, o.SUB_DAY, o.SUB_TIME, s.NAME FROM REGISTER r JOIN OPEN o ON o.NUM = r.SUBJECT_NUM JOIN MEMBER m ON m.NUM = o.PROF_NUM JOIN SUBJECT s ON s.NUM=o.SUBJECT_NUM WHERE stud_num=" + num;
   
      
      try {
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);
         while(rs.next()){
            list.add(new RegL(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
         }
         closeDB();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return list;

   }
   
	public List<OpenDto> listOpen(int num) {
		sql = "SELECT o.num, subject_num, room, sub_day, sub_time, prof_num, s.NAME, m.NAME"
				+ " FROM open o"
				+ " JOIN SUBJECT s"
				+ " ON s.NUM = o.SUBJECT_NUM JOIN MEMBER m ON m.NUM = o.PROF_NUM"
				+ " WHERE o.NUM <> ALL(SELECT r.SUBJECT_NUM FROM REGISTER r WHERE r.STUD_NUM="+num+") ORDER BY s.NAME";
		conn = db.getConnection();
		List<OpenDto> odList = new ArrayList<OpenDto>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				//odList.add(new OpenDto(num, subject_num, room, sub_day, sub_time, prof_num))
				odList.add(new OpenDto(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8)));
			}
			return odList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeDB();
		}

		return null;
	}
	
	public List<String> regList(int num) {
		sql = "select m.NAME from REGISTER r join MEMBER m on r.STUD_NUM = m.NUM WHERE r.SUBJECT_NUM="+num;
		conn = db.getConnection();
		List<String> ls = new ArrayList<String>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				//odList.add(new OpenDto(num, subject_num, room, sub_day, sub_time, prof_num))
				ls.add(rs.getString(1));
			}
			return ls;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeDB();
		}

		return null;
	}

   public void closeDB(){
      //Con, Stmt, Rs
      if(pstmt!=null){
         try {
            pstmt.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      if(rs!=null){
         try {
            rs.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }

      if(stmt!=null){
         try {
            stmt.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      if(conn!=null){
         try {
            conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }


public boolean regCHK(int num, String day, String time) {
	//Register 테이블에서 시간표 중복 체크 중복이면 false 반환, 
	sql = "select count(*) from REGISTER r join OPEN o on o.NUM = r.SUBJECT_NUM where o.SUB_DAY = ? and o.SUB_TIME = ? AND r.STUD_NUM=?";
	System.out.println(sql);
	conn = db.getConnection();
	boolean flag = false;
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, day);
		pstmt.setString(2, time);
		pstmt.setInt(3, num);
		rs = pstmt.executeQuery();
		int chk = 0;
		System.out.println("AA");
		while(rs.next()){
			chk = rs.getInt(1);
		}
		System.out.println("chk:"+chk);
		if(chk==0){
			flag = true;
		} else{
			flag = false;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return flag;
}




}