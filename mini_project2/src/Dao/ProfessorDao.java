package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connect.DBConnect;
import Dto.OpenDto;
import Interface.Professor;

public class ProfessorDao implements Professor {
	private DBConnect db;
	private Connection conn=null;
	private Statement stmt=null;
	private ResultSet rs=null;
	private PreparedStatement pstmt = null;
	private String sql="";
	public ProfessorDao(){
		db = DBConnect.getInstance();
	}
	
	
	@Override
	public boolean addOpen(OpenDto od) {
		sql = "INSERT INTO open(num, subject_num, room, sub_day, sub_time, prof_num) VALUES (seq_open.nextval, ?, ?, ?, ?, ?)";
		conn = db.getConnection();
		System.out.println("!11111들어옴");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, od.getSubject_num());
			pstmt.setString(2, od.getRoom());
			pstmt.setString(3, od.getSub_day());
			pstmt.setString(4, od.getSub_time());
			pstmt.setInt(5, od.getProf_num());
			int chk = pstmt.executeUpdate();
			if(chk==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			closeDB();
		}
		
		return false;
	}

	@Override
	public List<OpenDto> listOpen(int num) {
		sql = "SELECT o.num, o.subject_num, o.room, o.sub_day, o.sub_time, s.NAME FROM open o JOIN SUBJECT s ON s.NUM = o.SUBJECT_NUM WHERE prof_num="+num+"ORDER BY o.num";
		conn = db.getConnection();
		List<OpenDto> odList = new ArrayList<OpenDto>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				//odList.add(new OpenDto(num, subject_num, room, sub_day, sub_time, prof_num))
				odList.add(new OpenDto(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), num, rs.getString(6)));
			}
			return odList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeDB();
		}

		return null;
	}
	
	public OpenDto infoOpen(int num) {
		//여기까지작업완료 서비스단에 추가하고 콘트롤러에서 정보받아서 타입이랑 데이 작업 중복체크
		sql = "SELECT sub_day, sub_time FROM open WHERE num="+num;
		conn = db.getConnection();
		OpenDto od = new OpenDto();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				//odList.add(new OpenDto(num, subject_num, room, sub_day, sub_time, prof_num))
				od.setSub_day(rs.getString(1));
				od.setSub_time(rs.getString(2));
				System.out.println("od:"+od.getSub_day());
			}
			return od;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeDB();
		}

		return null;
	}
	
	@Override
	public boolean modOpen(OpenDto od) {
		sql = "UPDATE open SET room=?, sub_day=?, sub_time=? WHERE num=?";
		conn = db.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, od.getRoom());
			pstmt.setString(2, od.getSub_day());
			pstmt.setString(3, od.getSub_time());
			pstmt.setInt(4, od.getNum());
			int chk = pstmt.executeUpdate();
			if(chk==1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeDB();
		}
		return false;
	}

	@Override
	public boolean delOpen(int num) {
		sql = "DELETE FROM open WHERE num="+num;
		conn = db.getConnection();
		try {
			stmt = conn.createStatement();
			int chk = stmt.executeUpdate(sql);
			if(chk==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeDB();
		}
		return false;
	}
	
	private void closeDB(){
		System.out.println("DBClose...");
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
		System.out.println("DBClose...OK");
	}

}
