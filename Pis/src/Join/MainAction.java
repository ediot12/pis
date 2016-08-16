package Join;

import javax.servlet.ServletRequest;
import javax.servlet.http.*;
import controller.CommandAction;

public class MainAction implements CommandAction {
	public String requestPro(HttpServletRequest request,
	 HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		try{
			session.getAttribute("memId");
		
		}catch(NullPointerException e){
			
		}
		
		
		
		/*BoardDataBean article = new BoardDataBean();
		BoardDBBean dbPro = BoardDBBean.getInstance();*/
		
		
		return "/Join/main.jsp";
	}

}
