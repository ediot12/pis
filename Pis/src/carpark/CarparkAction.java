package carpark;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Parking.ParkInfoBean;
import controller.CommandAction;

public class CarparkAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("pageNum");
		
		if(pageNum == null){ pageNum = "1"; }
		int pageSize = 20; //한페이지의 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1; //한페이지의 시작글 번호
		int endRow = currentPage * pageSize;//한페이지의 마지막 글번호
		int count = 0;
		int number = 0;
		
		//search기능
		String search = request.getParameter("search");
		int searchn = 0;
		if(search==null) search="";
		else searchn = Integer.parseInt(request.getParameter("searchn"));
		////////////////////////////////////////////////////////////////////
		
		CarDBBean cdb = CarDBBean.getInstance();//DB연동		
		if(search.equals("") || search == null) count = cdb.getArticleCount();//전체 글의 수
		else count = cdb.getArticleCount(searchn, search);
		
		Vector parkList = cdb.getArticles(startRow, endRow);
		
		int totalList = parkList.size();
		
			for(int i=0; i<totalList; i++){ 
				ParkInfoBean pid = (ParkInfoBean)parkList.elementAt(i);
				String parking_name = pid.getParking_name();
				String addr = pid.getAddr();
				String tel = pid.getTel();
				String weekday_begin_time = pid.getWeekday_begin_time();
				String weekday_end_time = pid.getWeekday_end_time();
				String weekend_begin_time = pid.getWeekend_begin_time();
				String weekend_end_time = pid.getWeekend_end_time();
				String operation_rule_nm = pid.getOperation_rule_nm();
				int capacity2 = pid.getCapacity2();
			}
			
		//search기능
		if(count > 0){
		if(search.equals("") || search == null) parkList = cdb.getArticles(startRow, endRow);
		else parkList = cdb.getArticles(startRow, endRow, searchn, search);
		}
		/////////////////////////////////////////////////////////
		
		request.setAttribute("search", search);
		request.setAttribute("searchn", searchn);
		request.setAttribute("parkList", parkList);
		request.setAttribute("totalList", totalList);
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		
		return "/semi/admin/carpark/carpark.jsp";
	}

}
