package mvc.review;

import java.io.File;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.CommandAction;
import mvc.review.*;

public class WriteProAction implements CommandAction{
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
		
	
		// ������ ����� ������ ���. �ǵ����̸� getRealPath�� �̿�����.
		// String savePath = "c:/Pis/workspace/Pis/WebContent/fileSave";
		/*String savePath = request.getServletContext().getRealPath("fileSave");*/
		String savePath = "C:/Users/coco/gitt/pis/Pis/WebContent/filesave";
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
		
		HttpSession session = request.getSession();
		
		ReviewDataBean article = new ReviewDataBean();
		article.setNum(Integer.parseInt(multi.getParameter("num")));
		article.setWriter((String)session.getAttribute("memId"));
		article.setSubject(multi.getParameter("subject"));
		article.setContent(multi.getParameter("content"));
		article.setScore(Integer.parseInt(multi.getParameter("score")));
		article.setBfile(real_file);
		article.setRegdt(new Timestamp(System.currentTimeMillis()));
		
		ReviewDBBean rdd = ReviewDBBean.getInstance();
		rdd.insertArticle(article);
		
		request.setAttribute("rdd", rdd);
		request.setAttribute("article", article);
		

	return "/review/writePro.jsp";
	}
}
