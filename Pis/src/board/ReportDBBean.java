package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import chart.jdbcUtil;

public class ReportDBBean {
	public static ReportDBBean instance = new ReportDBBean();
	public static ReportDBBean getInstance(){ return instance; }
	private ReportDBBean(){}
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
			return DriverManager.getConnection(jdbcDriver);
	}
	
	//Report.jsp : 페이징을 위해서 전체 DB에 입력된 행의 수가 필요하다!!
	public int getArticleCount() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from Report");
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
	
	//Report.jsp ==> Paging!!! DB로부터 여러행을 결과로 받는다.
	public List getArticles(int start, int end) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select num,kind, writer,subject,regdt, content, r  " +
			        "from (select num,kind, writer,subject,regdt, content, rownum r " +
			        "from (select num,kind, writer,subject,regdt, content " +
			        "from Report order by num desc) order by num desc) where r >= ? and r <= ? ");
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				articleList = new ArrayList(end);
				do{
					ReportDataBean rdb = new ReportDataBean();
					rdb.setNum(rs.getInt("num"));
					rdb.setKind(rs.getString("kind"));
					rdb.setWriter(rs.getString("writer"));
					rdb.setSubject(rs.getString("subject"));
					rdb.setRegdt(rs.getTimestamp("regdt"));
					rdb.setContent(rs.getString("content"));
					articleList.add(rdb);
					
				}while(rs.next());
			}
		}catch(Exception e){ e.printStackTrace(); }
		finally{
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}
		return articleList;
	}
	
	//Report.jsp ::::::::::::::: search : 검색 만들기
		public int getArticleCount(int n, String search) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int x = 0;
			String[] search_name = {"회원정보", "예약", "취소", "환불", "기타"};
			try{
				conn = getConnection();
				
				pstmt = conn.prepareStatement("select count(*) from Report where kind='"+search_name[n]+"' and subject like '%"+search+"%'");
				rs = pstmt.executeQuery();
				
				if(rs.next()) x= rs.getInt(1);
				
			}catch(Exception e){ e.printStackTrace(); }
			finally{
				jdbcUtil.close(rs);
				jdbcUtil.close(pstmt);
				jdbcUtil.close(conn);
			}
			return x;
		}
		//Report.jsp ::::::::::::: search : 검색후 결과갑 뽑기
		public List getArticles(int start, int end, int n, String search) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List articleList = null;
			
			String[] search_name = {"회원정보", "예약", "취소", "환불", "기타"};
			try{
				conn = getConnection();
				String sql="select num,kind, writer,subject,regdt, content, r  " +
			            "from (select num,kind, writer,subject,regdt, content, rownum r " +
			            "from (select * from Report order by num desc)"
			            + "where kind='"+search_name[n]+"' and subject like '%"+search+"%' order by num desc) where r >= ? and r <= ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					articleList = new ArrayList(end);
					do{
						ReportDataBean rdb = new ReportDataBean();
						rdb.setNum(rs.getInt("num"));
						rdb.setKind(rs.getString("kind"));
						rdb.setWriter(rs.getString("writer"));
						rdb.setSubject(rs.getString("subject"));
						rdb.setRegdt(rs.getTimestamp("regdt"));
						rdb.setContent(rs.getString("content"));
						articleList.add(rdb);
					}while(rs.next());
				}
			}catch(Exception e){ e.printStackTrace(); }
			finally{
				jdbcUtil.close(rs);
				jdbcUtil.close(pstmt);
				jdbcUtil.close(conn);
			}
			return articleList;
		}
		//ReportContent.jsp : DB로부터 한줄의 데이터를 가져온다.
		public ReportDataBean getArticle(int num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ReportDataBean rdb = null;
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement("select * from Report where num = ?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					rdb = new ReportDataBean();
					rdb.setNum(rs.getInt("num"));
					rdb.setKind(rs.getString("kind"));
					rdb.setWriter(rs.getString("writer"));
					rdb.setSubject(rs.getString("subject"));
					rdb.setRegdt(rs.getTimestamp("regdt"));
					rdb.setContent(rs.getString("content"));
				}
			}catch(Exception e){ e.printStackTrace(); }
			finally{
				jdbcUtil.close(rs);
				jdbcUtil.close(pstmt);
				jdbcUtil.close(conn);
			}
			return rdb;
		}
		
}
