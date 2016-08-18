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
		
		
		// 파일이 저장될 서버의 경로. 되도록이면 getRealPath를 이용하자.
		// String savePath = "c:/Pis/workspace/Pis/WebContent/fileSave";
		/*String savePath = request.getServletContext().getRealPath("filesave");*/
		request.setCharacterEncoding("utf-8");
		String savePath = request.getServletContext().getRealPath("filesave");
		String realPath = ""; 
		String type = "utf-8";
		int sizeLimit = 5*1024*1024;//5M
				
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, type, new DefaultFileRenamePolicy());
				
		// MultipartRequest로 전송받은 데이터를 불러온다.
		// enctype을 "multipart/form-data"로 선언하고 submit한 데이터들은 request객체가 아닌 MultipartRequest객체로 불러와야 한다.
		// 전송받은 데이터가 파일일 경우 getFilesystemName()으로 파일 이름을 받아올 수 있다.
		String fileName = multi.getFilesystemName("upload");
		// 업로드한 파일의 전체 경로를 DB에 저장하기 위함
		String m_fileFullPath = savePath + "/" + fileName;
		File file = multi.getFile("upload");
		String file_name = String.valueOf(file);
		String real_file = new File(file_name).getName();
		
		LogonDataBean member = new LogonDataBean();

		member.setId(multi.getParameter("id"));
        member.setPasswd(multi.getParameter("passwd"));
        member.setName(multi.getParameter("name"));
        member.setPhone(multi.getParameter("phone"));
        member.setZipcode(multi.getParameter("zipcode"));
        member.setAddress(multi.getParameter("address"));
        member.setEmail(multi.getParameter("email"));
        member.setCertify((String)(request.getSession().getAttribute("certify")));
        member.setResident(multi.getParameter("resident"));
        member.setUpload(file_name);
        member.setReg_date(new Timestamp(System.currentTimeMillis())); 
        member.setDiscount("0");        
        member.setGrade("일반");
        
		
        LogonDBBean manager = LogonDBBean.getInstance();
        manager.insertMember(member);
        
        request.setAttribute("member", member);
      
		return "/Join/inputPro.jsp";
	}
}
