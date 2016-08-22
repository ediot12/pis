package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import Pay.PayDBBean;
import Pay.PayDataBean;
import Pay.PaymentDBBean;
import controller.CommandAction;
import email.SendEmail;

public class PointmentAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String point = request.getParameter("point");
		request.getSession().setAttribute("point", point);
		
		String id = (String) request.getSession().getAttribute("memId");
		request.setAttribute("id", id);
		   
		PayDBBean paydb = PayDBBean.getInstance();
		String name = paydb.selectName(id);
		request.setAttribute("name", name);
		
		System.out.println("pointmentAction!");
		
//		***** test *****
		System.out.println(point);
		System.out.println(id);
		System.out.println(name);
		
		System.out.println("pointmentAction에서 결과 확인 후 pointment.jsp 이동");
		
		return "/pointcharge/pointment.jsp";
	}

}
