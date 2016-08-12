package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import board.NoticeDataBean;
import chart.jdbcUtil;

public class NoticeDBBean {
	public static NoticeDBBean instance = new NoticeDBBean();
	public static NoticeDBBean getInstance(){ return instance; }
	private NoticeDBBean(){}
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
			return DriverManager.getConnection(jdbcDriver);
	}
	
	//공지사항 noticewrite.jsp
	public void insertArticle(NoticeDataBean bdb) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int num = bdb.getNum();
		int number = 0;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select max(num) from notice");
			rs = pstmt.executeQuery();
			
			if(rs.next()) number = rs.getInt(1)+1;
			else number = 1;
				
			//쿼리를 작성
			String sql = "insert into notice(num, subject, regdt, ";
			sql += "content) values (notice_num.NEXTVAL,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bdb.getSubject());
			pstmt.setTimestamp(2, bdb.getRegdt());
			pstmt.setString(3, bdb.getContent());
			pstmt.executeUpdate();
			
		}catch(Exception e){ e.printStackTrace(); }
		finally{
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}
	}
	
	//NoticeForm.jsp : 페이징을 위해서 전체 DB에 입력된 행의 수가 필요하다!!
	public int getArticleCount() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from notice");
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
	
	//NoticeForm.jsp ==> Paging!!! DB로부터 여러행을 결과로 받는다.
	public List getArticles(int start, int end) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select num,subject,regdt,content,readcount,r  " +
			        "from (select num,subject,regdt,content,readcount,rownum r " +
			        "from (select num,subject,regdt,content,readcount " +
			        "from notice order by num desc) order by num desc) where r >= ? and r <= ? ");
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
				
			if(rs.next()){
				articleList = new ArrayList(end);
				do{
					NoticeDataBean bdb = new NoticeDataBean();
					bdb.setNum(rs.getInt("num"));
					bdb.setSubject(rs.getString("subject"));
					bdb.setRegdt(rs.getTimestamp("regdt"));
					bdb.setReadcount(rs.getInt("readcount"));
					bdb.setContent(rs.getString("content"));
					articleList.add(bdb);
					
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
	
	//search : 검색 만들기
		public int getArticleCount(int n, String search) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int x = 0;
			String[] search_name = {"writer", "subject", "content"};
			try{
				conn = getConnection();
				
				pstmt = conn.prepareStatement("select count(*) from board where "+search_name[n]+" like '%"+search+"%'");
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
		//search : 검색후 결과갑 뽑기
		public List getArticles(int start, int end, int n, String search) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List articleList = null;
			
			String[] search_name = {"writer", "subject", "content"};
			try{
				conn = getConnection();
				String sql="select num,subject,regdt,content,readcount,r  " +
			            "from (select num,subject,regdt,content,readcount,r " +
			            "from (select * from notice order by num desc)"
			            + "where "+search_name[n]+" Like '%"+search+"%' order by num desc) where r >= ? and r <= ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					articleList = new ArrayList(end);
					do{
						NoticeDataBean bdb = new NoticeDataBean();
						bdb.setNum(rs.getInt("num"));
						bdb.setSubject(rs.getString("subject"));
						bdb.setRegdt(rs.getTimestamp("regdt"));
						bdb.setReadcount(rs.getInt("readcount"));
						bdb.setContent(rs.getString("content"));
						articleList.add(bdb);
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
		//noticeContent.jsp : DB로부터 한줄의 데이터를 가져온다.
		public NoticeDataBean getArticle(int num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			NoticeDataBean article = null;
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement("update notice set readcount = readcount+1 where num = ?");
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				
				pstmt = conn.prepareStatement("select * from notice where num = ?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					article = new NoticeDataBean();
					article.setNum(rs.getInt("num"));
					article.setSubject(rs.getString("subject"));
					article.setReadcount(rs.getInt("readcount"));
					article.setContent(rs.getString("content"));
				}
			}catch(Exception e){ e.printStackTrace(); }
			finally{
				jdbcUtil.close(rs);
				jdbcUtil.close(pstmt);
				jdbcUtil.close(conn);
			}
			return article;
		}
		//noticeDelete.jsp
		public void deleteArticle(int num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try{
				conn = getConnection();
				
				pstmt=conn.prepareStatement("delete from notice where num = ? ");
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				
			}catch(Exception e){ e.printStackTrace(); }
			finally{
				jdbcUtil.close(rs);
				jdbcUtil.close(pstmt);
				jdbcUtil.close(conn);
			}
		}
		//noticeUpdateForm.jsp : 수정폼에 한줄의 데이터를 가져올 때.
		public NoticeDataBean updateGetArticle(int num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			NoticeDataBean bdb = null;
			try{
				conn = getConnection();
				
				pstmt = conn.prepareStatement("select * from notice where num = ?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if(rs.next()){
					bdb = new NoticeDataBean();
					bdb.setNum(rs.getInt("num"));
					bdb.setSubject(rs.getString("subject"));
					bdb.setRegdt(rs.getTimestamp("regdt"));
					bdb.setReadcount(rs.getInt("readcount"));
					bdb.setContent(rs.getString("content"));			
				}
			}catch(Exception e){ e.printStackTrace(); }
			finally{
				jdbcUtil.close(rs);
				jdbcUtil.close(pstmt);
				jdbcUtil.close(conn);
			}
			return bdb;
		}
		//noticeUpdatePro.jsp : 실제 데이터를 수정하는 메소드
		public void updateArticle(NoticeDataBean bdb) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "";
			int x = -1;
			try{
				conn = getConnection();
				
				sql = "update notice set subject = ?";
				sql += ", content = ? where num=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bdb.getSubject());
				pstmt.setString(2, bdb.getContent());
				pstmt.setInt(3,  bdb.getNum());
				pstmt.executeUpdate();

			}catch(Exception e){ e.printStackTrace(); }
			finally{
				jdbcUtil.close(rs);
				jdbcUtil.close(pstmt);
				jdbcUtil.close(conn);
			}

		}
		
}
