package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.CommandAction;

import Pay.PayDBBean;
import Pay.PayDataBean;
import controller.CommandAction;

public class PaymentAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		
		return "/pointcharge/payment.jsp";
	}

}
