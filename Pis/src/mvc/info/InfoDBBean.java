package mvc.info;

import java.sql.*;
import java.util.*;


import mvc.info.*;
import mvc.review.ReviewDataBean;


public class InfoDBBean {

	
	private static InfoDBBean instance = new InfoDBBean();

	public static InfoDBBean getInstance() {
		return instance;
	}
 
	private InfoDBBean() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	//writePro.jsp 글작성 
		public void insertArticle(InfoDataBean article)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int num=article.getNum();
			int number=0;
			String sql="";
			try{
				conn=getConnection();
				
				
				sql = "insert into info(num,writer,subject,content,bfile,zipcode,address)" + "values(info_num.NEXTVAL,?,?,?,?,?,?)";
				  
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, article.getWriter());
				pstmt.setString(2, article.getSubject());
				pstmt.setString(3, article.getContent());
				pstmt.setString(4, article.getBfile());
				pstmt.setString(5, article.getZipcode());
				pstmt.setString(6, article.getAddress());
				pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	        if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	        if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
		}
		
		//해당 테이블에 저장된 모든 개수 = 전체 DB에 입력된 행의 수가 필요하다!!
		public int getArticleCount() throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x= 0;
			
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*) from info");
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
		
		//해당 id 로 작성한 글 목록 ~
		public List getArticles(int start, int end, String writer)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List articleList = null;
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement("select num,writer,subject,content,regdt,address,r "+
						"from (select num,writer,subject,content,regdt,address,rownum r "+
						"from (select num,writer,subject,content,regdt,address from info order by num desc ) order by num desc ) where r >= ? and r <= ? ");
				
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				pstmt.setString(3, writer);
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					articleList = new ArrayList(end);
					do{
						InfoDataBean article = new InfoDataBean();
						article.setNum(rs.getInt("num"));
						article.setWriter(rs.getString("writer"));
						article.setSubject(rs.getString("subject"));
						article.setContent(rs.getString("content"));
						article.setRegdt(rs.getTimestamp("regdt"));
						article.setAddress(rs.getString("address"));
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
		
		//db에 작성된 한줄의 글 내용 보기
		public InfoDataBean getArticle(int num) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			InfoDataBean article = null;
			try{
				conn = getConnection();
				/*pstmt = conn.prepareStatement("update info set readcount=readcount+1 where num = ?");
				pstmt.setInt(1, num);
				pstmt.executeUpdate();*/

				pstmt = conn.prepareStatement("select * from info where num = ?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					article = new InfoDataBean();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setSubject(rs.getString("subject"));
					article.setContent(rs.getString("content"));
					article.setZipcode(rs.getString("zipcode"));
					article.setAddress(rs.getString("address"));
					article.setRegdt(rs.getTimestamp("regdt"));
					article.setBfile(rs.getString("bfile"));
				
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
		
		// 우편번호 주소 검색
		public Vector zipcodeRead(String area4)  {
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        Vector vecList = new Vector();
	        
	        try {
	            con = getConnection();
	            String strQuery = "select * from zipcode where area4 like '"+area4+"%'";
	            pstmt = con.prepareStatement(strQuery);
	            rs = pstmt.executeQuery();
	            while(rs.next()){
	                ZipcodeBean tempZipcode = new ZipcodeBean();
	                tempZipcode.setZipcode(rs.getString("zipcode"));
	                tempZipcode.setArea1(rs.getString("area1"));
	                tempZipcode.setArea2(rs.getString("area2"));
	                tempZipcode.setArea3(rs.getString("area3"));
	                tempZipcode.setArea4(rs.getString("area4"));
	                vecList.addElement(tempZipcode);
	            }

	        }catch(Exception ex) {
	            System.out.println("Exception" + ex);
	        }finally {
	            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (con != null) try { con.close(); } catch(SQLException ex) {}
	        }
	        return vecList;
	    }
		

}
