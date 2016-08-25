package action;
 
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pay.PayDBBean;
import Pay.PayDataBean;
import controller.CommandAction;

// pointment에서 결제된 내역 db에 저장하는 역확 
public class PointChargeAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		int point =  Integer.parseInt((String) request.getSession().getAttribute("point"));
		     
		if(point == 5000){
			point += 1000;
		}else if(point == 10000){
			point += 2000;
		}else if(point == 50000){
			point += 6000;
		}else if(point == 100000){
			point += 20000;
		}
		
		String id = (String) request.getSession().getAttribute("memId");
		
		PayDBBean paydb = PayDBBean.getInstance();
		int total_point = paydb.getTotalPoint(id);
		
//		***** total_point 가져왔는지 확인
		System.out.println(total_point);
		
//		***** total_point 에 현재 충전한 point 만큼 더함
		total_point += point;

//		***** 중천한만큼 더해졌는지 확인
		System.out.println(total_point);
		 
		
		if(point!=0){			
			PayDataBean article = new PayDataBean();
			article.setId(id);
			article.setPoint(point);
			article.setUse_point(0);
			article.setInfo("포인트충전");
			article.setPdate(new Timestamp(System.currentTimeMillis()));
			article.setTotal_point(total_point);
			
			paydb.InsertPay(article);
			System.out.println("db등록");
			
			}
		  
		System.out.println("포인트 충전 내역 디비에 저장완료");
		
		
		return "/pointcharge/pointlist.do";
	}

}
