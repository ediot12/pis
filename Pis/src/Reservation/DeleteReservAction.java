package Reservation;
 
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Pay.DeleteDate;
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
				
//				*** test 1단계 ***
				System.out.println("reservpark테이블에서 레코드 삭제");
				if(rs.next()){
					pstmt = conn.prepareStatement("delete from pointlist where id='" + id +"'and use_point=? and parkname=?");
					pstmt.setInt(1, use_point);
					pstmt.setString(2, parkname);
					rs = pstmt.executeQuery();
					
//					*** test 2단계 ***
					System.out.println("pointlist테이블에서 레코드 삭제");
					
					if(rs.next()){
						pstmt = conn.prepareStatement("select point from pointment where id='"+id+"'");
						rs = pstmt.executeQuery();
						
//						*** test 3단계 ***
						System.out.println("3단계");
						
						if(rs.next()){
							
							int point = rs.getInt("point");
							int before_point = use_point + point;
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							rs.getString("pdate");
							String date = sdf.format(date1);    
							
							
							System.out.println("3단계 이후 ::: " + point);
							pstmt = conn.prepareStatement("update pointment set point=?, use_point=? where id='"+id+"'");
							pstmt.setInt(1, before_point);
							pstmt.setInt(2, 0);
							pstmt.executeUpdate();
							System.out.println("pointment에 update완료 ");
							
							
							pstmt = conn.prepareStatement("SELECT parking_code,capacity2 FROM FIRSTDATE WHERE addr in (SELECT substr(parkloca,7)  FROM reservpark where id='?'");
							pstmt.setString(1, id);
							rs = pstmt.executeQuery();
							
//							*** test ***
							System.out.println("select parking_code!");
							
							if(rs.next()){
								
							DeleteDate del = DeleteDate.getInstance();
							del.FindDate(date, rs.getInt("capacity2"), rs.getInt("parking_code"));
							
							System.out.println("test 완료");
							
							}
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
