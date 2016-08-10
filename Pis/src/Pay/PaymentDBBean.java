package Pay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PaymentDBBean {
	public static PaymentDBBean instance = new PaymentDBBean();
	public static PaymentDBBean getInstance(){ return instance;}
	private PaymentDBBean(){}
	
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
			return DriverManager.getConnection(jdbcDriver);
	}
	
//	결제 내역 db에 저장
	public void Insertpayment(PaymentDataBean article) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into Payment values(?,?,?,?,?,?,?)" );
			pstmt.setString(1, article.getPay_type());
			pstmt.setString(2, article.getNum());
			pstmt.setString(3, article.getParking_name());
			pstmt.setInt(4, article.getCost());
			pstmt.setString(5, article.getName());
			pstmt.setString(6, article.getEmail());
			pstmt.setString(7, article.getPhonenum());
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			
		}
	}
	
//	point결제내역 db 저장 
	public void InsertPoint(PointmentDataBean member) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into values(?,?,?)");
			pstmt.setString(1, member.getPay_type());
			pstmt.setString(2, member.getId());
			pstmt.setInt(3, member.getPoint());
			
			pstmt.executeUpdate();
			
			System.out.println("db에 insert");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(Exception e){}
			if(conn != null) try{conn.close();} catch(Exception e){}
		}
		
	}

	
}
