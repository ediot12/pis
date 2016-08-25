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
		System.out.println("����");
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

//			*** �ڵ�COMMIT �ȵǰ� FALSE�� ���� (���� �߻��� ���� X)
			conn.setAutoCommit(false);   
			
			pstmt = conn.prepareStatement("select * from reservpark where id='" + id +"'and begintime=?");
			pstmt.setString(1, beginTime);
			rs = pstmt.executeQuery();
			
//			*** test 1 ***
			System.out.println("1�ܰ� ::: reservpark select");
			
//			sql���� ��� pay,parkname ���� ������ �����ϰ� pointment���� point,pdate select
			if(rs.next()){
				int use_point = rs.getInt("pay");
				String parkname = rs.getString("parkname");
				
				pstmt = conn.prepareStatement("select point,pdate from pointment where id='"+id+"'");
				rs = pstmt.executeQuery();
				
//				*** test 2 ***
				System.out.println("2�ܰ� ::: pointment select");
				
//				sql���� ��� point,before_point,date �� ����
				if(rs.next()){
					
					int point = rs.getInt("point");
					int before_point = use_point + point;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");					
					String date = sdf.format(rs.getTimestamp("pdate"));   
					
					
					
//					before_point������  pointment���̺��� point�� ������Ʈ
					pstmt = conn.prepareStatement("update pointment set point=?, use_point=? where id='"+id+"'");
					pstmt.setInt(1, before_point);
					pstmt.setInt(2, 0);
					result = pstmt.executeUpdate();
					
					
//					*** test 3 ***
					System.out.println("3�ܰ� ::: pointment�� update�Ϸ� ");
					
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
							System.out.println("reservpark���̺��� ���ڵ� ����");
						    
							
							if(result > 0 ){
							pstmt = conn.prepareStatement("delete from pointlist where id='" + id +"'and use_point=? and parkname=?");
							pstmt.setInt(1, use_point);
							pstmt.setString(2, parkname);
							result = pstmt.executeUpdate();
							
							System.out.println("pointlist ����");
							
								if(result > 0){									
									DeleteDate del = DeleteDate.getInstance();
									del.FindDate(day, capacity, parking_code);
									
									System.out.println("test �Ϸ�");
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
