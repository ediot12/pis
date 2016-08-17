package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pay.PayDBBean;
import Pay.PayDataBean;
import controller.CommandAction;

public class PaymentFormAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

	
		request.setAttribute("pay_method", request.getParameter("pay_method"));
		System.out.println(request.getAttribute("pay_method"));
		
		int point = Integer.parseInt( (String) request.getSession().getAttribute("point"));
		request.setAttribute("point", point);
		
		
		
		return "/pointcharge/paymentForm.jsp";
	}

}
