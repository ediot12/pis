package carpark;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class CarparkInputAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {	
				
		return "/admin/carpark/carparkInput.jsp";//ÇØ´çºä
	}

}
