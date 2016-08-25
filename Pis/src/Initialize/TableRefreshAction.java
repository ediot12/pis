package Initialize;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableRefreshAction {

	private static TableRefreshAction tra = new TableRefreshAction();

	public static TableRefreshAction getInstance() {
		return tra;
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public void refreshTable() throws Throwable{
		Connection conn = null;
		PreparedStatement pstmt = null,pstmt2 = null,pstmt3 = null;
		ResultSet rs = null,rs2=null;
		String id = null;
		int parking_code = 0;
		String parking_name = null;
		int capacity = 0;
		int count =0;

		try {
			conn = getConnection();
			
//			*** 자동COMMIT 안되게 FALSE로 지정 (오류 발생시 실행 X)
			conn.setAutoCommit(false);  
			
			pstmt = conn.prepareStatement("select id,parkname from reservpark where outtime < to_char(sysdate,'yyyy-MM-dd')");
			rs = pstmt.executeQuery();
			while(rs.next()){
				id = rs.getString(1);
				parking_name = rs.getString(2);
				pstmt2 = conn.prepareStatement(
						"select parking_code,capacity2 from firstdate where addr in (select substr(parkloca,7) from reservpark where parkname like ? and id = ?)");
				pstmt2.setString(1, parking_name);
				pstmt2.setString(2, id);
				rs2 = pstmt2.executeQuery();
				if(rs2.next()){
					parking_code = rs2.getInt(1);
					capacity = rs2.getInt(2);
				}
				pstmt3 = conn.prepareStatement("update firstdate set capacity2 = ? where parking_code = ?");
				pstmt3.setInt(1, capacity+1);
				pstmt3.setInt(2, parking_code);
				pstmt3.executeUpdate();
				
			}
			//////////////
			pstmt.close();
			//////////////
			pstmt = conn.prepareStatement("delete from reservpark where to_date(outtime,'yyyy-MM-dd HH24:MI') < sysdate");
			count = pstmt.executeUpdate();
			
			conn.commit(); 
		} catch (SQLException e) {
			conn.rollback();
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
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (rs2 != null) {
					rs2.close();
				}
				if (pstmt3 != null) {
					pstmt3.close();
				}
			} catch (SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		}
		
		conn.setAutoCommit(true);

	}

}
