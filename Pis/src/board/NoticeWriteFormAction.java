package board;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class NoticeWriteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		int num = 0;
		try{
			if(request.getParameter("num")!=null){
				num=Integer.parseInt(request.getParameter("num"));
			}
		}catch(Exception e){e.printStackTrace(); }
		
		//해당 뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		
		return "/admin/board/noticewriteForm.jsp";
	}

}
