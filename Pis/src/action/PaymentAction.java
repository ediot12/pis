package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import controller.CommandAction;

public class PaymentAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
//		point.jsp���� select �� �� session�� ����
		request.setCharacterEncoding("utf-8");
		
		String point = request.getParameter("point");
		request.setAttribute("point", point);
		System.out.println("paymentaction");
		
		return "/pointcharge/payment.jsp";
	}

}
