package mvc.report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.report.ReportDBBean;


public class ReportDBBean {
	private static ReportDBBean instance = new ReportDBBean();
	public static ReportDBBean getInstance(){
		return instance;
	}
	private ReportDBBean(){
		
	}
	private Connection getConnection() throws Exception{
		String jdbcDriver="jdbc:apache:commons:dbcp:/pool";         
        return DriverManager.getConnection(jdbcDriver);
	}
	
	//writePro.jsp
		public void insertArticle(ReportDataBean article)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int num=article.getNum();
			int number=0;
			String sql="";
			try{
				conn=getConnection();
				
				
				sql = "insert into Report(num,writer,subject,readcount,content,reply)" + "values(Report_num.NEXTVAL,?,?,?,?,?)";
				  
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, article.getWriter());
				pstmt.setString(2, article.getSubject());
				pstmt.setInt(3, article.getReadcount());
				pstmt.setString(4, article.getContent());
				pstmt.setString(5, article.getReply());
				
				pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	        if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	        if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
		}
		
		public int getArticleCount() throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x= 0;
			
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*) from Report");
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					x = rs.getInt(1);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException ex) {
					}
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException ex) {
					}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException ex) {
					}
			}
			return x;
		}
		
		public List getArticles(int start, int end)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List articleList = null;
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement("select num,writer,readcount,subject,content,regdt,reply, rownum r from Report where rownum >= ? and rownum <= ? order by num desc");
				
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					articleList = new ArrayList(end);
					do{
						ReportDataBean article = new ReportDataBean();
						article.setNum(rs.getInt("num"));
						article.setWriter(rs.getString("writer"));
						article.setReadcount(rs.getInt("readcount"));
						article.setSubject(rs.getString("subject"));
						article.setContent(rs.getString("content"));
						article.setRegdt(rs.getTimestamp("regdt"));
						article.setReply(rs.getString("reply"));
						articleList.add(article);
					}while(rs.next());
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException se) {
					}
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException se) {
					}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException se) {
					}
			}
			return articleList;
			}
		
		public ReportDataBean getArticle(int num) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ReportDataBean article = null;
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement("update Report set readcount=readcount+1 where num = ?");
				pstmt.setInt(1, num);
				pstmt.executeUpdate();

				pstmt = conn.prepareStatement("select * from Report where num = ?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					article = new ReportDataBean();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setReadcount(rs.getInt("readcount"));
					article.setSubject(rs.getString("subject"));
					article.setContent(rs.getString("content"));
					article.setRegdt(rs.getTimestamp("regdt"));
					article.setReply(rs.getString("reply"));
				
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException ex) {
					}
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException ex) {
					}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException ex) {
					}
			}
			return article;
		}
		
		//수정할 한줄의 데이터 가져올떄...
		public ReportDataBean updateGetArticle(int num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ReportDataBean article = null;
			try{
				conn = getConnection();
				
				pstmt = conn.prepareStatement(
						"select * from Report where num = ?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if(rs.next()){
					article = new ReportDataBean();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setSubject(rs.getString("subject"));
					article.setRegdt(rs.getTimestamp("regdt"));
					article.setReadcount(rs.getInt("readcount"));
					article.setContent(rs.getString("content"));
					article.setReply(rs.getString("reply"));
				
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			return article;
		}
		
		public int updateArticle(ReportDataBean article)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			int a = 1;

			String sql = "";
			int x = -1;
			
			try{
				conn = getConnection();
				
				if(a != 0){
				pstmt = conn.prepareStatement(
						"update Report set writer=?, subject=?, content=? where num=?");
				pstmt.setString(1, article.getWriter());
				pstmt.setString(2, article.getSubject());
				pstmt.setString(3, article.getContent());
				pstmt.setInt(5, article.getNum());
				pstmt.executeUpdate();
				x = 1;
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException ex) {
					}
				if (conn != null)
					try {
						conn.close();
			}catch(SQLException ex){}
			}
			return x;
		}
		
		public int deleteArticle(int num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			int a = 1;
			int x = -1;
			try{
				conn = getConnection();
				if(a != 0){
					pstmt = conn.prepareStatement("delete from Report where num = ?");
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					x=1;
				}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					if (pstmt != null)
						try {
							pstmt.close();
						} catch (SQLException ex) {
						}
					if (conn != null)
						try {
							conn.close();
						} catch (SQLException ex) {}
				
			}
			return x;
		}
	
	
	

}
