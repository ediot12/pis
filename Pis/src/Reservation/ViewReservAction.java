package Reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Parking.SearchInfoBean;
import controller.CommandAction;

public class ViewReservAction implements CommandAction {

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HttpSession session = request.getSession();
		Vector<ReservBean> reservList = new Vector<ReservBean>();
		String id = (String) session.getAttribute("memId");
		int count = 0;
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();//날짜비교
		Date date2 = new Date();//오늘날짜
		

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from reservpark where id ='" + id + "'");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReservBean rsb = new ReservBean();
				rsb.setId(rs.getString(1));
				rsb.setName(rs.getString(2));
				rsb.setPhoneNum(rs.getString(3));
				rsb.setParking_name(rs.getString(4));
				rsb.setParking_loca(rs.getString(5));
				rsb.setCarType(rs.getString(6));
				rsb.setBeginTime(rs.getString(7));
				rsb.setOutTime(rs.getString(8));
				rsb.setCost(rs.getInt(9));
				rsb.setNum(rs.getInt(10));
				date = smf.parse(rs.getString(7));
				long gap = date2.getTime()-date.getTime();
				if(gap<600000){
					rsb.setCheck(true);
				}
				else{
					rsb.setCheck(false);
				}

				reservList.add(rsb);
				count += 1;
				
			}
			
			request.setAttribute("recount", count);
			request.setAttribute("myreservList", reservList);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();

				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return "/park/myReserv.jsp";
	}

}
