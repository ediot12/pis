package Pay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import logon.LogonDataBean;

public class PayDBBean {
	public static PayDBBean instance = new PayDBBean();
	public static PayDBBean getInstance(){ return instance;}
	private PayDBBean(){}
	
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
			return DriverManager.getConnection(jdbcDriver);
	} 
	
//	PointChargeAction ::: 최근 total_point 값을 가져옴. 
	public int getTotalPoint(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total_point = 0;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select total_point from pointment where id=? order by pdate desc");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				total_point = rs.getInt("total_point");
			}
			
		}catch(Exception e){
			
			
		}
		
		return total_point;
	}
	
	
	
//	결제 내역 db에 저장
	public void InsertPAY(PayDataBean article) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into pointment values(?,?,?,?,?,?)" );
			pstmt.setString(1, article.getId());
			pstmt.setInt(2, article.getPoint());
			pstmt.setInt(3, article.getUse_point());
			pstmt.setString(4, article.getInfo());	
			pstmt.setTimestamp(5, article.getPdate());
			pstmt.setInt(6, article.getTotal_point());		
			pstmt.executeUpdate(); 
			
			System.out.println("db에 insert");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(Exception e){}
			if(conn != null) try{conn.close();} catch(Exception e){}
		}
		
	}
	
	
//	pointment.jsp ::: name 값 가져와야함 
	public String selectName(String id)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = "";
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select name from members where id=?" );
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				name = rs.getString("name");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(Exception e){}
			if(pstmt != null) try{pstmt.close();}catch(Exception e){}
			if(conn != null) try{conn.close();} catch(Exception e){}
		}
		return name;
	}
	
	

//	paymentForm.jsp ::: el로 출력될 id정보값 
	public LogonDataBean selectUserInfo(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LogonDataBean logon = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from members where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			logon = new LogonDataBean();		
			
			if(rs.next()){ 				
				logon.setName(rs.getString("name"));
				logon.setEmail(rs.getString("email"));
				logon.setPhone(rs.getString("phone"));
				logon.setAddress(rs.getString("address"));
				logon.setZipcode(rs.getString("zipcode"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(Exception e){}
			if(pstmt != null) try{pstmt.close();}catch(Exception e){}
			if(conn != null) try{conn.close();} catch(Exception e){}
		}
		
		return logon;
	}
	
}
