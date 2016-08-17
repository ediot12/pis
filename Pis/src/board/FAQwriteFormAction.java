package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class FAQwriteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		
		int num = 0;
		try{
			if(request.getParameter("num")!=null){
				num=Integer.parseInt(request.getParameter("num"));
			}
		}catch(Exception e){e.printStackTrace(); }
		
		//�ش� �信�� ����� �Ӽ�
		request.setAttribute("num", new Integer(num));
		
        
		return "/admin/board/FAQwriteForm.jsp";
	}

}
