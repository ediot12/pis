package mvc.notice;

import java.sql.*;

import java.util.*;
import javax.sql.*;

import mvc.notice.PisDataBean;

import javax.naming.*;

public class PisDBBean {

	private static PisDBBean instance = new PisDBBean();
 
	public static PisDBBean getInstance() {
		return instance;
	}

	private PisDBBean() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	/*//�� �ۼ�
	public void insertArticle(PisDataBean article) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int num = article.getNum(); // �۹�ȣ
		String sql = "";
		try {
			conn = getConnection();
			
			 * pstmt=conn.prepareStatement("select max(num) from Notice"); rs =
			 * pstmt.executeQuery();
			 

			 if (rs.next()) number = rs.getInt(1) + 1; else number = 1; 

			// ������ �ۼ�
			sql = "insert into Notice(num,subject,readcount,content)" + "values(Notice_num.NEXTVAL,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getSubject());
			pstmt.setInt(2, article.getReadcount());
			pstmt.setString(3, article.getContent());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}
*/
	//  ����¡�� ���ؼ� ��ü db�� �Էµ� ������ ������ �ʿ���.
	public int getArticleCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from Notice");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
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
		return x;
	}

	// Paging!!! DB�κ��� �������� ����� �޴´�.
	public List getArticles(int start, int end) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select num,subject,readcount,content,regdt,r "+
							"from (select num,subject,readcount,content,regdt,rownum r "+
							"from (select num,subject,readcount,content,regdt from Notice order by num desc ) order by num desc ) where r >= ? and r <= ? ");

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				articleList = new ArrayList(end);
				do {
					PisDataBean article = new PisDataBean();
					article.setNum(rs.getInt("num"));
					article.setSubject(rs.getString("subject"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRegdt(rs.getTimestamp("regdt"));
					article.setContent(rs.getString("content"));
					articleList.add(article);

				} while (rs.next());

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

	//  db �� ���� ������ �����͸� �����´�.
	public PisDataBean getArticle(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PisDataBean article = null;
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("update notice set readcount=readcount+1 where num = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement("select * from Notice where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article = new PisDataBean();
				article.setNum(rs.getInt("num"));
				article.setReadcount(rs.getInt("readcount"));
				article.setSubject(rs.getString("subject"));
				article.setContent(rs.getString("content"));
				article.setRegdt(rs.getTimestamp("regdt"));
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

	/*// updateForm.jsp : �������� ������ �����͸� �����ö�.
	public PisDataBean updateGetArticle(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PisDataBean article = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from Notice where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new PisDataBean();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setReadcount(rs.getInt("readcount"));
				article.setSubject(rs.getString("subject"));
				article.setContent(rs.getString("content"));
				article.setRegdt(rs.getTimestamp("regdt"));
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

	// updatePro.jsp : ���� �����͸� �����ϴ� �޼���.
	public int updateArticle(PisDataBean article) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int a = 1;

		String sql = "";
		int x = -1;
		try {
			conn = getConnection();

			if (a != 0) {
				sql = "update Notice set writer=?,subject=?, content=? where num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, article.getWriter());
				pstmt.setString(2, article.getSubject());
				pstmt.setString(3, article.getContent());
				pstmt.setInt(4, article.getNum());
				pstmt.executeUpdate();
				x = 1;
			} else {
				x = 0;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
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

	// deletePro.jsp : ���� �����͸� �����ϴ� �޼���...
	public int deleteArticle(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int a = 1;
		int x = -1;
		try {
			conn = getConnection();
			if (a != 0) {
				pstmt = conn.prepareStatement("delete from Notice where num=?");
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				x = 1; // �ۻ���
			} else {
				x = 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

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
		return 1;

	}*/

	// search : �˻� �����
	public int getArticleCount(int n, String search) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;
		String[] column_name = { "subject", "num" };

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select count(*) from notice where " + column_name[n] + " like '%" + search + "%'");

			rs = pstmt.executeQuery();

			if (rs.next())
				x = rs.getInt(1);
		} catch (Exception ex) {
			ex.printStackTrace();
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
		return x;
	}
	
	//���� �˻� 
	public List getArticles(int start, int end, int n, String search) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList = null;
		System.out.println(n);

		String[] column_name = {  "subject", "num" };

		try {
			conn = getConnection();

			String sql = "select num,subject,regdt,content,readcount,r "
					+ "from (select num,subject,regdt,content,readcount,rownum r "
					+ "from (select num,subject,regdt,content,readcount "
					+ "from Notice order by num desc ) where " + column_name[n] + " like '%" + search
					+ "%') where r >= ? and r <= ?";
			
			System.out.println(sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				articleList = new ArrayList(end);
				do {
					PisDataBean article = new PisDataBean();
					article.setNum(rs.getInt("num"));
					article.setSubject(rs.getString("subject"));
					article.setRegdt(rs.getTimestamp("regdt"));
					article.setReadcount(rs.getInt("readcount"));
					article.setContent(rs.getString("content"));
					articleList.add(article);
				} while (rs.next());

			}

		} catch (Exception ex) {
			ex.printStackTrace();
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

		return articleList;
	}

}
