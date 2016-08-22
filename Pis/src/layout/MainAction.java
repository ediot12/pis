package layout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pay.PayDBBean;
import board.FAQDBBean;
import board.NoticeDBBean;
import chart.ChartDBBean;
import chart.ChartDataBean;
import controller.CommandAction;
import logon.LogonDBBean;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
public class MainAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
//		main에 표현할 id정보에 따른 등급,point
		String memId = (String) request.getSession().getAttribute("memId");
		LogonDBBean logdb = LogonDBBean.getInstance();
		String grade = logdb.getGrade(memId);
		request.getSession().setAttribute("grade", grade);
		
		PayDBBean paydb = PayDBBean.getInstance();
		int point = paydb.getPoint(memId);
		request.getSession().setAttribute("point", point);
		
		
		
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
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

		//공지사항 받아오기!
		String pageNum = request.getParameter("pageNum");

		if(pageNum == null){ pageNum = "1";}
		int pageSize = 5; //한페이지의 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1; //한페이지의 시작글 번호
		int endRow = currentPage * pageSize;//한페이지의 마지막 글번호
		int count = 0;
		int cou = 0;
		int number = 0;
		int num = 0;

		NoticeDBBean dbpro = NoticeDBBean.getInstance();
		List articleList = dbpro.getArticles(startRow, endRow);
		
		count = dbpro.getArticleCount();
		
		if(count > 0){
			articleList = dbpro.getArticles(startRow, endRow);//현재 페이지에 해당하는 글 목록
		}else articleList = Collections.EMPTY_LIST;
		
		number = count-(currentPage-1)*pageSize;
		
		//자주묻는질문 받아오기
		FAQDBBean pro = FAQDBBean.getInstance();
		List articleList2 = pro.getArticles(startRow, endRow);
		
		cou = pro.getArticleCount();
		
		if(cou > 0){
			articleList2 = pro.getArticles(startRow, endRow);//현재 페이지에 해당하는 글 목록
		}else articleList2 = Collections.EMPTY_LIST;

		num = cou-(currentPage-1)*pageSize;
		
		request.getSession().setAttribute("totalCount", totalCount); // 전체 방문자 수
		request.getSession().setAttribute("todayCount", todayCount); // 오늘 방문자 수
		request.setAttribute("date", date);
		request.setAttribute("currentPage", new Integer(currentPage));
	    request.setAttribute("count", new Integer(count));
	    request.setAttribute("pageSize", new Integer(pageSize));
	    request.setAttribute("number", new Integer(number));
	    request.setAttribute("num", new Integer(num));
	    request.setAttribute("articleList", articleList);
	    request.setAttribute("articleList2", articleList2);
	   
		
		return "/layout/main.jsp";
	}
	 
} 
