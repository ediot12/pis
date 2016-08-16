package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MemberDAO {

	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {

		return instance;
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);

	}

	public void insertMember(MemberDTO dto) throws Throwable{
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into member values(?,?,?,?)");
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getFilePath());
			pstmt.executeUpdate();
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		finally{
			if(conn!=null){
				conn.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
		}
		
		
		
	}

}
