package Join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.CommandAction;
import logon.LogonDBBean;
import logon.LogonDataBean;

public class LoginProAction implements CommandAction {
	public String requestPro(HttpServletRequest request, 
			HttpServletResponse response) throws Throwable{
		
		request.setCharacterEncoding("utf-8");
		
		LogonDBBean dbPro = LogonDBBean.getInstance();
		// 객체를 리턴
		HttpSession session = request.getSession();
		// HttpSession session에 = request.getSession() 대입 세션 받아올때 이거씀
	
		String id = request.getParameter("id");
		// jsp에서 썻던 id값을 가져와서 id참조변수에 저장
		String passwd = request.getParameter("passwd");
	
		int check = dbPro.userCheck(id, passwd);
		// dbpro.유저체크에 id, passwd를 가지고 가서 SQL문 실행한 후 리턴한 x값을 check에 저장
		if(check==1){
			session.setAttribute("memId",id);
			//response.sendRedirect("main.jsp");
		 } //체크가 1일때 세션에 "memId 라는 값으로 id를 저장
		LogonDataBean member = new LogonDataBean();
		member.setGrade(request.getParameter("grade"));
	
		request.setAttribute("check", new Integer(check));
	
		
		return "/Join/loginPro.jsp";
		
	}

}
