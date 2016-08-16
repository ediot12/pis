package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import chart.jdbcUtil;

public class FAQDBBean {
	public static FAQDBBean instance = new FAQDBBean();
	public static FAQDBBean getInstance(){ return instance; }
	private FAQDBBean(){}
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
			return DriverManager.getConnection(jdbcDriver);
	}
	
	//FAQ.jsp : ����¡�� ���ؼ� ��ü DB�� �Էµ� ���� ���� �ʿ��ϴ�!!
		public int getArticleCount() throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int x = 0;
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*) from faq");
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
		
		//FAQ.jsp ==> Paging!!! DB�κ��� �������� ����� �޴´�.
		public List getArticles(int start, int end) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List articleList = null;
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement("select num,kind,subject,content, r  " +
				        "from (select num,kind,subject,content, rownum r " +
				        "from (select num,kind,subject,content " +
				        "from faq order by num desc) order by num desc) where r >= ? and r <= ? ");
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs = pstmt.executeQuery();
					
				if(rs.next()){
					articleList = new ArrayList(end);
					do{
						FAQDataBean fdb = new FAQDataBean();
						fdb.setNum(rs.getInt("num"));
						fdb.setKind(rs.getString("kind"));
						fdb.setSubject(rs.getString("subject"));
						fdb.setContent(rs.getString("content"));
						articleList.add(fdb);
						
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
		
		//FAQ.jsp ::::::::::::::: search : �˻� �����
			public int getArticleCount(int n, String search) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				int x = 0;
				String[] search_name = {"ȸ������", "����", "���", "ȯ��"};
				try{
					conn = getConnection();
					
					pstmt = conn.prepareStatement("select count(*) from faq where kind='"+search_name[n]+"' and subject like '%"+search+"%'");
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
			//FAQ.jsp ::::::::::::: search : �˻��� ����� �̱�
			public List getArticles(int start, int end, int n, String search) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List articleList = null;
				
				String[] search_name = {"ȸ������", "����", "���", "ȯ��"};
				try{
					conn = getConnection();
					String sql="select num,kind,subject,content,r  " +
				            "from (select num,kind,subject,content, rownum r " +
				            "from (select * from faq order by num desc)"
				            + "where kind='"+search_name[n]+"' and subject like '%"+search+"%' order by num desc) where r >= ? and r <= ? ";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
					rs = pstmt.executeQuery();
					
					if(rs.next()){
						articleList = new ArrayList(end);
						do{
							FAQDataBean fdb = new FAQDataBean();
							fdb.setNum(rs.getInt("num"));
							fdb.setKind(rs.getString("kind"));
							fdb.setSubject(rs.getString("subject"));
							fdb.setContent(rs.getString("content"));
							articleList.add(fdb);
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
			
			//FAQUpdateForm.jsp : �������� ������ �����͸� ������ ��.
			public FAQDataBean updateGetArticle(int num) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				FAQDataBean fdb = null;
				try{
					conn = getConnection();
					
					pstmt = conn.prepareStatement("select * from faq where num = ?");
					pstmt.setInt(1, num);
					rs = pstmt.executeQuery();
					if(rs.next()){
						fdb = new FAQDataBean();
						fdb.setNum(rs.getInt("num"));
						fdb.setKind(rs.getString("kind"));
						fdb.setSubject(rs.getString("subject"));
						fdb.setContent(rs.getString("content"));			
					}
				}catch(Exception e){ e.printStackTrace(); }
				finally{
					jdbcUtil.close(rs);
					jdbcUtil.close(pstmt);
					jdbcUtil.close(conn);
				}
				return fdb;
			}
			//FAQUpdatePro.jsp : ���� �����͸� �����ϴ� �޼ҵ�
			public void updateArticle(FAQDataBean fdb) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				String sql = "";
				int x = -1;
				try{
					conn = getConnection();
					
					sql = "update faq set kind=?, subject = ?";
					sql += ", content = ? where num=?";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, fdb.getKind());
					pstmt.setString(2, fdb.getSubject());
					pstmt.setString(3, fdb.getContent());
					pstmt.setInt(4,  fdb.getNum());
					pstmt.executeUpdate();

				}catch(Exception e){ e.printStackTrace(); }
				finally{
					jdbcUtil.close(rs);
					jdbcUtil.close(pstmt);
					jdbcUtil.close(conn);
				}

			}
			//�������� noticewrite.jsp
			public void insertArticle(FAQDataBean fdb) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				int num = fdb.getNum();
				int number = 0;
				
				try{
					conn = getConnection();
					pstmt = conn.prepareStatement("select max(num) from notice");
					rs = pstmt.executeQuery();
					
					if(rs.next()) number = rs.getInt(1)+1;
					else number = 1;
						
					//������ �ۼ�
					String sql = "insert into faq(num, kind, subject, ";
					sql += "content) values (faq_num.NEXTVAL,?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, fdb.getKind());
					pstmt.setString(2, fdb.getSubject());
					pstmt.setString(3, fdb.getContent());
					pstmt.executeUpdate();
					
				}catch(Exception e){ e.printStackTrace(); }
				finally{
					jdbcUtil.close(rs);
					jdbcUtil.close(pstmt);
					jdbcUtil.close(conn);
				}
			}
			//FAQDelete.jsp
			public void deleteArticle(int num) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try{
					conn = getConnection();
					
					pstmt=conn.prepareStatement("delete from faq where num = ? ");
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					
				}catch(Exception e){ e.printStackTrace(); }
				finally{
					jdbcUtil.close(rs);
					jdbcUtil.close(pstmt);
					jdbcUtil.close(conn);
				}
			}
}
