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
	
		 
		request.setCharacterEncoding("utf-8"); //한글 인코딩
		
		// 파일이 저장될 서버의 경로. 되도록이면 getRealPath를 이용하자.
		// String savePath = "c:/Pis/workspace/Pis/WebContent/fileSave";
		/*String savePath = request.getServletContext().getRealPath("fileSave");*/
		String savePath = "C:/Users/coco/gitt/pis/Pis/WebContent/filesave"; //파일이 저장될 실 경로
		String realPath = ""; 
		String type = "utf-8";
		int sizeLimit = 5*1024*1024;//5M 저장될 최대 크기
		
		// MultipartRequest로 전송받은 데이터를 불러온다.
		// enctype을 "multipart/form-data"로 선언하고 submit한 데이터들은 request객체가 아닌 MultipartRequest객체로 불러와야 한다.
		//파일 업로드 하기 위한 MultipartRequest 생성
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, type, new DefaultFileRenamePolicy());
				
		
		// 전송받은 데이터가 파일일 경우 getFilesystemName()으로 파일 이름을 받아올 수 있다.
		String fileName = multi.getFilesystemName("bfile"); //
		// 업로드한 파일의 전체 경로를 DB에 저장하기 위함
		String m_fileFullPath = savePath + "/" + fileName;
		//업로드 된 파일의 File객체를 얻는다
		File file = multi.getFile("bfile");
		//문자열로 변환
		String file_name = String.valueOf(file);
		//실제 파일 이름
		String real_file = new File(file_name).getName();
		
		//session 불러옴
		HttpSession session = request.getSession();
		
		//request.getParameter 에서 request를 multi 로 변경 해줘야됨
		InfoDataBean article = new InfoDataBean();//데이터 처리 빈
		article.setNum(Integer.parseInt(multi.getParameter("num")));
		article.setWriter((String)session.getAttribute("memId"));
		article.setSubject(multi.getParameter("subject"));
		article.setContent(multi.getParameter("content"));
		article.setAddress(multi.getParameter("address"));
		article.setZipcode(multi.getParameter("zipcode"));
		article.setBfile(real_file); //DB에 경로가 아닌 파일이름으로 저장
		article.setRegdt(new Timestamp(System.currentTimeMillis()));
		

		InfoDBBean pdd = InfoDBBean.getInstance();
		pdd.insertArticle(article);
		
		request.setAttribute("pdd", pdd);
		request.setAttribute("article", article);

		return "/info/writePro.jsp";
		
	}
}
