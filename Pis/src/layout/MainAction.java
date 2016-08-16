package layout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chart.ChartDBBean;
import chart.ChartDataBean;
import controller.CommandAction;

import java.sql.*;
public class MainAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		ChartDataBean article = new ChartDataBean();
		article.setC_date(new Timestamp(System.currentTimeMillis()));
		article.setC_ip(request.getRemoteAddr());

		ChartDBBean cb = ChartDBBean.getInstance(); 
		int check=cb.TodayIpCheck(request.getRemoteAddr());

		if(check==-1||check==0){
				cb.setVisitTotalCount(article);
		}
		
		int todayCount= cb.getVisitTodayCount();
		int totalCount = cb.getVisitTotalCount();
		
		request.getSession().setAttribute("totalCount", totalCount); // ��ü �湮�� ��
		request.getSession().setAttribute("todayCount", todayCount); // ���� �湮�� ��
		
		return "/layout/main.jsp";
	}
	 
} 
