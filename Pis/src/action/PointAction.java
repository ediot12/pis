package action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Pay.PayDBBean;
import Pay.PayDataBean;
import Pay.PaymentDataBean;
import controller.CommandAction;



public class PointAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
//		Point.jsp에서 선택된 point값 저장
		request.setCharacterEncoding("utf-8");
		String point = request.getParameter("point");

		
		
		if(point!=null){
				int p = Integer.parseInt(point);
				
				PayDataBean article = new PayDataBean();
				article.setPoint(p);
				article.setPdate(new Timestamp(System.currentTimeMillis()));
		//		article.setId(memId);
				article.setInfo("포인트충전");
				PayDBBean member = PayDBBean.getInstance();
				member.InsertPAY(article);
				System.out.println("db등록");
				
				request.setAttribute("point", p);
				}
					
			
		
				System.out.println("pointaction");
				System.out.println(point);

		
		return "/pointcharge/Point.jsp";
	
	}

}
