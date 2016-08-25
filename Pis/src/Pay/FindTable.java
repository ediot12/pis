package Pay;

import java.util.*;
import java.sql.*;

public class FindTable {
	
	private static FindTable fd = new FindTable();
	
	public static FindTable getInstance(){
		return fd;
	}
                
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;
	String checkTime1, checkTime2, checkTime3, checkTime4 = null;
	String table = null;

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public String FindDate(String date) throws Throwable {
			System.out.println("ㅇㅇ");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select checkdate from firstdate where rownum=1");
			rs = pstmt.executeQuery();			
			if (rs.next()) {
				checkTime1 = rs.getString(1);
				
			}

			pstmt = conn.prepareStatement("select checkdate from seconddate where rownum=1");
			rs = pstmt.executeQuery();			
			if (rs.next()) {
				checkTime2 = rs.getString(1);
				
			}

			pstmt = conn.prepareStatement("select checkdate from thirddate where rownum=1");
			rs = pstmt.executeQuery();			
			if (rs.next()) {
				checkTime3 = rs.getString(1);
				
			}

			pstmt = conn.prepareStatement("select checkdate from fourthdate where rownum=1");
			rs = pstmt.executeQuery();			
			if (rs.next()) {
				checkTime4 = rs.getString(1);
				
			}

			///////////////////////////////////////////// 모두찾은뒤엔 비교를 한다.

			if (checkTime1.equals(date)) {
				table = "firstdate";

				
			}

			else if (checkTime2.equals(date)) {
				table = "seconddate"; 
				
			}
			else if (checkTime3.equals(date)) {
				table = "thirddate";

				
			} else if (checkTime4.equals(date)) {
				table = "fourthdate";

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return table;
	}
}
