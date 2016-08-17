package action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.sun.javafx.scene.paint.GradientUtils.Point;

import Pay.PayDBBean;
import Pay.PayDataBean;
import controller.CommandAction;

public class PointmentAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
//		point.jsp에서 selected value값을 가져와 point변수에 값 저장
		String point = request.getParameter("point");
		
//		저장된 point값을 session에 저장
		request.getSession().setAttribute("point", point);
		
		System.out.println("pointmentAction!");
		System.out.println(point);
		
//		id랑 이름값 
		
		/*
		if(point!=null){
				int p = Integer.parseInt(point);
				
				PayDataBean article = new PayDataBean();
				article.setPoint(p);
				article.setPdate(new Timestamp(System.currentTimeMillis()));
		//		article.setId(memId);
				article.setInfo("포인트충전");
				PayDBBean member = PayDBBean.getInstance();
				member.InsertPAY(article);
				System.out.println("db등록");
				
				request.setAttribute("point", p);
				}
					
			
		
				System.out.println("pointaction");
				System.out.println(point);
		*/
		return "/pointcharge/pointment.jsp";
	}

}
