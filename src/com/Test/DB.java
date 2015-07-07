package com.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DB {
	public static Connection getCon(){
		Connection con=null;
		String url="jdbc:oracle:thin:@pwdbos0407qp01.pw.ge.com:1521:etpcamq2";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url, "Cam_app", "Cam12appq1");
			
		}
		catch (Exception e){
			
		}
		finally{
			/*try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}*/
		}
		return con;
	}
	
	public static Statement getStmt(Connection con){
		Statement stmt=null;
	//	ResultSet rs=null;
		try{
			stmt=con.createStatement();
			//rs=stmt.executeQuery(sql);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			/*try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}*/
		}
		return stmt;
	}
	
	public static void testUserRole(int id){
		String sql = "select  role_id ,business_grp_id  from cost_user_role_default where user_id=?";
	    PreparedStatement stmt=null;
		Connection conn = null;
		String existFlag="false";
		String defaultRole="";
		String defaultBus="";
		/*String status_ind ="";*/
		HashMap<String,String> params=new HashMap<String,String>();
		try {
			conn = getCon();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "1");
			ResultSet rs=stmt.executeQuery();
			
			if(rs.next()){
				existFlag="true";
				defaultRole=(String)rs.getString("role_id");
				defaultBus=(String)rs.getString("business_grp_id");
				params.put("defaultRole",defaultRole);
				params.put("defaultBus",defaultBus);
			}
			params.put("existFlag",existFlag);
			
			System.out.println(params.get("defaultRole"));
			System.out.println(params.get("defaultBus"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null && ! conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
	}

	
	public static void main(String[] args) {
		testUserRole(1);
		/*ResultSet rs=null;
		String sql="select * from cost_model_ver";
		Connection con=DB.getCon();
		Statement stmt=null;
		try{
			int i=0;
			 stmt=DB.getStmt(con);
			rs=stmt.executeQuery(sql);
			while(rs.next()&&i<20){
				System.out.println(rs.getString("mdl_ver_id"));
				i++;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}*/
	}

}
