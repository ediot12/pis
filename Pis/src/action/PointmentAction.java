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
		
//		point.jsp���� selected value���� ������ point������ �� ����
		String point = request.getParameter("point");
		
//		����� point���� session�� ����
		request.getSession().setAttribute("point", point);
		
		System.out.println("pointmentAction!");
		System.out.println(point);
		
//		id�� �̸��� 
		
		/*
		if(point!=null){
				int p = Integer.parseInt(point);
				
				PayDataBean article = new PayDataBean();
				article.setPoint(p);
				article.setPdate(new Timestamp(System.currentTimeMillis()));
		//		article.setId(memId);
				article.setInfo("����Ʈ����");
				PayDBBean member = PayDBBean.getInstance();
				member.InsertPAY(article);
				System.out.println("db���");
				
				request.setAttribute("point", p);
				}
					
			
		
				System.out.println("pointaction");
				System.out.println(point);
		*/
		return "/pointcharge/pointment.jsp";
	}

}
