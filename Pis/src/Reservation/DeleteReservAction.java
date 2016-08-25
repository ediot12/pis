package Reservation;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Pay.DeleteDate;
import Pay.FindTable;
import controller.CommandAction;

public class DeleteReservAction	implements CommandAction {
	
	private Connection getConnection() throws Exception {     
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);

	}
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		System.out.println("ㅎㅇ");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("memId");
		String beginTime = request.getParameter("beginTime");
		String fparkname = request.getParameter("parkname");

		String day = beginTime.substring(0, 10);
		
		FindTable ft = FindTable.getInstance();
		String table = ft.FindDate(day);
		System.out.println("deletereservaction ::: " +table);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;	
		
		
		try{
			conn = getConnection();

//			*** 자동COMMIT 안되게 FALSE로 지정 (오류 발생시 실행 X)
			conn.setAutoCommit(false);   
			
			pstmt = conn.prepareStatement("select * from reservpark where id='" + id +"'and begintime=?");
			pstmt.setString(1, beginTime);
			rs = pstmt.executeQuery();
			
//			*** test 1 ***
			System.out.println("1단계 ::: reservpark select");
			
//			sql실행 결과 pay,parkname 각각 변수에 저장하고 pointment에서 point,pdate select
			if(rs.next()){
				int use_point = rs.getInt("pay");
				String parkname = rs.getString("parkname");
				
				pstmt = conn.prepareStatement("select point,pdate from pointment where id='"+id+"'");
				rs = pstmt.executeQuery();
				
//				*** test 2 ***
				System.out.println("2단계 ::: pointment select");
				
//				sql실행 결과 point,before_point,date 값 저장
				if(rs.next()){
					
					int point = rs.getInt("point");
					int before_point = use_point + point;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");					
					String date = sdf.format(rs.getTimestamp("pdate"));   
					
					
					
//					before_point값으로  pointment테이블의 point값 업데이트
					pstmt = conn.prepareStatement("update pointment set point=?, use_point=? where id='"+id+"'");
					pstmt.setInt(1, before_point);
					pstmt.setInt(2, 0);
					result = pstmt.executeUpdate();
					
					
//					*** test 3 ***
					System.out.println("3단계 ::: pointment에 update완료 ");
					
					if(result > 0 ){
			
						pstmt = conn.prepareStatement("SELECT parking_code,capacity2 from "+table+" WHERE addr in (SELECT substr(parkloca,7) FROM reservpark where id=? and parkname like '"
								+ fparkname + "')");
						pstmt.setString(1, id);
						rs = pstmt.executeQuery();
						
						if(rs.next()){
							int parking_code = rs.getInt("parking_code");
							int capacity = rs.getInt("capacity2");
							System.out.println("deleteaction ::: " + parking_code);
							System.out.println("deleteaction ::: " + capacity);
							
							pstmt = conn.prepareStatement("delete from reservpark where id='" + id +"'and begintime=?");
							pstmt.setString(1, beginTime);
							result = pstmt.executeUpdate();
						
							
//						*** test 4 ***
							System.out.println("reservpark테이블에서 레코드 삭제");
						    
							
							if(result > 0 ){
							pstmt = conn.prepareStatement("delete from pointlist where id='" + id +"'and use_point=? and parkname=?");
							pstmt.setInt(1, use_point);
							pstmt.setString(2, parkname);
							result = pstmt.executeUpdate();
							
							System.out.println("pointlist 삭제");
							
								if(result > 0){									
									DeleteDate del = DeleteDate.getInstance();
									del.FindDate(day, capacity, parking_code);
									
									System.out.println("test 완료");
								}
							}
						}
					}     
				}
			}	
			
			conn.commit();
						
		}catch(Exception e){
			conn.rollback();
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
			
			conn.setAutoCommit(true); 
		}
		
		return "/park/myReserv.do";
	}

}
