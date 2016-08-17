package Pay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chart.jdbcUtil;

public class PointListDBBean {
	public static PointListDBBean instance = new PointListDBBean();
	public static PointListDBBean getInstance(){ return instance;}
	private PointListDBBean(){}
	
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
			return DriverManager.getConnection(jdbcDriver);
	} 
	
	 
//	ppointlist :::  페이징을 위해 count
    public int getArticleCount() throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int x=0;

        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement("select count(*) from pointlist");
            rs = pstmt.executeQuery();

            if (rs.next()) {
               x= rs.getInt(1);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return x;
    }
	
	
//	pointlist ::: 결제된내역과 point에 대한 내역을 조인해서 pointlist테이블에 값을 저장 
	public void InsertList(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// pointment와 reservpark 테이블을 조인 
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT pointment.id, pointment.point, pointment.use_point, pointment.info,"+
					"pointment. pdate,reservpark.parkname  FROM pointment, reservpark WHERE pointment.id = reservpark.id and pointment.id=?");
			
			pstmt.setString(1, id);
			pstmt.executeQuery();
			
			// 조인된 결과를 poinlistdatabean에 저장
			if(rs.next()){
				PointListDataBean member = new PointListDataBean();
				
				member.setId(rs.getString("id"));
				member.setPoint(rs.getInt("point"));
				member.setUse_point(rs.getInt("use_point"));
				member.setInfo(rs.getString("info"));
				member.setParking_name(rs.getString("parkname"));
				member.setReg_date(rs.getTimestamp("pdate"));
				
				// 저장된 결과를 pointlist DB에 insert
				pstmt = conn.prepareStatement("insert into pointlist(num,id,point,use_point,info,parkname,pdate)"+
						" values (pointlist_num.NEXTVAL,?,?,?,?,?,?)");
				pstmt.setString(1, member.getId());
				pstmt.setInt(2, member.getPoint());
				pstmt.setInt(3, member.getUse_point());
				pstmt.setString(4, member.getInfo());
				pstmt.setString(5, member.getParking_name());
				pstmt.setTimestamp(5, member.getReg_date());
				pstmt.executeUpdate();
				
				
			}
			
		}catch(Exception e){ 
			e.printStackTrace();
		}finally{
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}
		
	}
	
	
//	pointlist ::: DB로부터 여러행을 결과로 받는다.
	public List getArticles(int start, int end) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List articleList=null;
        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement(
            "select num,id,point,use_point,info,pdate,parkname r  " +
            "from (select num,id,point,use_point,info,pdate,parkname,rownum r " +
            "from (select * " +
            "from poinlist order by pdate asc) order by pdate asc ) where r >= ? and r <= ? ");
            pstmt.setInt(1, start);
    		pstmt.setInt(2, end);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                articleList = new ArrayList(end);
                do{
                  PointListDataBean article= new PointListDataBean();
  				  article.setNum(rs.getInt("num"));
  				  article.setId(rs.getString("id"));
  				  article.setPoint(rs.getInt("point"));
  				  article.setUse_point(rs.getInt("use_point"));
  				  article.setInfo(rs.getString("info"));
  				  article.setReg_date(rs.getTimestamp("pdate"));
  				  article.setParking_name(rs.getString("parkname"));
                  articleList.add(article);
		}while(rs.next());
    	}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return articleList;
    }

}
