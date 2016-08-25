package Join;

import java.io.File;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


import logon.LogonDBBean;
import logon.LogonDataBean;
import controller.CommandAction;


public class InputProAction implements CommandAction {
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable{
		
		/*String savePath = request.getServletContext().getRealPath("filesave");*/
		request.setCharacterEncoding("utf-8");
		String savePath = "request.getServletContext().getRealPath"; // 파일저장 경로
		String realPath = "";
		String type = "utf-8";
		int sizeLimit = 5*1024*1024;// 파일 제한용량 5M
		int exist = 0;
		
		// MultipartRequest로 전송받은 데이터를 불러온다.
		// enctype을 "multipart/form-data"로 선언하고 submit한 데이터들은 request객체가 아닌 MultipartRequest객체로 불러와야 한다.
		//파일 업로드 하기 위한 MultipartRequest 생성
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, type, new DefaultFileRenamePolicy());
		
		String fileName = multi.getFilesystemName("upload");// 전송받은 데이터가 파일일 경우 getFilesystemName()으로 파일 이름을 받아올 수 있다.
		String m_fileFullPath = savePath + "/" + fileName; //업로드 된 파일의 File객체를 얻는다
		File file = multi.getFile("upload"); //문자열로 변환
		String file_name = String.valueOf(file); //실제 파일 이름
		String real_file = new File(file_name).getName(); // 파일명만 DB저장
		LogonDataBean member = new LogonDataBean(); // member변수명의  LogonDataBean객체생성
		String certify = (String)request.getSession().getAttribute("certify"); // 세션영역의 certify값을 가져와 저장
		
		if(certify==null) certify="n";  // 이메일 인증 받기전 n값 저장

	    if(certify.equals("y")){
		// certify 가 "y"와 같을때 회원가입 가능
		member.setId(multi.getParameter("id"));
        member.setPasswd(multi.getParameter("passwd"));
        member.setName(multi.getParameter("name"));
        member.setPhone(multi.getParameter("phone"));
        member.setZipcode(multi.getParameter("zipcode"));
        member.setAddress(multi.getParameter("address"));
        member.setEmail(multi.getParameter("email"));
        member.setCertify((String)(request.getSession().getAttribute("certify")));
        member.setResident(multi.getParameter("resident"));
        member.setUpload(real_file);
        member.setReg_date(new Timestamp(System.currentTimeMillis())); 
        member.setDiscount("0");        
        member.setGrade("일반");
        member.setChecked("거주자 미 승인");
        LogonDBBean manager = LogonDBBean.getInstance();
        
        exist = manager.insertMember(member);
        
	}
	    else  // 인증받지 못하면 회원가입 불가 
	    	
		exist = -1;
        request.setAttribute("exist", exist);
        request.setAttribute("member", member);
        
        return "/Join/inputPro.jsp";
        
        }  
	}
