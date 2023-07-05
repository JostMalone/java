package service;

import java.util.Map;
import java.util.Scanner;

import dao.LoginDAO;

public class LoginService {
	private static LoginService instance=null;
	private LoginService() {}
	public static LoginService getInstance() {
		if (instance==null)instance=new LoginService();
		return instance;
	}
	
	Scanner sc=new Scanner(System.in);
	LoginDAO dao=LoginDAO.getInstance();
	Map<String, Object> result=null;
	
	public void login() {
		System.out.println("[[로그인]]");
		while(true) {
			System.out.print("아이디 : ");
			String id=sc.nextLine();
			
			System.out.print("password : ");
			String pw=sc.nextLine();
			
			result=dao.login(id, pw);
			if(result!=null) {
				System.out.println(result.get("MNAME")+
						"님 환영합니다.");
				break;
			}
		}
	}
	
	public Map<String, Object> isDuplicate(String mid){
		return dao.select(mid);
	}
}















