package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import Connect.DBConnect;
import Dto.MemberDto;
import Interface.Member;

public class MemberDao implements Member {
	private DBConnect db;
	private Connection conn=null;
	private Statement stmt=null;
	private ResultSet rs=null;
	private PreparedStatement pstmt = null;
	private HttpServletRequest request = null;
	private String sql="";
	public MemberDao(){
		db = DBConnect.getInstance();
	}

	@Override
	public boolean join(MemberDto m) {
		conn = db.getConnection();
		sql = "INSERT INTO MEMBER(num, name, tel, email, dept, type) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m.getNum());
			pstmt.setString(2, m.getName());
			pstmt.setString(3, m.getTel());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getDept());
			pstmt.setInt(6, m.getType());
			int chk = pstmt.executeUpdate();
			if(chk==1) return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally{
			closeDB();
		}
		return false;
	}

	@Override
	public boolean login(int num, String name, int type) {
		// TODO 로그인 (학번, 이름, 신분으로 판별)
		conn = db.getConnection();
		sql = "SELECT COUNT(*) FROM MEMBER WHERE num=? AND name=? AND TYPE=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, name);
			pstmt.setInt(3, type);
			rs = pstmt.executeQuery();
			rs.next();
			int chk = rs.getInt(1);
			if (chk == 0){
				return false;
			} else
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally{
			closeDB();
		}
	}

	@Override
	public boolean modify(MemberDto m) {
		// TODO Auto-generated method stub
		conn = db.getConnection();
		sql = "UPDATE MEMBER SET tel = ?,  email = ?, dept = ? WHERE NUM = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getTel());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getDept());
			pstmt.setInt(4, m.getNum());
			int chk = pstmt.executeUpdate();
			if(chk==1) return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally{
			closeDB();
		}
		return true;
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

	public MemberDto select(int num) {
		sql = "select num, name, tel, email, dept, type FROM MEMBER WHERE NUM="+num;
		conn = db.getConnection();
		MemberDto md = new MemberDto();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				md = new MemberDto(rs.getInt("num"), rs.getString("name"), rs.getString("tel"), rs.getString("email"), rs.getString("dept"), rs.getInt("type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeDB();
		}

		return md;
	}


}

