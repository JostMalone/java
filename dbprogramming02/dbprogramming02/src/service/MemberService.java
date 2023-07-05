package service;

import java.util.Map;
import java.util.Scanner;

import dao.LoginDAO;
import dao.MemberDAO;

public class MemberService {
    //싱글톤
	private static MemberService instance=null;
	private MemberService() {}
	public static MemberService getInstance() {
		if(instance==null)instance=new MemberService();
		return instance;
	}
	
	//신규입력(signUp)	
	private static Scanner sc=new Scanner(System.in);
	MemberDAO dao=MemberDAO.getInstance();
	
	public void signUp() {
		int result=0;
		System.out.println("[[신규 회원 등록]]");
		
		System.out.print("회원명 : ");
		String name=sc.nextLine();
		
		System.out.print("전화번호 : ");
		String telNum=sc.nextLine();
		
		System.out.print("마일리지 : ");
		int mile=Integer.parseInt(sc.nextLine());
		
		System.out.print("암호 : ");
		String pw=sc.nextLine();
		
		System.out.println("위 자료를 저장하겠습니까? (y/n)");
		String flag=sc.nextLine();
		if(flag.equalsIgnoreCase("y")) {
			result=dao.signUp(name,telNum,mile,pw); 
		}
		
		if(result != 0) {
			System.out.println(name+"회원의 자료 입력이 정상 처리되었습니다");
		}else {
			System.out.println(name+"회원의 자료 입력이 취소되었습니다.");
		}
	}
	//정보변경(update)
	public void update() {
		int result=0;
		String mid="";
		String name="";
		String telNum="";
		int mile=0;
		String passwd="";
		String flag="";
		String str="";
		
		System.out.println("[[회원정보 수정]]");
		
		while(true) {
			System.out.print("회원번호 : ");
			mid=sc.nextLine();
			LoginService loginService=LoginService.getInstance();
			Map<String, Object>map=loginService.isDuplicate(mid);
			if(map==null) {
				System.out.println(mid+" 회원님 정보가 없어 수정할 수 없습니다.");
			}else {
				break;
			}			
		}
		
		System.out.print("이름을 변경하겠습니까?(y/n) : ");
		flag=sc.next();
		if(flag.equalsIgnoreCase("y")) {
			System.out.print("이 름 : ");
			name=sc.next();
			str=str+" MNAME = '"+name+"' ,";
		}
		
		System.out.print("전화번호를 변경하겠습니까?(y/n) : ");
		flag=sc.next();
		if(flag.equalsIgnoreCase("y")) {
			System.out.print("전화번호 : ");
			telNum=sc.next();
			str=str+" TEL_NUM = '"+telNum+"' ,";
		}
		
		System.out.print("마일리지를 변경하겠습니까?(y/n) : ");
		flag=sc.next();
		if(flag.equalsIgnoreCase("y")) {
			System.out.print("마일리지 : ");
			mile=sc.nextInt();
			str=str+" MILEAGE = "+mile+" ,";
		}
		
		System.out.print("비밀번호를 변경하겠습니까?(y/n) : ");
		flag=sc.next();
		if(flag.equalsIgnoreCase("y")) {
			System.out.print("비밀번호 : ");
			passwd=sc.next();
			str=str+" PASSWD = '"+passwd+"' ,";
		}
		int len=str.length();
		str=str.substring(0,len-2);
		
		result=dao.update(str, mid);
		 
		if(result != 0) {
			System.out.println("회원정보 수정이 정상 처리되었습니다");
		}else {
			System.out.println("회원정보 수정이 취소되었습니다.");
		}
		 
	}
	//회원별 정보 검색(searchOne)
	//회원 모두 조회(searchAll)
	//회원삭제(delete)
}
