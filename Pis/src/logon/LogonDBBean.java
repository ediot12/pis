package logon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;



public class LogonDBBean {//DB와 관련된 일을 하는 클래스: DBBean, DAO
   
	private static LogonDBBean instance = new LogonDBBean();
   
	//LogonDBBean m = LogonDBBean.getInstance();
    public static LogonDBBean getInstance() {
        return instance;
    }
   
    private LogonDBBean() {}
   
    private Connection getConnection() throws Exception {
    	String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";        
    	return DriverManager.getConnection(jdbcDriver);
    }
     
    //inputPro.jsp
    public void insertMember(LogonDataBean member) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
       
        
        try {
            conn = getConnection();
 //DriverManager.getConnection(jdbc:apache:commons:dbcp:/pool);
            pstmt = conn.prepareStatement(
            "insert into MEMBERS values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPasswd());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getPhone());
            pstmt.setString(5, member.getZipcode());
            pstmt.setString(6, member.getAddress());
            pstmt.setString(7, member.getEmail());
            pstmt.setString(8, member.getCertify());
            pstmt.setString(9, member.getResident());
            pstmt.setString(10, member.getUpload());
            pstmt.setTimestamp(11, member.getReg_date());
            pstmt.setString(12, member.getDiscount());
            pstmt.setString(13, member.getGrade());
            pstmt.executeUpdate();
           
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
     
    }
          
    public int setCertify(String id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int x = 0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("update MEMBERS set CERTIFY = ? where ID = ?");
			pstmt.setString(1, "y"); /*인증여부값을 y로 변경*/
			pstmt.setString(2, id);

			x = pstmt.executeUpdate();

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
    
    //loginPro.jsp
    public int userCheck(String id, String passwd) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;    
        ResultSet rs= null;
        String dbpasswd="";
        int x=-1;
        
        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement(
            "select passwd from MEMBERS where id = ?");
            pstmt.setString(1, id);
           
            rs= pstmt.executeQuery();

            if(rs.next()){
            	dbpasswd= rs.getString("passwd");
            	if(dbpasswd.equals(passwd))
            		x= 1; //인증 성공
            	else
            		x= 0; //비밀번호 틀림
            }else
            	x= -1;//해당 아이디 없음

        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return x;
    }
    //confirmId.jsp
    public int confirmId(String id) throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        int x=-1;//경우의 수
       
        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement(
            "select id from MEMBERS where id = ?");
            pstmt.setString(1, id);
            rs= pstmt.executeQuery();

            if(rs.next())
            	x= 1; //해당 아이디 있음
            else
            	x= -1;//해당 아이디 없음
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return x;
    }
    //findID.jsp
    public String findID(String name,String phone){
   	 Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String id="";
    
        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement(
            "select id from MEMBERS where name = ? AND phone = ?");
            pstmt.setString(1, name);
            pstmt.setString(2, phone); 
           
            rs = pstmt.executeQuery();

            if (rs.next()) {
                id = rs.getString("id");        
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        
   	return id;
   }
    
    //updatePwd
    public int updatePwd(String passwd, String passwd2, String id){
 	   Connection conn = null;
        PreparedStatement pstmt = null;
        int x = 0;
        
        try {
            conn = getConnection();
           if(passwd.equals(passwd2)){
            pstmt = conn.prepareStatement(
         		   "update MEMBERS set passwd=? where id=?");
            pstmt.setString(1, passwd);
            pstmt.setString(2, id);
            
            pstmt.executeUpdate();
            
            x=1;
           }else{ x=0;}
           } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
 	
 	
 	return x;
 }

    //findPWD
 public String findPWD(String id,String name,String phone){
    	
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String pwd="";
        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement(
            "select passwd from MEMBERS where id=? AND name = ? AND phone = ?");
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, phone); 
            
       
            rs = pstmt.executeQuery();

            if (rs.next()) {
                pwd = rs.getString("passwd");        
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
       
   	return pwd;
   } 

    //modifyForm.jsp
    public LogonDataBean getMember(String id) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LogonDataBean member=null;
        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement(
            "select * from MEMBERS where id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                member = new LogonDataBean();
                member.setId(rs.getString("id"));
                member.setPasswd(rs.getString("passwd"));
                member.setName(rs.getString("name"));
                member.setPhone(rs.getString("phone"));
                member.setZipcode(rs.getString("zipcode"));
                member.setAddress(rs.getString("address"));
                member.setEmail(rs.getString("email"));
                member.setResident(rs.getString("resident"));
                member.setUpload(rs.getString("upload"));
                member.setReg_date(rs.getTimestamp("reg_date"));    
            }
        } 
         catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return member;
    }
     
    //modifyPro.jsp
    public void updateMember(LogonDataBean member) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
      
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(
         "update MEMBERS set passwd=?, phone=?, zipcode=?, address=?, email=?, resident=?, upload=?" + " where id=?");
            pstmt.setString(1, member.getPasswd());
            pstmt.setString(2, member.getPhone());
            pstmt.setString(3, member.getZipcode());
            pstmt.setString(4, member.getAddress());
            pstmt.setString(5, member.getEmail());
            pstmt.setString(6, member.getResident());
            pstmt.setString(7, member.getUpload());
            pstmt.setString(8, member.getId());
           
            pstmt.executeUpdate();
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    }
    
    public int deleteMember(String id, String passwd) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        String dbpasswd="";
        int x=-1;
        
        try {
        	conn = getConnection();

            pstmt = conn.prepareStatement(
            "select passwd from MEMBERS where id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
           
            if(rs.next()){
            	dbpasswd= rs.getString("passwd");
            	if(dbpasswd.equals(passwd)){
            		pstmt = conn.prepareStatement(
                  "delete from MEMBERS where id=?");
                    pstmt.setString(1, id);
                    pstmt.executeUpdate();
                    x= 1; //회원탈퇴 성공
            	}else
            		x= 0; //비밀번호 틀림
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
    
    
    public Vector zipcodeRead(String area4)  {
    	Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Vector vecList = new Vector();//값을 알 수 없어서 벡터를 씀
        
        try {
            con = getConnection();
            String strQuery = "select*from zipcode where area4 like '"+area4+"%'";
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
