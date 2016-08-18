package Parking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InsertReservAction implements CommandAction {

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);

	}

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id = (String) session.getAttribute("memid");

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select name,phone from members where id ='" + id + "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				request.setAttribute("name", rs.getString(1));
				request.setAttribute("phone", rs.getString(2));
			}
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

		return "/park/reserv.jsp";
	}

}
