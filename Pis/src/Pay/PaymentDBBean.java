package Pay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaymentDBBean {

	
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
			return DriverManager.getConnection(jdbcDriver);
	}
	 
	public String requestPro(HttpServletRequest reqeust,HttpServletResponse response) throws Throwable{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn=getConnection();
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		
		
		
		return "kk";
	}
	
	
}
