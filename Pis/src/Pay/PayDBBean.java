package Pay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.BoardDataBean;
import chart.jdbcUtil;

public class PayDBBean {
	public static PayDBBean instance = new PayDBBean();
	public static PayDBBean getInstance(){ return instance;}
	private PayDBBean(){}
	
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
			return DriverManager.getConnection(jdbcDriver);
	} 
	
//	����Ʈ���� ���� db�� ����
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
			
			System.out.println("db�� insert");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(Exception e){}
			if(conn != null) try{conn.close();} catch(Exception e){}
		}		
	}

//	���� �� ����Ʈ ���  ��볻�� db�� ����
	public void InsertPoint(PayDataBean result) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into Pointment values(?,?,?,?,?)");
			
			pstmt.setString(1, result.getId());
			pstmt.setInt(2, result.getPoint());
			pstmt.setInt(3, result.getUse_point());
			pstmt.setString(4, result.getInfo());
			pstmt.setTimestamp(5, result.getPdate());
			pstmt.executeUpdate();
			
			System.out.println("point ����� pointment table�� ����");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(Exception e){}
			if(conn != null) try{conn.close();} catch(Exception e){}
		}		
	}
	
//	�����ܿ�����Ʈ�� ������(reserv_pointment���� ���)
	public int selectPoint(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int	point = 0;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select point from pointment where id=? order by pdate desc");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				point = rs.getInt("point");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null) try{rs.close();} catch(Exception e){}
			if(pstmt != null) try{pstmt.close();}catch(Exception e){}
			if(conn != null) try{conn.close();} catch(Exception e){}
		}	
		
		return point;
		
	}
	
//	point ��� �� ���� �ܿ� point db�� update
	public int UpdatePoint(String id, int poin)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("update pointment set point=? where id=? order by pdate desc");
			pstmt.setInt(1, poin);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			
			
		}catch (Exception e){
			
		}
		
		return poin;
		
	}
	
//	id�� ���޿�� ���� db���� ������ 
	public PaymentDataBean getId(PaymentDataBean article)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select id, pay from reservpark");
			pstmt.executeQuery();
			
			if(rs.next()){
				article.setId(rs.getString("id"));
				article.setPay(rs.getInt("pay"));
			}
			
			
		}catch(Exception e){
			
		}
		
		return article;
	}
	

	
}
