package test;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class InsertAction {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String savePath = "C:\\Users\\ChangBaeKim\\gitt\\Pis\\WebContent\\abc";
		int sizeLimit = 1024 * 1024 * 15;
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8",
				new DefaultFileRenamePolicy());
		String name = multi.getParameter("name");
		String id = multi.getParameter("id");
		String fileName = multi.getFilesystemName("file");
		String filePath = savePath + "\\" + fileName;

		MemberDTO memberdto = new MemberDTO();
		memberdto.setName(name);
		memberdto.setId(id);
		memberdto.setFileName(fileName);
		memberdto.setFilePath(filePath);
		
		MemberDAO memberdao = MemberDAO.getInstance();
		memberdao.insertMember(memberdto);
	}
}
