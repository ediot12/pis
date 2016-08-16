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
	
	//Question.jsp : ����¡�� ���ؼ� ��ü DB�� �Էµ� ���� ���� �ʿ��ϴ�!!
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
			
			//Question.jsp ==> Paging!!! DB�κ��� �������� ����� �޴´�.
			public List getArticles(int start, int end) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List articleList = null;
				try{
					conn = getConnection();
					pstmt = conn.prepareStatement("select num,"
							+ "kind,subject,checked, regdt, content, r  " +
					        "from (select num,writer,kind,subject,checked, regdt, content, rownum r " +
					        "from (select num,writer,kind,subject,checked, regdt, content " +
					        "from Question order by num desc) order by num desc) where r >= ? and r <= ? ");
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
			
			//Question.jsp ::::::::::::::: search : �˻� �����
				public int getArticleCount(int n, String search) throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					
					int x = 0;
					String[] search_name = {"ȸ������", "����", "���", "ȯ��", "��Ÿ"};
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
				//Question.jsp ::::::::::::: search : �˻��� ����� �̱�
				public List getArticles(int start, int end, int n, String search) throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					List articleList = null;
					
					String[] search_name = {"ȸ������", "����", "���", "ȯ��", "��Ÿ"};
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
				//QuestionContent.jsp : DB�κ��� ������ �����͸� �����´�.
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