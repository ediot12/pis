package Reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandAction;
import Parking.SearchInfoBean;

public class InsertReservAction implements CommandAction {

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);

	}

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<SearchInfoBean> vecList = new Vector<SearchInfoBean>();

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("memId");
		String capacity = request.getParameter("capacity2");
		
//		*** 내가 추가한 부분 ***
		request.getSession().setAttribute("capacity", capacity);
		
		String parking_code = request.getParameter("parking_code");
		
		//날짜 관련
		String weekdayOpen = request.getParameter("time1");
		String weekdayClose= request.getParameter("time5");
		String weekendOpen = request.getParameter("time2");
		String weekendClose = request.getParameter("time6");
		////////
		
		            

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select name,phone from members where id ='" + id + "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				request.setAttribute("name", rs.getString(1));
				request.setAttribute("phone", rs.getString(2));
			}

			
			//한번 끊어준다
			pstmt.close();
			rs.close();
			
			///////////////
			pstmt = conn.prepareStatement(
					"select * from park_info where parking_name like '" + request.getParameter("parking_name") + "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				SearchInfoBean searchList = new SearchInfoBean();
				searchList.setParking_code(rs.getInt(1));
				searchList.setParking_name(rs.getString(2));
				searchList.setAddr(rs.getString(3));
				searchList.setParking_type_nm(rs.getString(4));
				searchList.setOperation_rule_nm(rs.getString(5));
				searchList.setTel(rs.getString(6));
				searchList.setCapacity2(Integer.parseInt(capacity));//형변환해서 넣음
				searchList.setPay_nm(rs.getString(8));
				searchList.setWeekday_begin_time(rs.getString(9));
				searchList.setWeekday_end_time(rs.getString(10));
				searchList.setWeekend_begin_time(rs.getString(11));
				searchList.setWeekend_end_time(rs.getString(12));
				searchList.setSaturday_pay_nm(rs.getString(13));
				searchList.setHoliday_pay_nm(rs.getString(14));
				searchList.setFulltime_monthly(rs.getInt(15));
				searchList.setRates(rs.getInt(16));
				searchList.setTime_rates(rs.getInt(17));
				searchList.setAdd_rates(rs.getInt(18));
				searchList.setAdd_time_rate(rs.getInt(19));
				searchList.setDay_maximum(rs.getInt(20));
				vecList.add(searchList);
				request.setAttribute("reserv", vecList);
				request.setAttribute("wdopen", weekdayOpen);
				request.setAttribute("wdclose", weekdayClose);
				request.setAttribute("weopen", weekendOpen);
				request.setAttribute("weclose", weekendClose);
				request.setAttribute("capacity", capacity);
				request.setAttribute("parking_code", parking_code);
			} else {
				System.out.println("잘못된 접근 입니다.");
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return "/park/reserv.jsp";
	}

}
