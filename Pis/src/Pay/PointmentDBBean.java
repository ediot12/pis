package Pay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PointmentDBBean {
	public static PointmentDBBean instance = new PointmentDBBean();
	public static PointmentDBBean getInstance(){ return instance;}
	private PointmentDBBean(){}
	
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
			return DriverManager.getConnection(jdbcDriver);
	}
	 
//	point결제  db에 저장
	public void InsertPoint(PointmentDataBean article) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into values(?,?,?)");
			pstmt.setString(1, article.getPay_type());
			pstmt.setString(2, article.getId());
			pstmt.setInt(3, article.getPoint());
			
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
