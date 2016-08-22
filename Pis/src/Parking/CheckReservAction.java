package Parking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;;

public class CheckReservAction implements CommandAction{
	
	
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable{
		
	/*	String name = request.getParameter("name");
		String phoneNum = request.getParameter("phone");
		String parkName = request.getParameter("parkname");
		String parkLoca = request.getParameter("parkloca");
		String carType = request.getParameter("car");*/
	
		
		return "/park/paySuccess.jsp";
	}

}
