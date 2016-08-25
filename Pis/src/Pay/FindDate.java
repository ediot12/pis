package Pay;

import java.util.*;
import java.sql.*;

public class FindDate {
	
	private static FindDate fd = new FindDate();
	
	public static FindDate getInstance(){
		return fd;
	}

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;
	String checkTime1, checkTime2, checkTime3, checkTime4 = null;

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public void FindDate(String date, int capacity, int parking_code) throws Throwable {

		try {
			conn = getConnection();
			
//			*** 자동COMMIT 안되게 FALSE로 지정 (오류 발생시 실행 X)
			conn.setAutoCommit(false);   
			
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
				firstdate(capacity, parking_code);
			}

			else if (checkTime2.equals(date)) {
				seconddate(capacity, parking_code);
			}
			else if (checkTime3.equals(date)) {
				thirddate(capacity, parking_code);
			} else if (checkTime4.equals(date)) {
				fourthdate(capacity, parking_code);
			}
			
			conn.commit();      

		} catch (SQLException e) {
			conn.rollback();
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
		
		conn.setAutoCommit(true);  

	}

	private void firstdate(int capacity, int parking_code) throws Throwable {
		conn = getConnection();
		pstmt = conn.prepareStatement("update firstdate set capacity2=? where parking_code = ?");
		pstmt.setInt(1, capacity - 1);
		pstmt.setInt(2, parking_code);
		pstmt.executeUpdate();

	}

	private void seconddate(int capacity, int parking_code) throws Throwable {
		conn = getConnection();
		pstmt = conn.prepareStatement("update seconddate set capacity2=? where parking_code = ?");
		pstmt.setInt(1, capacity - 1);
		pstmt.setInt(2, parking_code);
		pstmt.executeUpdate();
	}

	private void thirddate(int capacity, int parking_code) throws Throwable {
		conn = getConnection();
		pstmt = conn.prepareStatement("update thirddate set capacity2=? where parking_code = ?");
		pstmt.setInt(1, capacity - 1);
		pstmt.setInt(2, parking_code);
		pstmt.executeUpdate();
	}

	private void fourthdate(int capacity, int parking_code) throws Throwable {
		conn = getConnection();
		pstmt = conn.prepareStatement("update fourthdate set capacity2=? where parking_code = ?");
		pstmt.setInt(1, capacity - 1);
		pstmt.setInt(2, parking_code);
		pstmt.executeUpdate();
	}

}
