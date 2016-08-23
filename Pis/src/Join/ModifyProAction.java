package Join;


import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import logon.LogonDBBean;
import logon.LogonDataBean;

import controller.CommandAction;

public class ModifyProAction implements CommandAction {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {		request.setCharacterEncoding("UTF-8");

	
		request.setCharacterEncoding("utf-8");
		
		String savePath = "C:/Users/ÀåÂù±Ô/git/pis/Pis/WebContent/filesave";
		String realPath = ""; 
		String type = "utf-8";
		
		int sizeLimit = 5*1024*1024;//5M
		
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, type, new DefaultFileRenamePolicy());
		
		String fileName = multi.getFilesystemName("upload");
		String m_fileFullPath = savePath + "/" + fileName;
		File file = multi.getFile("upload");
		String file_name = String.valueOf(file);
		String real_file = new File(file_name).getName();
		
		String id = (String)request.getSession().getAttribute("memId");
		LogonDataBean ldb=new LogonDataBean();
		ldb.setId(id);
		ldb.setPasswd(multi.getParameter("passwd"));
		ldb.setName(multi.getParameter("name"));
		ldb.setPhone(multi.getParameter("phone"));
		ldb.setZipcode(multi.getParameter("zipcode"));
		ldb.setAddress(multi.getParameter("address"));
		ldb.setEmail(multi.getParameter("email"));
		ldb.setResident(multi.getParameter("resident"));
		ldb.setUpload(real_file);
		LogonDBBean manager = LogonDBBean.getInstance();
	    
		manager.updateMember(ldb);
		request.setAttribute("member", ldb);
		return "/Join/modifyPro.jsp";
	}
	
}
