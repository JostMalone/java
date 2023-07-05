package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class JDBCUtil {
	//½Ì±ÛÅæ
	private static JDBCUtil instance=null; //°´Ã¼º¯¼ö
	private JDBCUtil() {} //»ý¼ºÀÚ
	public static JDBCUtil getInstance() {
		if(instance==null) instance=new JDBCUtil();
		return instance;
	}
	//½Ì±ÛÅæ ³¡
	
	private String url="jdbc:oracle:thin:@localhost:1521:xe";
	private String user="SEM";
	private String pw="java";
	
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private Statement stmt= null;
	private ResultSet rs=null;
	
	public List<Map<String, Object>> selectAll(String sql){
		//"select * from tbl_member"
		//"select * from tbl_member where mid >= '23010'"
		List<Map<String, Object>> list=null;
		try {
			conn=DriverManager.getConnection(url, user, pw);
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			ResultSetMetaData rsmd=rs.getMetaData();
			int columncount=rsmd.getColumnCount();
			while(rs.next()) {
				if(list==null) list= new ArrayList<>();
				Map<String, Object> row=new HashMap<>();
				for(int i=0; i<columncount; i++) {
					String key=rsmd.getColumnLabel(i+1);
					Object value=rs.getObject(i+1);
					row.put(key, value);
				}
				list.add(row);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(pstmt!=null) try {pstmt.close();}catch(Exception e) {}
			if(conn!=null) try {conn.close();}catch(Exception e) {}
			
		}
		return list;
	}
	
	public Map<String, Object> selectOne(String sql, List<Object> param){
		Map<String, Object> row=null;
		try {
			conn=DriverManager.getConnection(url, user, pw);
			pstmt=conn.prepareStatement(sql);
			
			for(int i=0; i<param.size(); i++) {
				pstmt.setObject(i+1, param.get(i));
			}
			
			rs=pstmt.executeQuery();
			
			ResultSetMetaData rsmd=rs.getMetaData();
			int columncount=rsmd.getColumnCount();
			while(rs.next()) {
				row=new HashMap<>();
				for(int i=0; i<columncount; i++) {
					String key=rsmd.getColumnLabel(i+1);
					Object value=rs.getObject(i+1);
					row.put(key, value);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(pstmt!=null) try {pstmt.close();}catch(Exception e) {}
			if(conn!=null) try {conn.close();}catch(Exception e) {}
		
		}
		return row;
	}
	
	public int update(String sql) {
		//sql="delete from tbl_member"
		//sql="delete from tbl_member where mid='23011'"
		//sql="update tbl_member set mileage=mileage*1.1"
		//sql="update tbl_member set tel_num = '010-1234-5678'
		//            where mid='23011'"
		//sql="insert into tbl_member(mid,mname) 
		//      values('23030','ÀÌÁ¤¹Î')"
		int result=0;
		try {
			conn=DriverManager.getConnection(url, user, pw);
			pstmt=conn.prepareStatement(sql);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(pstmt!=null) try {pstmt.close();}catch(Exception e) {}
			if(conn!=null) try {conn.close();}catch(Exception e) {}
		
		}
		return result;	
	}
	
	public int update(String sql, List<Object>param) {
		//sql="delete from tbl_member where mid= ?"
		//sql="update tbl_member set tel_num = '010-1234-5678'
		//            where mid= ?"
		//sql="insert into tbl_member(mid,mname) 
		//      values(?,?)"
		int result=0;
		try {
			conn=DriverManager.getConnection(url, user, pw);
			pstmt=conn.prepareStatement(sql);
			for(int i=0; i<param.size(); i++) {
				pstmt.setObject(i+1, param.get(i));
			}
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(pstmt!=null) try {pstmt.close();}catch(Exception e) {}
			if(conn!=null) try {conn.close();}catch(Exception e) {}
		
		}
		return result;	
	}
}













