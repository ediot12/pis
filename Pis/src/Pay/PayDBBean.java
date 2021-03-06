package Pay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.jasper.tagplugins.jstl.core.Catch;

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
			e.printStackTrace();
			
		}finally{
			 if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		return total_point;
	}
	
	  
	
//	결제 내역 db에 저장
	public void InsertPay(PayDataBean article) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int point = article.getPoint();
		String id = article.getId();
		String grade = null;
		
		
		try{
			conn = getConnection();
			
//			*** 자동COMMIT 안되게 FALSE로 지정 (오류 발생시 실행 X)
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement("select point from pointment where id=? order by pdate desc");
			pstmt.setString(1, article.getId());
			rs = pstmt.executeQuery();	
			if(rs.next()){		
			
			int befor_point = rs.getInt("point");			
			
			int after_point = point + befor_point;			
			
			pstmt = conn.prepareStatement("update pointment set point=?, use_point=?, info=?, pdate=?, total_point=? where id=?" );
			pstmt.setString(6, article.getId());
			pstmt.setInt(1, after_point);
			pstmt.setInt(2, article.getUse_point());
			pstmt.setString(3, article.getInfo());	
			pstmt.setTimestamp(4, article.getPdate());
			pstmt.setInt(5, article.getTotal_point());		
			pstmt.executeUpdate(); 			
			
//			*** test ::: DB insert ***
			System.out.println("PayDBBean Update완료!");
			
			
	
			pstmt = conn.prepareStatement("select * from pointment where id=? order by pdate desc");
			pstmt.setString(1, article.getId());
			rs = pstmt.executeQuery();

			
			if(rs.next()){
			
				
			PointListDataBean member = new PointListDataBean();
			member.setId(rs.getString("id"));
			member.setPoint(point);
			member.setUse_point(rs.getInt("use_point"));
			member.setInfo(rs.getString("info"));
			member.setReg_date(rs.getTimestamp("pdate"));
			
			

			
			// 저장된 결과를 pointlist DB에 insert
			pstmt = conn.prepareStatement("insert into pointlist(num,id,point,use_point,info,parkname,pdate)"+
					" values (pointlist_num.NEXTVAL,?,?,?,?,?,?)");
			pstmt.setString(1, member.getId());
			pstmt.setInt(2, member.getPoint());
			pstmt.setInt(3, member.getUse_point());
			pstmt.setString(4, member.getInfo());
			pstmt.setString(5, member.getParking_name());
			pstmt.setTimestamp(6, member.getReg_date());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("UPDATE pointlist SET parkname = '-' WHERE id=? and parkname is null");
			pstmt.setString(1, member.getId());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("select total_point from pointment where id='"+ id +"'");
			rs = pstmt.executeQuery();
			if(rs.next()){
			int total_point = rs.getInt("total_point");
			
			pstmt = conn.prepareStatement("select grade from members where id='"+id+"'");
			rs = pstmt.executeQuery();
			if(rs.next()){
				grade = rs.getString("grade");
								
				if(0 <= total_point && total_point < 30000){
					grade = "일반";
				}else if(30000 <= total_point && total_point <50000){
					grade = "실버";
				}else if(50000 <= total_point && total_point <100000){
					grade = "골드";
				}else if(100000 <= total_point){
					grade = "VIP";
				}
				
				pstmt = conn.prepareStatement("update members set grade=? where id='"+id+"'");
				pstmt.setString(1, grade);
				pstmt.executeUpdate();
				
				conn.commit();
				
			}
			
//			*** test ::: DB insert ***
			System.out.println("PayDBBean pointlist 테이블에 저장 완료!");
			}
			}
			}
		}catch(Exception e){
			conn.rollback();
			e.printStackTrace();
		}finally{
			if(rs!=null)try {rs.close();}catch(Exception e){}
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
	
//	main.jsp ::: point 값
	public int getPoint(String memId)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int point = 0;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select point from pointment where id=?" );
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				point = rs.getInt("point");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(Exception e){}
			if(pstmt != null) try{pstmt.close();}catch(Exception e){}
			if(conn != null) try{conn.close();} catch(Exception e){}
		}
		return point;
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
