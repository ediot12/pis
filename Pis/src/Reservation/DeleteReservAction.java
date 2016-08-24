package Reservation;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandAction;

public class DeleteReservAction	implements CommandAction {
	
	private Connection getConnection() throws Exception {     
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);

	}
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("memId");
		String beginTime = request.getParameter("beginTime");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from reservpark where id='" + id +"'and begintime=?");
			pstmt.setString(1, beginTime);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				int use_point = rs.getInt("pay");
				String parkname = rs.getString("parkname");
				
				pstmt = conn.prepareStatement("delete from reservpark where id='" + id +"'and begintime=?");
				pstmt.setString(1, beginTime);
				rs = pstmt.executeQuery();
				
//				*** test 1�ܰ� ***
				System.out.println("reservpark���̺��� ���ڵ� ����");
				
				if(rs.next()){
					pstmt = conn.prepareStatement("delete from pointlist where id='" + id +"'and use_point=? and parkname=?");
					pstmt.setInt(1, use_point);
					pstmt.setString(2, parkname);
					rs = pstmt.executeQuery();
					
//					*** test 2�ܰ� ***
					System.out.println("pointlist���̺��� ���ڵ� ����");
					
					if(rs.next()){
						pstmt = conn.prepareStatement("select point from pointment where id='"+id+"'");
						rs = pstmt.executeQuery();
						
//						*** test 3�ܰ� ***
						System.out.println("3");
						
						if(rs.next()){
							int point = rs.getInt("point");
							int before_point = use_point + point;
							
							System.out.println(point);
							pstmt = conn.prepareStatement("update pointment set point=?, use_point=? where id='"+id+"'");
							pstmt.setInt(1, before_point);
							pstmt.setInt(2, 0);
							pstmt.executeUpdate();
							
//							*** test 4�ܰ� ***
							System.out.println("pointment�� update�Ϸ� ");
		
						}
					}					
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null){
				rs.close();
			}
			
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		
		return "/park/myReserv.jsp";
	}

}
