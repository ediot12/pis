package action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pay.PayDBBean;
import Pay.PayDataBean;
import Pay.PaymentDataBean;
import controller.CommandAction;



public class PointAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		
		System.out.println("pointaction");
		
		
		return "/pointcharge/Point.jsp";
	
	}

}
