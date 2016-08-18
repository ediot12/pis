package mvc.review;

import java.io.File;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.CommandAction;
import mvc.info.InfoDBBean;
import mvc.info.InfoDataBean;

public class WriteProAction implements CommandAction{
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
		
	
		// ������ ����� ������ ���. �ǵ����̸� getRealPath�� �̿�����.
		// String savePath = "c:/Pis/workspace/Pis/WebContent/fileSave";
		/*String savePath = request.getServletContext().getRealPath("fileSave");*/
		String savePath = "C:/Users/coco/git/pis2/Pis/WebContent/fileSave";
		String realPath = ""; 
		String type = "utf-8";
		int sizeLimit = 5*1024*1024;//5M
		
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, type, new DefaultFileRenamePolicy());
		
		// MultipartRequest�� ���۹��� �����͸� �ҷ��´�.
		// enctype�� "multipart/form-data"�� �����ϰ� submit�� �����͵��� request��ü�� �ƴ� MultipartRequest��ü�� �ҷ��;� �Ѵ�.
		// ���۹��� �����Ͱ� ������ ��� getFilesystemName()���� ���� �̸��� �޾ƿ� �� �ִ�.
		String fileName = multi.getFilesystemName("bfile");
		// ���ε��� ������ ��ü ��θ� DB�� �����ϱ� ����
		String m_fileFullPath = savePath + "/" + fileName;
		File file = multi.getFile("bfile");
		String file_name = String.valueOf(file);
		String real_file = new File(file_name).getName();
		
		
		InfoDataBean article = new InfoDataBean();
		article.setNum(Integer.parseInt(multi.getParameter("num")));
		article.setWriter(multi.getParameter("writer"));
		article.setSubject(multi.getParameter("subject"));
		article.setContent(multi.getParameter("content"));
		article.setZipcode(multi.getParameter("zipcode"));
		article.setAddress(multi.getParameter("address"));
		article.setBfile(real_file);
		article.setRegdt(new Timestamp(System.currentTimeMillis()));
		
		InfoDBBean rdd = InfoDBBean.getInstance();
		rdd.insertArticle(article);
		
		request.setAttribute("rdd", rdd);
		request.setAttribute("article", article);
		

	return "/review/writePro.jsp";
	}
}
