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
		
		
		// ������ ����� ������ ���. �ǵ����̸� getRealPath�� �̿�����.
		// String savePath = "c:/Pis/workspace/Pis/WebContent/fileSave";
		/*String savePath = request.getServletContext().getRealPath("filesave");*/
		request.setCharacterEncoding("utf-8");
		String savePath = request.getServletContext().getRealPath("filesave");
		String realPath = ""; 
		String type = "utf-8";
		int sizeLimit = 5*1024*1024;//5M
				
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, type, new DefaultFileRenamePolicy());
				
		// MultipartRequest�� ���۹��� �����͸� �ҷ��´�.
		// enctype�� "multipart/form-data"�� �����ϰ� submit�� �����͵��� request��ü�� �ƴ� MultipartRequest��ü�� �ҷ��;� �Ѵ�.
		// ���۹��� �����Ͱ� ������ ��� getFilesystemName()���� ���� �̸��� �޾ƿ� �� �ִ�.
		String fileName = multi.getFilesystemName("upload");
		// ���ε��� ������ ��ü ��θ� DB�� �����ϱ� ����
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
        member.setGrade("�Ϲ�");
        
		
        LogonDBBean manager = LogonDBBean.getInstance();
        manager.insertMember(member);
        
        request.setAttribute("member", member);
      
		return "/Join/inputPro.jsp";
	}
}
