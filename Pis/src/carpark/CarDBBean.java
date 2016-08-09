package carpark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Parking.ParkInfoBean;
import chart.ChartDataBean;
import chart.jdbcUtil;

public class CarDBBean {
	public static CarDBBean instance = new CarDBBean();
	public static CarDBBean getInstance(){ return instance; }
	private CarDBBean(){}
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
			return DriverManager.getConnection(jdbcDriver);
	}
	
	public int getArticleCount() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from park_info");
			rs = pstmt.executeQuery();
			
			if(rs.next()) x = rs.getInt(1);
		}catch(Exception e){ e.printStackTrace(); }
		finally{
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}
		return x;
	}
	//carpark.jsp ==> Paging!!! DB로부터 여러행을 결과로 받는다.
	public Vector getArticles(int start, int end) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector vecList = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select parking_name, addr, tel, weekday_begin_time, weekday_end_time, weekend_begin_time, weekend_end_time, operation_rule_nm, capacity2,r  " +
		            "from (select parking_name, addr, tel, weekday_begin_time, weekday_end_time, weekend_begin_time, weekend_end_time, operation_rule_nm, capacity2,rownum r " +
		            "from (select parking_name, addr, tel, weekday_begin_time, weekday_end_time, weekend_begin_time, weekend_end_time, operation_rule_nm, capacity2 " +
		            "from park_info order by parking_name asc) order by parking_name asc) where r >= ? and r <= ? ");
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				vecList = new Vector(end);
				do{
					ParkInfoBean pib = new ParkInfoBean();
					pib.setParking_name(rs.getString("parking_name"));
					pib.setAddr(rs.getString("addr"));
					pib.setTel(rs.getString("tel"));
					pib.setWeekday_begin_time(rs.getString("weekday_begin_time"));
					pib.setWeekday_end_time(rs.getString("weekday_end_time"));
					pib.setWeekend_begin_time(rs.getString("weekend_begin_time"));
					pib.setWeekend_end_time(rs.getString("weekend_end_time"));
					pib.setOperation_rule_nm(rs.getString("operation_rule_nm"));
					pib.setCapacity2(rs.getInt("capacity2"));
					vecList.add(pib);
				}while(rs.next());
			}
		}catch(Exception e){ e.printStackTrace(); }
		finally{
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}
		return vecList;
	}
	public void setCarpark(ParkInfoBean pib) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into park_info values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pib.getParking_code());
			pstmt.setString(2, pib.getParking_name());
			pstmt.setString(3, pib.getAddr());
			pstmt.setString(4, pib.getParking_type_nm());
			pstmt.setString(5, pib.getOperation_rule_nm());
			pstmt.setString(6, pib.getTel());
			pstmt.setInt(7, pib.getCapacity2());
			pstmt.setString(8, pib.getPay_nm());
			pstmt.setString(9, pib.getWeekday_begin_time());
			pstmt.setString(10, pib.getWeekday_end_time());
			pstmt.setString(11, pib.getWeekend_begin_time());
			pstmt.setString(12, pib.getWeekend_end_time());
			pstmt.setString(13, pib.getSaturday_pay_nnm());
			pstmt.setString(14, pib.getHoliday_pay_nm());
			pstmt.setInt(15, pib.getFulltime_monthly());
			pstmt.setInt(16, pib.getRates());
			pstmt.setInt(17, pib.getTime_rate());
			pstmt.setInt(18, pib.getAdd_rates());
			pstmt.setInt(19, pib.getAdd_time_rate());
			pstmt.setInt(20, pib.getDay_maximum());
			
			pstmt.executeUpdate();
			
		}catch(Exception e){ e.printStackTrace(); }
		finally{
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}
	}
	//search : 검색 만들기
		public int getArticleCount(int n, String search) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int x = 0;
			String[] search_name = {"parking_name", "addr"};
			
			try{
				conn = getConnection();
				
				pstmt = conn.prepareStatement("select count(*) from park_info where "+search_name[n]+" like '%"+search+"%'");
				rs = pstmt.executeQuery();
				
				if(rs.next()) x= rs.getInt(1);
				
			}catch(Exception e){ e.printStackTrace(); }
			finally{
				jdbcUtil.close(rs);
				jdbcUtil.close(pstmt);
				jdbcUtil.close(conn);
			}
			return x;
		}
		public Vector getArticles(int start, int end, int n, String search) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Vector vecList = null;
			
			String[] search_name = {"parking_name", "addr"};
			try{
				conn = getConnection();
				String sql="select parking_name, addr, tel, weekday_begin_time, weekday_end_time, weekend_begin_time, weekend_end_time, operation_rule_nm, capacity2,r  " +
			            "from (select parking_name, addr, tel, weekday_begin_time, weekday_end_time, weekend_begin_time, weekend_end_time, operation_rule_nm, capacity2,rownum r " +
			            "from (select parking_name, addr, tel, weekday_begin_time, weekday_end_time, weekend_begin_time, weekend_end_time, operation_rule_nm, capacity2 " +
			            "from park_info where "+search_name[n]+" Like '%"+search+"%' order by parking_name asc) order by parking_name asc) where r >= ? and r <= ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					vecList = new Vector(end);
					do{
						ParkInfoBean pib = new ParkInfoBean();
						pib.setParking_name(rs.getString("parking_name"));
						pib.setAddr(rs.getString("addr"));
						pib.setTel(rs.getString("tel"));
						pib.setWeekday_begin_time(rs.getString("weekday_begin_time"));
						pib.setWeekday_end_time(rs.getString("weekday_end_time"));
						pib.setWeekend_begin_time(rs.getString("weekend_begin_time"));
						pib.setWeekend_end_time(rs.getString("weekend_end_time"));
						pib.setOperation_rule_nm(rs.getString("operation_rule_nm"));
						pib.setCapacity2(rs.getInt("capacity2"));
						vecList.add(pib);
					}while(rs.next());
				}
			}catch(Exception e){ e.printStackTrace(); }
			finally{
				jdbcUtil.close(rs);
				jdbcUtil.close(pstmt);
				jdbcUtil.close(conn);
			}
			return vecList;
		}
}
