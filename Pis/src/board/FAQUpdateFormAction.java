package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class FAQUpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		FAQDBBean dbPro = FAQDBBean.getInstance();
        FAQDataBean fdb =  dbPro.updateGetArticle(num);

        //해당 뷰에서 사용할 속성
        request.setAttribute("pageNum", new Integer(pageNum));
        request.setAttribute("fdb", fdb);
        
        
		return "/admin/board/FAQUpdateForm.jsp";
	}

}
