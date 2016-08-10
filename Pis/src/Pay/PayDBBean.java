package Pay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PayDBBean {
	public static PayDBBean instance = new PayDBBean();
	public static PayDBBean getInstance(){ return instance;}
	private PayDBBean(){}
	
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
			return DriverManager.getConnection(jdbcDriver);
	}
	
//	결제 내역 db에 저장
	public void InsertPAY(PayDataBean article) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into Pay values(?,?,?,?)" );
			pstmt.setString(1, article.getId());
			pstmt.setInt(2, article.getPoint());
			pstmt.setTimestamp(3, article.getPdate());
			pstmt.setString(4, article.getInfo());			
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
