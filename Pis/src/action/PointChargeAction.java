package action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pay.PayDBBean;
import Pay.PayDataBean;
import controller.CommandAction;

// pointment���� ������ ���� db�� �����ϴ� ��Ȯ 
public class PointChargeAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		int point =  Integer.parseInt((String) request.getSession().getAttribute("point"));
		
		String id = (String) request.getSession().getAttribute("memId");
		
		PayDBBean paydb = PayDBBean.getInstance();
		int total_point = paydb.getTotalPoint(id);
		
//		***** total_point �����Դ��� Ȯ��
		System.out.println(total_point);
		
//		***** total_point �� ���� ������ point ��ŭ ����
		total_point =+ point;

//		***** ��õ�Ѹ�ŭ ���������� Ȯ��
		System.out.println(total_point);
		
		if(point!=0){			
			PayDataBean article = new PayDataBean();
			article.setId(id);
			article.setPoint(point);
			article.setUse_point(0);
			article.setInfo("����Ʈ����");
			article.setPdate(new Timestamp(System.currentTimeMillis()));
			article.setTotal_point(total_point);
			
			paydb.InsertPAY(article);
			System.out.println("db���");
			
			}
		  
		System.out.println("����Ʈ ���� ���� ��� ����Ϸ�");
		
		
		return "/pointcharge/PointList.jsp";
	}

}
