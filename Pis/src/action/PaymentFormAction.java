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
//		"memId"�̸����� ����� session�� id ������ ����
		String id = (String) request.getSession().getAttribute("memId");
		
//		"point"�̸����� ����� session�� point ������ ����, payment.jsp���� ���õ� �Ķ���� �� pay_method ����
		String point = (String)request.getSession().getAttribute("point");
		String pay_method = request.getParameter("pay_method");
		
//		"pay_method"�̸����� pay_method �� ����
		request.setAttribute("pay_method", pay_method);
		
		
//		***** TEST *****
		System.out.println(point);
		System.out.println(pay_method);
		
		
		PayDBBean paydb = PayDBBean.getInstance();
		System.out.println(id);
		System.out.println("1�ܰ�");
//		�Ű����� id���� �̿��� members���̺� ����� id�� ������ ������
		LogonDataBean member = paydb.selectUserInfo(id);
		   
		System.out.println("2�ܰ�");
//		������ ������ member�� ����
		request.setAttribute("member", member);
		
		System.out.println("����� 3�ܰ�");
		
		return "/pointcharge/paymentForm.jsp";
	}

}
