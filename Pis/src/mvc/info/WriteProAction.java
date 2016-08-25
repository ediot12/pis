package mvc.info;

import java.io.File;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.CommandAction;
import mvc.info.InfoDataBean;

public class WriteProAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
		 
		request.setCharacterEncoding("utf-8"); //�ѱ� ���ڵ�
		
		// ������ ����� ������ ���. �ǵ����̸� getRealPath�� �̿�����.
		// String savePath = "c:/Pis/workspace/Pis/WebContent/fileSave";
		/*String savePath = request.getServletContext().getRealPath("fileSave");*/
		String savePath = "C:/Users/coco/gitt/pis/Pis/WebContent/filesave"; //������ ����� �� ���
		String realPath = ""; 
		String type = "utf-8";
		int sizeLimit = 5*1024*1024;//5M ����� �ִ� ũ��
		
		// MultipartRequest�� ���۹��� �����͸� �ҷ��´�.
		// enctype�� "multipart/form-data"�� �����ϰ� submit�� �����͵��� request��ü�� �ƴ� MultipartRequest��ü�� �ҷ��;� �Ѵ�.
		//���� ���ε� �ϱ� ���� MultipartRequest ����
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, type, new DefaultFileRenamePolicy());
				
		
		// ���۹��� �����Ͱ� ������ ��� getFilesystemName()���� ���� �̸��� �޾ƿ� �� �ִ�.
		String fileName = multi.getFilesystemName("bfile"); //
		// ���ε��� ������ ��ü ��θ� DB�� �����ϱ� ����
		String m_fileFullPath = savePath + "/" + fileName;
		//���ε� �� ������ File��ü�� ��´�
		File file = multi.getFile("bfile");
		//���ڿ��� ��ȯ
		String file_name = String.valueOf(file);
		//���� ���� �̸�
		String real_file = new File(file_name).getName();
		
		//session �ҷ���
		HttpSession session = request.getSession();
		
		//request.getParameter ���� request�� multi �� ���� ����ߵ�
		InfoDataBean article = new InfoDataBean();//������ ó�� ��
		article.setNum(Integer.parseInt(multi.getParameter("num")));
		article.setWriter((String)session.getAttribute("memId"));
		article.setSubject(multi.getParameter("subject"));
		article.setContent(multi.getParameter("content"));
		article.setAddress(multi.getParameter("address"));
		article.setZipcode(multi.getParameter("zipcode"));
		article.setBfile(real_file); //DB�� ��ΰ� �ƴ� �����̸����� ����
		article.setRegdt(new Timestamp(System.currentTimeMillis()));
		

		InfoDBBean pdd = InfoDBBean.getInstance();
		pdd.insertArticle(article);
		
		request.setAttribute("pdd", pdd);
		request.setAttribute("article", article);

		return "/info/writePro.jsp";
		
	}
}
