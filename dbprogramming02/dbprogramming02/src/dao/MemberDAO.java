package dao;

import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;

public class MemberDAO {
	//싱글톤
	private static MemberDAO instance=null;
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		if(instance==null)instance=new MemberDAO();
		return instance;
	}
	JDBCUtil jdbc=JDBCUtil.getInstance();
	//신규입력(signUp)	
	public int signUp(String name,String telNum,int mile,String pw) {
		String sql="INSERT INTO TBL_MEMBER(MID, MNAME, TEL_NUM, MILEAGE, PASSWD ) ";
		sql=sql+" VALUES(fn_create_new_mid('2023'), ?, ?, ? ,? )";
		
		List<Object> param=new ArrayList<Object>();
		param.add(name);
		param.add(telNum);
		param.add(mile);
		param.add(pw);
		
		return jdbc.update(sql, param);
	}
	//정보변경(update)
	public int update(String str, String mid) {
		int result=0;
		String sql="UPDATE tbl_member SET ";
		sql=sql+str;
		sql=sql+" WHERE MID = '"+mid+"'";
		
		return jdbc.update(sql);
	}
	//회원별 정보 검색(searchOne)
	//회원 모두 조회(searchAll)
	//회원삭제(delete)
}
