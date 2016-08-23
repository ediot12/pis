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
		int pay = Integer.parseInt(request.getParameter("point"));// 계산된 금액
		int remainPoint = 0; // 초기화
		int sPoint, sUse_Point = 0;// update문을 위한 초기화 작업
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();
		date = smf.parse(begintime);

		try {// 예약현황에 먼저 db에 넣고 그다음 포인트 사용 현황에 db를 넣음
			conn = getConnection();
			pstmt = conn.prepareStatement("select point from pointment where id ='" + id + "' order by pdate desc");
			rs = pstmt.executeQuery();
			if (rs.next()) {// 어차피 최근 날짜에서 현재 point를 빼오는 거기 때문에 if로한다.
				System.out.println("현재 포인트 : " + rs.getInt(1));
				remainPoint = rs.getInt(1);
			}

			if (remainPoint < pay) {// 만약 잔여 포인트보다 내야할 포인트보다 적다면
				request.setAttribute("notpass", date);
				request.setAttribute("remainPoint", remainPoint);
				request.setAttribute("pay", pay);
				return "/park/payFail.jsp";// 어차피 리턴을 시켜버리면 아래 쿼리들은 실행도 되지 않는다.
			}

			//////////// 기존 유저별 포인트 내용에 관한 테이블을 업데이트 시켜준다.
			sPoint = remainPoint - pay; // 잔여포인트 = 내가 가지고 있는 포인트 - 결제 포인트
			sUse_Point = pay; // 사용된 포인트 = 결제 포인트
			pstmt = conn.prepareStatement(
					"update pointment set point = ? ,use_point = ?,info='포인트사용',pdate=sysdate where id='" + id + "'");
			pstmt.setInt(1, sPoint);
			pstmt.setInt(2, sUse_Point);
			pstmt.executeUpdate();
			//////////////
			
			pstmt = conn.prepareStatement("update firstdate set capacity2=? where parking_code = ?");
			pstmt.setInt(1, capacity-1);
			pstmt.setInt(2, parking_code);
			pstmt.executeUpdate();

			//////////// 예약현황과 admin이 볼 수 있는 포인트 사용 현황 테이블에 insert 작업
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
			pstmt.setString(4, "포인트사용");
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
