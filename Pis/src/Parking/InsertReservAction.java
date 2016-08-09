package Parking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertReservAction implements CommandAction {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		
		request.setCharacterEncoding("utf-8");
		String addr = request.getParameter("addr");
		String parking_name = request.getParameter("parking_name");
		String tel = request.getParameter("tel");
		System.out.println(addr);
		System.out.println(parking_name);
		System.out.println(tel);

		return "/park/reserv.jsp";
	}

}
