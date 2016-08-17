package Pay;

import controller.CommandAction;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

public class PaymentDBBean implements CommandAction {

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		HttpSession session = request.getSession(); 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		request.setCharacterEncoding("utf-8");
		String id = (String) session.getAttribute("memId");
		String name = null;
		String phoneNum = null;
		String parkname = request.getParameter("parkname");
		String parkloca = request.getParameter("parkloca");
		String carType = request.getParameter("car");
		String beginday = request.getParameter("calendar1");
		String outday = request.getParameter("calendar2");
		String beginTimehour = request.getParameter("inhour");
		String beginTimeMin = request.getParameter("inmin");
		String outTimehour = request.getParameter("outhour");
		String outTimemin = request.getParameter("outmin");

		
		String beginTime = beginTimehour + "시" + beginTimeMin + "분";
		String outTime = outTimehour + "시" + outTimemin + "분";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select id,phone from members where id='"+id+"'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				name = rs.getString(1);
				phoneNum = rs.getString(2);
			}
			pstmt = conn.prepareStatement("insert into reservpark values(?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, name);
			pstmt.setString(2, phoneNum);
			pstmt.setString(3, parkname);
			pstmt.setString(4, parkloca);
			pstmt.setString(5, carType);
			pstmt.setString(6, beginday);
			pstmt.setString(7, outday);
			pstmt.setString(8, beginTime);
			pstmt.setString(9, outTime);
			pstmt.executeUpdate();
			request.setAttribute("name", name);
			request.setAttribute("phoneNum", phoneNum);
			request.setAttribute("parkname", parkname);
			request.setAttribute("parkloca", parkloca);
			request.setAttribute("cartype", carType);
			request.setAttribute("beginday", beginday);
			request.setAttribute("outday", outday);
			request.setAttribute("begintime", beginTime);
			request.setAttribute("outtime", outTime);
			

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

		return "/park/paySuccess.jsp";
	}

}
