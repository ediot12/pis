package member;

import java.util.ArrayList;
import java.util.List;

import board.NoticeDataBean;

import java.sql.*;

import chart.jdbcUtil;

public class MemberDBBean {
	public static MemberDBBean instance = new MemberDBBean();
	public static MemberDBBean getInstance(){ return instance; }
	private MemberDBBean(){}
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
			return DriverManager.getConnection(jdbcDriver);
	}
	
	public List getArticles(int start, int end) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select m.id, m.passwd, m.name, m.phone, m.email, m.resident, m.upload, m.reg_date, m.discount, m.grade, m.checked, l.point, rownum  " +
			        "from members m left outer join pointment l on (m.id=l.id) where rownum>= ? and rownum<= ? order by m.name asc");
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery(); 
			
			if(rs.next()){
				articleList = new ArrayList(end);
				do{
					MemberDataBean mdb = new MemberDataBean();
					mdb.setId(rs.getString("id"));
					mdb.setPasswd(rs.getString("passwd"));
					mdb.setName(rs.getString("name"));
					mdb.setPhone(rs.getString("phone"));
					mdb.setEmail(rs.getString("email"));
					mdb.setResident(rs.getString("resident"));
					mdb.setUpload(rs.getString("upload"));
					mdb.setReg_date(rs.getTimestamp("reg_date"));
					mdb.setDiscount(rs.getString("discount"));
					mdb.setGrade(rs.getString("grade"));
					mdb.setChecked(rs.getString("checked"));
					mdb.setPoint(rs.getInt("point"));
					articleList.add(mdb);
					
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
	public int getArticleCount() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from Members");
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
	//member.jsp ::::::::::::::: search : 검색 만들기
	public int getArticleCount(int n, String search) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		String[] search_name = {"id", "name", "resident"};
		try{
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select count(*) from Members where "+search_name[n]+" like '%"+search+"%'");
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
	//member.jsp ::::::::::::: search : 검색후 결과갑 뽑기
	public List getArticles(int start, int end, int n, String search) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList = null;
		
		String[] search_name = {"m.id", "m.name", "m.resident"};
		try{
			conn = getConnection();
			String sql="select m.id, m.passwd, m.name, m.phone, m.email, m.resident, m.upload, m.reg_date, m.discount, m.grade, m.checked, l.point, rownum  " +
			        "from members m left outer join pointment l on (m.id=l.id) where rownum>= ? and rownum<= ? and "+search_name[n]+" like '%"+search+"%' order by m.name desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				articleList = new ArrayList(end);
				do{
					MemberDataBean mdb = new MemberDataBean();
					mdb.setId(rs.getString("id"));
					mdb.setPasswd(rs.getString("passwd"));
					mdb.setName(rs.getString("name"));
					mdb.setPhone(rs.getString("phone"));
					mdb.setEmail(rs.getString("email"));
					mdb.setResident(rs.getString("resident"));
					mdb.setUpload(rs.getString("upload"));
					mdb.setReg_date(rs.getTimestamp("reg_date"));
					mdb.setDiscount(rs.getString("discount"));
					mdb.setGrade(rs.getString("grade"));
					mdb.setChecked(rs.getString("checked"));
					mdb.setPoint(rs.getInt("point"));
					articleList.add(mdb);
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
	
	public MemberDataBean getMember(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDataBean mdb = null;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select m.id, m.passwd, m.name, m.phone, m.zipcode, m.address, m.email, m.resident,"
					+ "m.upload, m.reg_date, m.discount, m.grade, m.checked, l.point from members m, pointment l where m.id=?" );
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				mdb = new MemberDataBean();
				mdb.setId(rs.getString("id"));
				mdb.setPasswd(rs.getString("passwd"));
				mdb.setName(rs.getString("name"));
				mdb.setPhone(rs.getString("phone"));
				mdb.setZipcode(rs.getString("zipcode"));
				mdb.setAddress(rs.getString("address"));
				mdb.setEmail(rs.getString("email"));
				mdb.setResident(rs.getString("resident"));
				mdb.setUpload(rs.getString("upload"));
				mdb.setReg_date(rs.getTimestamp("reg_date"));
				mdb.setDiscount(rs.getString("discount"));
				mdb.setGrade(rs.getString("grade"));
				mdb.setChecked(rs.getString("checked"));
				mdb.setPoint(rs.getInt("point"));
			}
		}catch(Exception e){ e.printStackTrace(); }
		finally{
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}
		return mdb;
	}
	public void updateArticle(MemberDataBean mdb) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		int x = -1;
		try{
			conn = getConnection();
			System.out.println(mdb.getId());
			sql = "update members set checked = '거주자 승인'";
			sql += "where id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mdb.getId());
			pstmt.executeUpdate();

		}catch(Exception e){ e.printStackTrace(); }
		finally{
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}

	}
}
 