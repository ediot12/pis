package mvc.review;

import java.sql.*;
import java.util.*;


import mvc.review.ReviewDataBean;



public class ReviewDBBean {
	
	private static ReviewDBBean instance = new ReviewDBBean();
	public static ReviewDBBean getInstance(){
		return instance;
	}
	private ReviewDBBean(){
		
	}
	private Connection getConnection() throws Exception{
		String jdbcDriver="jdbc:apache:commons:dbcp:/pool";         
        return DriverManager.getConnection(jdbcDriver);
	}
	
	//writePro.jsp
	public void insertArticle(ReviewDataBean article)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int num=article.getNum();
		int number=0;
		String sql="";
		try{
			conn=getConnection();
			
			
			sql = "insert into Review(num,writer,subject, readcount,content,score)" + "values(Review_num.NEXTVAL,?,?,?,?,?)";
			  
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getSubject());
			pstmt.setInt(3, article.getReadcount());
			pstmt.setString(4, article.getContent());
			pstmt.setInt(5, article.getScore());
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
			pstmt = conn.prepareStatement("select count(*) from Review");
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
			pstmt = conn.prepareStatement("select num,writer,readcount,subject,content,regdt,score, rownum r from Review where rownum >= ? and rownum <= ? order by num desc");
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				articleList = new ArrayList(end);
				do{
					ReviewDataBean article = new ReviewDataBean();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setReadcount(rs.getInt("readcount"));
					article.setSubject(rs.getString("subject"));
					article.setContent(rs.getString("content"));
					article.setRegdt(rs.getTimestamp("regdt"));
					article.setScore(rs.getInt("score"));
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
	
	public ReviewDataBean getArticle(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewDataBean article = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("update Review set readcount=readcount+1 where num = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement("select * from Review where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article = new ReviewDataBean();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setReadcount(rs.getInt("readcount"));
				article.setSubject(rs.getString("subject"));
				article.setContent(rs.getString("content"));
				article.setRegdt(rs.getTimestamp("regdt"));
				article.setScore(rs.getInt("score"));
			
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
	public ReviewDataBean updateGetArticle(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewDataBean article = null;
		try{
			conn = getConnection();
			
			pstmt = conn.prepareStatement(
					"select * from Review where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				article = new ReviewDataBean();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setSubject(rs.getString("subject"));
				article.setRegdt(rs.getTimestamp("regdt"));
				article.setReadcount(rs.getInt("readcount"));
				article.setContent(rs.getString("content"));
				article.setScore(rs.getInt("score"));
			
				
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
	
	public int updateArticle(ReviewDataBean article)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		int a = 1;

		String sql = "";
		int x = -1;
		
		try{
			conn = getConnection();
			
			if(a != 0){
			pstmt = conn.prepareStatement(
					"update Review set writer=?, subject=?, content=?, score=? where num=?");
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getSubject());
			pstmt.setString(3, article.getContent());
			pstmt.setInt(4, article.getScore());
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
				pstmt = conn.prepareStatement("delete from Review where num = ?");
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

	


