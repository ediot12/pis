package Pay;

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

import controller.CommandAction;

public class PayPointCheckAction implements CommandAction {

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String id = (String) session.getAttribute("memId");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String cartype = request.getParameter("cartype");
		String parkname = request.getParameter("parkname");
		String parkloca = request.getParameter("parkloca");
		String begintime = request.getParameter("begintime");
		String outtime = request.getParameter("outtime");
		int parking_code = Integer.parseInt(request.getParameter("parking_code"));
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		int pay = Integer.parseInt(request.getParameter("point"));// ���� �ݾ�
		int remainPoint = 0; // �ʱ�ȭ
		int sPoint, sUse_Point = 0;// update���� ���� �ʱ�ȭ �۾�
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();
		date = smf.parse(begintime);

		try {// ������Ȳ�� ���� db�� �ְ� �״��� ����Ʈ ��� ��Ȳ�� db�� ����
			conn = getConnection();
			pstmt = conn.prepareStatement("select point from pointment where id ='" + id + "' order by pdate desc");
			rs = pstmt.executeQuery();
			if (rs.next()) {// ������ �ֱ� ��¥���� ���� point�� ������ �ű� ������ if���Ѵ�.
				System.out.println("���� ����Ʈ : " + rs.getInt(1));
				remainPoint = rs.getInt(1);
			}

			if (remainPoint < pay) {// ���� �ܿ� ����Ʈ���� ������ ����Ʈ���� ���ٸ�
				request.setAttribute("notpass", date);
				request.setAttribute("remainPoint", remainPoint);
				request.setAttribute("pay", pay);
				return "/park/payFail.jsp";// ������ ������ ���ѹ����� �Ʒ� �������� ���൵ ���� �ʴ´�.
			}

			//////////// ���� ������ ����Ʈ ���뿡 ���� ���̺��� ������Ʈ �����ش�.
			sPoint = remainPoint - pay; // �ܿ�����Ʈ = ���� ������ �ִ� ����Ʈ - ���� ����Ʈ
			sUse_Point = pay; // ���� ����Ʈ = ���� ����Ʈ
			pstmt = conn.prepareStatement(
					"update pointment set point = ? ,use_point = ?,info='����Ʈ���',pdate=sysdate where id='" + id + "'");
			pstmt.setInt(1, sPoint);
			pstmt.setInt(2, sUse_Point);
			pstmt.executeUpdate();
			//////////////
			
			pstmt = conn.prepareStatement("update firstdate set capacity2=? where parking_code = ?");
			pstmt.setInt(1, capacity-1);
			pstmt.setInt(2, parking_code);
			pstmt.executeUpdate();

			//////////// ������Ȳ�� admin�� �� �� �ִ� ����Ʈ ��� ��Ȳ ���̺� insert �۾�
			pstmt = conn.prepareStatement("insert into reservpark values(?,?,?,?,?,?,?,?,?,reserv_num.NEXTVAL)");
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
			pstmt.setString(4, parkname);
			pstmt.setString(5, parkloca);
			pstmt.setString(6, cartype);
			pstmt.setString(7, begintime);
			pstmt.setString(8, outtime);
			pstmt.setInt(9, pay);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement("insert into pointlist values(pointlist_num.NEXTVAL,?,?,?,?,?,?)");

			pstmt.setString(1, id);
			pstmt.setInt(2, 0);
			pstmt.setInt(3, pay);
			pstmt.setString(4, "����Ʈ���");
			pstmt.setDate(5, new java.sql.Date(date.getTime()));
			pstmt.setString(6, parkname);
			pstmt.executeUpdate();
			////////////////

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}

		return "/park/payAfter.jsp";
	}

}
