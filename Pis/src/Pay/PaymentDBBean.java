package Pay;

import controller.CommandAction;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String beginTimehour = request.getParameter("inhour");
		String beginTimeMin = request.getParameter("inmin");
		String outTimehour = request.getParameter("outhour");
		String outTimemin = request.getParameter("outmin");
		String pay_nm = request.getParameter("pay_nm");
		String rates = request.getParameter("rates"); // 30분당 기본 가격
		

		String beginTime = beginday +" "+ beginTimehour + ":" + beginTimeMin;
		String outTime = beginday +" "+ outTimehour + ":" + outTimemin;
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date,date2 = new Date();
		
		//////////////////////// 날짜에 따른 가격 계산 하는 부분
		date = smf.parse(beginTime);
		date2 = smf.parse(outTime);
		long gap = (date2.getTime()-date.getTime())/1000/60/30; //30분단위로 계산이 될것임
		int payment = (int) gap*Integer.parseInt(rates);
		//////////////////////////////
		
		if(gap==0){
			request.setAttribute("unpass", gap);
			return "/park/payFail.jsp";
		}
		
		try {//만약 니놈의 계정이 있다면
			conn = getConnection();
			pstmt = conn.prepareStatement("select name,phone from members where id='" + id + "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				name = rs.getString(1);
				phoneNum = rs.getString(2);
			}//난 니놈의 신상을 가져 올것이다.

			if (pay_nm.equals("무료")) {//니놈이 운좋게 무료를 골랏다면
				
				request.setAttribute("name", name);
				request.setAttribute("phoneNum", phoneNum);
				request.setAttribute("parkname", parkname);
				request.setAttribute("parkloca", parkloca);
				request.setAttribute("cartype", carType);
				request.setAttribute("begintime", beginTime);
				request.setAttribute("outtime", outTime);
				request.setAttribute("payment", 0);

			} else if (pay_nm.equals("유료")) {
				
				request.setAttribute("name", name);
				request.setAttribute("phoneNum", phoneNum);
				request.setAttribute("parkname", parkname);
				request.setAttribute("parkloca", parkloca);
				request.setAttribute("cartype", carType);
				request.setAttribute("begintime", beginTime);
				request.setAttribute("outtime", outTime);
				request.setAttribute("payment", payment);
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

		return "/park/paySuccess.jsp";
	}

}
