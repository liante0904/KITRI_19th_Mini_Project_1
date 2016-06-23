package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connect.DBConnect;
import Dto.SubjectDto;
import Interface.Staff;

public class StaffDao implements Staff {
	private DBConnect db;
	private Connection conn=null;
	private Statement stmt=null;
	private ResultSet rs=null;
	private PreparedStatement pstmt = null;
	private String sql="";
	public StaffDao(){
		db = DBConnect.getInstance();
	}
	
	
	@Override
	public boolean addSub(String name) {
		sql = "INSERT INTO subject(num, name, flag) VALUES (seq_open.nextval, ?, 1)";
		conn = db.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
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
	public List<SubjectDto> listSub() {
		sql = "SELECT num, name, flag FROM subject ORDER BY num";
		conn = db.getConnection();
		List<SubjectDto> sdList = new ArrayList<SubjectDto>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				sdList.add(new SubjectDto(rs.getInt(1),rs.getString(2),rs.getInt(3)));
			}
			return sdList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeDB();
		}

		return null;
	}
	
	public SubjectDto subInfo(int num) {
		sql = "SELECT num, name, flag FROM subject WHERE num="+num;
		conn = db.getConnection();
		SubjectDto sd = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				sd = new SubjectDto(rs.getInt(1),rs.getString(2),rs.getInt(3));
			}
			return sd;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeDB();
		}

		return null;
	}

	
	public SubjectDto infoSub(int num) {
		sql = "SELECT num, name, flag FROM subject WHERE num="+num;
		conn = db.getConnection();
		SubjectDto sd = new SubjectDto();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				sd = new SubjectDto(rs.getInt(1),rs.getString(2),rs.getInt(3));
			}
			return sd;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeDB();
		}

		return null;
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
