package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pay.PayDBBean;
import controller.CommandAction;
import logon.LogonDataBean;

public class PaymentFormAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
//		"memId"이름으로 저장된 session을 id 변수에 저장
		String id = (String) request.getSession().getAttribute("memId");
		
//		"point"이름으로 저장된 session을 point 변수에 저장, payment.jsp에서 선택된 파라미터 값 pay_method 저장
		String point = (String)request.getSession().getAttribute("point");
		String pay_method = request.getParameter("pay_method");
		
//		"pay_method"이름으로 pay_method 값 저장
		request.setAttribute("pay_method", pay_method);
		
		
//		***** TEST *****
		System.out.println(point);
		System.out.println(pay_method);
		
		
		PayDBBean paydb = PayDBBean.getInstance();
		System.out.println(id);
		System.out.println("1단계");
//		매개변수 id값을 이용해 members테이블에 저장된 id의 정보를 가져옴
		LogonDataBean member = paydb.selectUserInfo(id);
		   
		System.out.println("2단계");
//		가져온 정보를 member에 저장
		request.setAttribute("member", member);
		
		System.out.println("여기는 3단계");
		
		return "/pointcharge/paymentForm.jsp";
	}

}
