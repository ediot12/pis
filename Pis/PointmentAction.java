package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import Pay.PayDBBean;
import Pay.PayDataBean;
import Pay.PaymentDBBean;
import controller.CommandAction;

public class PointmentAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		
		
		return "/pointcharge/pointment.jsp";
	}

}
