package member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class MemberCheckAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
		
		MemberDBBean dbpro = MemberDBBean.getInstance();
		MemberDataBean mdb =  dbpro.getMember(id);
	    
	    request.setAttribute("id", id);  
	    request.setAttribute("mdb", mdb);
		
		
		return "/admin/member/memberCheck.jsp";
	}

}
