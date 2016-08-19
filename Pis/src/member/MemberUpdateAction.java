package member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class MemberUpdateAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
			MemberDBBean dbpro = MemberDBBean.getInstance();
			
			MemberDataBean mdb = new MemberDataBean();
			mdb.setId(request.getParameter("id"));
			mdb.setChecked("½ÂÀÎ");
			
			dbpro.updateArticle(mdb);
		
		return "/admin/member/memberupdate.jsp";
	}

}
