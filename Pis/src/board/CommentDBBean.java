package board;

import java.sql.*;
import java.util.*;

import board.CommentDataBean;
import chart.jdbcUtil;

public class CommentDBBean {   
	private static CommentDBBean instance = new CommentDBBean();
	public static CommentDBBean getInstance(){ return instance; }
	private CommentDBBean(){}
	
	private Connection getConnection() throws Exception{
		String jdbcDriver="jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	public void insertComment(CommentDataBean cdb) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = getConnection();
			
//			*** 자동COMMIT 안되게 FALSE로 지정 (오류 발생시 실행 X)
			conn.setAutoCommit(false);   
			
			pstmt = conn.prepareStatement("insert into q_comment values(?,?,?,?)");
			pstmt.setInt(1, cdb.getComment_num());
			pstmt.setInt(2, cdb.getContent_num());
			pstmt.setString(3, cdb.getCommentt());
			pstmt.setTimestamp(4, cdb.getReg_date());
			pstmt.executeUpdate();
			
			String sql = "update question set checked='답변완료' where num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cdb.getContent_num());
			pstmt.executeUpdate();
			
			conn.commit();  
			
		}catch(Exception e){ 
			conn.rollback();
			e.printStackTrace(); 
		}finally{
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}
	}
	
	public ArrayList getComments(int con_num)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList cm=null;
		try{
			conn=getConnection();
			String sql="select * from q_comment where content_num="+con_num+" order by reg_date desc";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				cm=new ArrayList();
				do{
					CommentDataBean cdb=new CommentDataBean();
					cdb.setComment_num(rs.getInt("comment_num"));
					cdb.setContent_num(rs.getInt("content_num"));
					cdb.setCommentt(rs.getString("commentt"));
					cdb.setReg_date(rs.getTimestamp("reg_date"));
					cm.add(cdb);
				}while(rs.next());
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}
		return cm;
	}
	
	public int getCommentCount(int con_num)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList cm=null;
		int count=0;
		
		try{
			conn=getConnection();
			String sql="select * from q_comment where content_num="+con_num+" order by reg_date desc";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				cm=new ArrayList();
				do{
					CommentDataBean cdb=new CommentDataBean();
					cdb.setCommentt(rs.getString("commentt"));
					cdb.setReg_date(rs.getTimestamp("reg_date"));
					cm.add(cdb);
				}while(rs.next());
				count=cm.size();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}
		return count;
	}
	public void deleteComment(int content_num, int comment_num)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			conn=getConnection();
			
//			*** 자동COMMIT 안되게 FALSE로 지정 (오류 발생시 실행 X)
			conn.setAutoCommit(false);  
			
			pstmt=conn.prepareStatement("delete from q_comment where content_num=? and comment_num=?");
			pstmt.setInt(1, content_num);
			pstmt.setInt(2, comment_num);
			pstmt.executeUpdate();
			
			String sql = "update question set checked='' where num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, content_num);
			pstmt.executeUpdate();
			
			conn.commit(); 
			
		}catch(Exception ex){
			conn.rollback();
			ex.printStackTrace();
		}finally{
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}		
	}
}
