package chart;

import java.sql.*;
import java.util.Date;
import java.text.*;
import javax.sql.*;

import chart.ChartDataBean;

import javax.naming.*;
import java.util.*;

public class ChartDBBean {
	public static ChartDBBean instance = new ChartDBBean();
	public static ChartDBBean getInstance(){ return instance; }
	private ChartDBBean(){}
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
			return DriverManager.getConnection(jdbcDriver);
	}
	
	//main.jsp -> ���� �湮�� �߰�!!
	public void setVisitTotalCount(ChartDataBean article) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into chart_visit values (sysdate, ?)");
			pstmt.setString(1, article.getC_ip());
			pstmt.executeUpdate();
			
		}catch(Exception e){ e.printStackTrace(); }
		finally{
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}
	}
	
	//���� �� �湮�� �� ���
	public int getVisitTotalCount() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from chart_visit");
			rs = pstmt.executeQuery();
			
			if(rs.next())x = rs.getInt(1);
		}catch(Exception e){ e.printStackTrace(); }
		finally{
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}
		return x;
	}
	
	//���� ��¥ �湮�� �� ���
	public int getVisitTodayCount() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from chart_visit where substr(to_char(c_date),1,9)=to_date(sysdate, 'yy/MM/dd')");
			rs = pstmt.executeQuery();
			
			if(rs.next()) x = rs.getInt(1);
		}catch(Exception e){ e.printStackTrace(); }
		finally{
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}
		return x;
	}
	
	//main.jsp -> ������ ���� ������ �����ǰ� �ִ� �� check!!(������ �湮�� �߰��ϱ����� if��)
	public int TodayIpCheck(String c_ip) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbc_ip="";
		int x =-1;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select c_date from chart_visit where c_ip=?");
			pstmt.setString(1, c_ip);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				pstmt = conn.prepareStatement("select * from chart_visit where substr(to_char(c_date),1,9)=to_date(sysdate, 'yy/MM/dd') and c_ip=?");
				pstmt.setString(1, c_ip);
				rs = pstmt.executeQuery();
				if(rs.next()) x=1;//�ش糯¥�� �ش�����Ǹ� ����
				
				else x=0; //�ش� ��¥���� ����.
			}else x= -1; //�ش� ������ ����.
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(rs != null) try{ rs.close(); } catch(SQLException e){}
			if(pstmt != null) try{pstmt.close(); } catch(SQLException e){}
			if(conn != null) try{ conn.close(); } catch(SQLException e){}
		}
		return x;
	}
	
	//���� ��¥�� ��� �湮�ߴ��� ī��Ʈ!!
	public int Chart_days(String c_date) throws Exception{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int x = 0;
			
		try{
			conn = getConnection();
			
			//pstmt = conn.prepareStatement("select count(*) from chart_days where to_date('?', 'yy/MM/dd')=substr(to_char(c_date),1,9)");
			//"Select count(*) from chart_days where to_char(c_date, 'YY/MM/DD') between '?' and '?' group by to_char(c_date, 'dd') order by to_char(c_date, 'dd') asc") ;
			//pstmt.setString(1, c_date);
			//rs = pstmt.executeQuery();
			
			stmt = conn.createStatement();
			String sql = "select count(*) from chart_visit where substr(to_char(c_date),1,9)=substr(to_char('"+c_date+"'),3,9)";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				x = rs.getInt(1);
			}
		}catch(Exception e){ e.printStackTrace(); }
		finally{
			jdbcUtil.close(rs);
			jdbcUtil.close(stmt);
			jdbcUtil.close(conn);
		}
		return x;
	}
	public int Chart_mon(String c_date) throws Exception{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int x = 0;
			
		try{
			conn = getConnection();
			
			stmt = conn.createStatement();
			String sql = "select count(*) from chart_visit where substr(to_char(c_date),1,5)=substr(to_char('"+c_date+"'),3,7)";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				x = rs.getInt(1);
			}
		}catch(Exception e){ e.printStackTrace(); }
		finally{
			jdbcUtil.close(rs);
			jdbcUtil.close(stmt);
			jdbcUtil.close(conn);
		}
		return x;
	}
}
