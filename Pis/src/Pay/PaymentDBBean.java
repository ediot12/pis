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
		String rates = request.getParameter("rates"); // 30�д� �⺻ ����
		

		String beginTime = beginday +" "+ beginTimehour + ":" + beginTimeMin;
		String outTime = beginday +" "+ outTimehour + ":" + outTimemin;
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date,date2 = new Date();
		
		//////////////////////// ��¥�� ���� ���� ��� �ϴ� �κ�
		date = smf.parse(beginTime);
		date2 = smf.parse(outTime);
		long gap = (date2.getTime()-date.getTime())/1000/60/30; //30�д����� ����� �ɰ���
		int payment = (int) gap*Integer.parseInt(rates);
		//////////////////////////////
		
		if(gap==0){
			request.setAttribute("unpass", gap);
			return "/park/payFail.jsp";
		}
		
		try {//���� �ϳ��� ������ �ִٸ�
			conn = getConnection();
			pstmt = conn.prepareStatement("select name,phone from members where id='" + id + "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				name = rs.getString(1);
				phoneNum = rs.getString(2);
			}//�� �ϳ��� �Ż��� ���� �ð��̴�.

			if (pay_nm.equals("����")) {//�ϳ��� ������ ���Ḧ ����ٸ�
				
				request.setAttribute("name", name);
				request.setAttribute("phoneNum", phoneNum);
				request.setAttribute("parkname", parkname);
				request.setAttribute("parkloca", parkloca);
				request.setAttribute("cartype", carType);
				request.setAttribute("begintime", beginTime);
				request.setAttribute("outtime", outTime);
				request.setAttribute("payment", 0);

			} else if (pay_nm.equals("����")) {
				
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
