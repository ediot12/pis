package action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pay.PayDBBean;
import Pay.PayDataBean;
import Pay.PaymentDataBean;
import Reservation.ReservBean;
import controller.CommandAction;

public class reserv_pointmentAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		System.out.println("reserv_pointmentAction!");

		

//		예약번호 값으로 id,pay 구하기(수정해야함)		
		PayDBBean p = PayDBBean.getInstance();		
		PaymentDataBean article = new PaymentDataBean();
		p.getId(article);
		String id = article.getId();
		int use_point = article.getPay();		
		
		System.out.println(id + use_point);
		
		
//		현재 포인트		
		int point = p.selectPoint(id);
		int poin = point - use_point;
		
		
		PayDataBean result = new PayDataBean();
		result.setId(id);
		result.setPoint(poin);
		result.setUse_point(use_point);
		result.setInfo("포인트 사용");
		result.setPdate(new Timestamp(System.currentTimeMillis()));
		
		PayDBBean paydb = PayDBBean.getInstance();
		paydb.InsertPoint(result);
		
		
		
		
		
		return "/pointcharge/point.jsp";
	}

}
