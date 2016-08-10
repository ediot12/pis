package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.CommandAction;

import Pay.PayDBBean;
import Pay.PayDataBean;
import Pay.PaymentDBBean;
import Pay.PointmentDataBean;
import controller.CommandAction;

public class PaymentAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		
		
		return "/pointcharge/payment.jsp";
	}

}
