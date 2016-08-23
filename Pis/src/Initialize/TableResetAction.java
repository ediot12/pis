package Initialize;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableResetAction {

	private static TableResetAction tra = new TableResetAction();

	public static TableResetAction getInstance() {
		return tra;
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public void initDB() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from firstdate");
			count = pstmt.executeUpdate();

			pstmt.close();// �ѹ� �ݾ��ش�.
			pstmt = conn.prepareStatement("insert into firstdate select * from seconddate");
			count = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("update firstdate set checkdate = to_char(sysdate,'yyyy-MM-dd')");
			count = pstmt.executeUpdate();
			System.out.println("ù��° ��� �� : " + count);

			pstmt = conn.prepareStatement("delete from seconddate");
			count = pstmt.executeUpdate();

			pstmt = conn.prepareStatement("insert into seconddate select * from thirddate");
			count = pstmt.executeUpdate();
			

			pstmt = conn.prepareStatement("update seconddate set checkdate = to_char(sysdate+1,'yyyy-MM-dd')");
			count = pstmt.executeUpdate();

			System.out.println("�ι�° ��� �� : " + count);

			pstmt = conn.prepareStatement("delete from thirddate");
			count = pstmt.executeUpdate();

			pstmt = conn.prepareStatement("insert into thirddate select * from fourthdate");
			count = pstmt.executeUpdate();
			

			pstmt = conn.prepareStatement("update thirddate set checkdate = to_char(sysdate+2,'yyyy-MM-dd')");
			count = pstmt.executeUpdate();

			System.out.println("����° ��� �� : " + count);

			pstmt = conn.prepareStatement("delete from fourthdate");
			count = pstmt.executeUpdate();

			pstmt = conn.prepareStatement(
					"insert into fourthdate(parking_code,addr,capacity2) select parking_code,addr,capacity2 from park_info");
			count = pstmt.executeUpdate();
			

			pstmt = conn.prepareStatement("update fourthdate set checkdate = to_char(sysdate+3,'yyyy-MM-dd')");
			count = pstmt.executeUpdate();

			System.out.println("�׹�° ��� �� : " + count);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
