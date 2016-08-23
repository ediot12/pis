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
	
	 
//	pointlist :::  페이징을 위해 count
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

//	pointlist ::: DB로부터 여러행을 결과로 받는다.
	public List getArticles(int start, int end, String id) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List articleList=null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * from pointlist where id=?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            
            if(rs.next()){
           
	            pstmt = conn.prepareStatement(
	            "select num,id,point,use_point,info,pdate,parkname, r  " +
	            "from (select num,id,point,use_point,info,pdate,parkname,rownum r " +
	            "from (select num,id,point,use_point,info,pdate,parkname " +
	            "from pointlist order by pdate asc) order by pdate asc ) where r >= ? and r <= ? ");
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
