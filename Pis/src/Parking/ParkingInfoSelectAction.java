package Parking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.CommandAction;

public class ParkingInfoSelectAction implements CommandAction {

	
	
	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);

	}

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		Connection conn = null;
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null, rs2 = null;
		Vector<SearchInfoBean> vecList = new Vector<SearchInfoBean>();
		request.setCharacterEncoding("utf-8");
		float lat = 0.0f;
		float lng = 0.0f;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select * from park_info where addr like '%" + request.getParameter("addr") + "%'",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			// 검색 결과 값이 있따면
			while (rs.next()) {
				SearchInfoBean searchList = new SearchInfoBean();
				searchList.setParking_code(rs.getInt(1));
				searchList.setParking_name(rs.getString(2));
				searchList.setAddr(rs.getString(3));
				searchList.setParking_type_nm(rs.getString(4));
				searchList.setOperation_rule_nm(rs.getString(5));
				searchList.setTel(rs.getString(6));
				searchList.setCapacity2(rs.getInt(7));
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

				pstmt2 = conn
						.prepareStatement("select lat ,lng from park_location where parking_code = " + rs.getInt(1));
				rs2 = pstmt2.executeQuery();
				if (rs2.next()) {
					searchList.setLat(rs2.getString(1));
					searchList.setLng(rs2.getString(2));
					lat += Float.parseFloat(rs2.getString(1));
					lng += Float.parseFloat(rs2.getString(2));
				}
				vecList.add(searchList);
			}
			
			pstmt = conn.prepareStatement("select count(*) from park_info where addr like '%" + request.getParameter("addr") + "%'");
			rs = pstmt.executeQuery();
			if(rs.next()){
				request.setAttribute("count", rs.getInt(1));

				request.setAttribute("lat", lat/rs.getInt(1));
				request.setAttribute("lng", lng/rs.getInt(1));
				System.out.println(rs.getInt(1));
			}
			request.setAttribute("search", vecList);
			request.setAttribute("result", request.getParameter("addr"));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (rs2 != null) {
					rs2.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return "/park/maptest2.jsp";

	}

}
