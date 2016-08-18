package board;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import chart.jdbcUtil;

public class QuestionDBBean {
	public static QuestionDBBean instance = new QuestionDBBean();
	public static QuestionDBBean getInstance(){ return instance; }
	private QuestionDBBean(){}
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
			return DriverManager.getConnection(jdbcDriver);
	}
	// 글쓰기 insert 
	public void insertArticle(QuestionDataBean article)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int num=article.getNum();
		int number=0;
		String sql="";
		try{
			conn=getConnection();
			
			
			sql = "insert into question(num,writer,subject,content,kind,checked)" + "values(question_num.NEXTVAL,?,?,?,?,?)";
			  
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getSubject());
			pstmt.setString(3, article.getContent());
			pstmt.setString(4, article.getKind());
			pstmt.setString(5, article.getChecked());
			pstmt.executeUpdate();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
        if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
        if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	//Question.jsp : 페이징을 위해서 전체 DB에 입력된 행의 수가 필요하다!!
			public int getArticleCount() throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				int x = 0;
				try{
					conn = getConnection();
					pstmt = conn.prepareStatement("select count(*) from Question");
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
			
			//Question.jsp ==> Paging!!! DB로부터 여러행을 결과로 받는다.
			public List getArticles(int start, int end) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List articleList = null;
				try{
					conn = getConnection();
					pstmt = conn.prepareStatement("select num,"
							+ "kind,subject,checked, regdt, content, r  " +
					        "from (select num,kind,subject,checked, regdt, content, rownum r " +
					        "from (select num,kind,subject,checked, regdt, content " +
					        "from Question order by num desc) order by num desc) where r >= ? and r <= ?");
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
					
					if(rs.next()){
						articleList = new ArrayList(end);
						do{
							QuestionDataBean qdb = new QuestionDataBean();
							qdb.setNum(rs.getInt("num"));
							qdb.setKind(rs.getString("kind"));
							qdb.setSubject(rs.getString("subject"));
							qdb.setChecked(rs.getString("checked"));
							qdb.setRegdt(rs.getTimestamp("regdt"));
							qdb.setContent(rs.getString("content"));
							articleList.add(qdb);
							
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
			public List getArticles(int start, int end, String writer) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List articleList = null;
				try{
					conn = getConnection();
					pstmt = conn.prepareStatement(
							"select num,writer,subject,content,regdt,checked,kind, rownum from question where rownum >= ? and rownum <= ? and writer =? order by num desc");
							/*"select num,"
							+ "kind,subject,checked, regdt, content, r  " +
					        "from (select num,kind,subject,checked, regdt, content, rownum r " +
					        "from (select num,kind,subject,checked, regdt, content " +
					        "from Question order by num desc) order by num desc) where r >= ? and r <= ? and writer = ?");*/
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					pstmt.setString(3, writer);
					rs = pstmt.executeQuery();
					
					if(rs.next()){
						articleList = new ArrayList(end);
						do{
							QuestionDataBean qdb = new QuestionDataBean();
							qdb.setNum(rs.getInt("num"));
							qdb.setWriter(rs.getString("writer"));
							qdb.setKind(rs.getString("kind"));
							qdb.setSubject(rs.getString("subject"));
							qdb.setChecked(rs.getString("checked"));
							qdb.setRegdt(rs.getTimestamp("regdt"));
							qdb.setContent(rs.getString("content"));
							articleList.add(qdb);
							
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
			
			//Question.jsp ::::::::::::::: search : 검색 만들기
				public int getArticleCount(int n, String search) throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					
					int x = 0;
					String[] search_name = {"회원정보", "예약", "취소", "환불", "기타"};
					try{
						conn = getConnection();
						
						pstmt = conn.prepareStatement("select count(*) from Question where kind='"+search_name[n]+"' and subject like '%"+search+"%'");
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
				//Question.jsp ::::::::::::: search : 검색후 결과갑 뽑기
				public List getArticles(int start, int end, int n, String search) throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					List articleList = null;
					
					String[] search_name = {"회원정보", "예약", "취소", "환불", "기타"};
					try{
						conn = getConnection();
						String sql="select num,writer,kind,subject,checked, regdt, content, r  " +
					            "from (select num,writer,kind,subject,checked, regdt, content, rownum r " +
					            "from (select * from Question order by num desc)"
					            + "where kind='"+search_name[n]+"' and subject like '%"+search+"%' order by num desc) where r >= ? and r <= ? ";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, start);
						pstmt.setInt(2, end);
						rs = pstmt.executeQuery();
						
						if(rs.next()){
							articleList = new ArrayList(end);
							do{
								QuestionDataBean qdb = new QuestionDataBean();
								qdb.setNum(rs.getInt("num"));
								qdb.setWriter(rs.getString("writer"));
								qdb.setKind(rs.getString("kind"));
								qdb.setSubject(rs.getString("subject"));
								qdb.setChecked(rs.getString("checked"));
								qdb.setRegdt(rs.getTimestamp("regdt"));
								qdb.setContent(rs.getString("content"));
								articleList.add(qdb);
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
				//QuestionContent.jsp : DB로부터 한줄의 데이터를 가져온다.
				public QuestionDataBean getArticle(int num) throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					QuestionDataBean qdb = null;
					try{
						conn = getConnection();
						pstmt = conn.prepareStatement("select * from Question where num = ?");
						pstmt.setInt(1, num);
						rs = pstmt.executeQuery();
						
						if(rs.next()){
							qdb = new QuestionDataBean();
							qdb.setNum(rs.getInt("num"));
							qdb.setWriter(rs.getString("writer"));
							qdb.setKind(rs.getString("kind"));
							qdb.setSubject(rs.getString("subject"));
							qdb.setChecked(rs.getString("checked"));
							qdb.setRegdt(rs.getTimestamp("regdt"));
							qdb.setContent(rs.getString("content"));
						}
					}catch(Exception e){ e.printStackTrace(); }
					finally{
						jdbcUtil.close(rs);
						jdbcUtil.close(pstmt);
						jdbcUtil.close(conn);
					}
					return qdb;
				}
				
}
