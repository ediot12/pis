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
		String savePath = "request.getServletContext().getRealPath"; // �������� ���
		String realPath = "";
		String type = "utf-8";
		int sizeLimit = 5*1024*1024;// ���� ���ѿ뷮 5M
		int exist = 0;
		
		// MultipartRequest�� ���۹��� �����͸� �ҷ��´�.
		// enctype�� "multipart/form-data"�� �����ϰ� submit�� �����͵��� request��ü�� �ƴ� MultipartRequest��ü�� �ҷ��;� �Ѵ�.
		//���� ���ε� �ϱ� ���� MultipartRequest ����
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, type, new DefaultFileRenamePolicy());
		
		String fileName = multi.getFilesystemName("upload");// ���۹��� �����Ͱ� ������ ��� getFilesystemName()���� ���� �̸��� �޾ƿ� �� �ִ�.
		String m_fileFullPath = savePath + "/" + fileName; //���ε� �� ������ File��ü�� ��´�
		File file = multi.getFile("upload"); //���ڿ��� ��ȯ
		String file_name = String.valueOf(file); //���� ���� �̸�
		String real_file = new File(file_name).getName(); // ���ϸ� DB����
		LogonDataBean member = new LogonDataBean(); // member��������  LogonDataBean��ü����
		String certify = (String)request.getSession().getAttribute("certify"); // ���ǿ����� certify���� ������ ����
		
		if(certify==null) certify="n";  // �̸��� ���� �ޱ��� n�� ����

	    if(certify.equals("y")){
		// certify �� "y"�� ������ ȸ������ ����
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
        member.setGrade("�Ϲ�");
        member.setChecked("������ �� ����");
        LogonDBBean manager = LogonDBBean.getInstance();
        
        exist = manager.insertMember(member);
        
	}
	    else  // �������� ���ϸ� ȸ������ �Ұ� 
	    	
		exist = -1;
        request.setAttribute("exist", exist);
        request.setAttribute("member", member);
        
        return "/Join/inputPro.jsp";
        
        }  
	}
