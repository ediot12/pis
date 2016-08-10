package carpark;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Parking.ParkInfoBean;
import controller.CommandAction;

public class CarparkInputProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		ParkInfoBean pib = new ParkInfoBean();//单捞磐 贸府后
		pib.setParking_code(Integer.parseInt(request.getParameter("parking_code")));
		pib.setParking_name(request.getParameter("parking_name"));
		pib.setAddr(request.getParameter("addr"));
		pib.setParking_type_nm(request.getParameter("parking_type_nm"));
		pib.setOperation_rule_nm(request.getParameter("operation_rule_nm"));
		pib.setTel(request.getParameter("tel"));
		pib.setCapacity2(Integer.parseInt(request.getParameter("capacity2")));
		pib.setPay_nm(request.getParameter("pay_nm"));
		pib.setWeekday_begin_time(request.getParameter("weekday_begin_time"));
		pib.setWeekday_end_time(request.getParameter("weekday_end_time"));
		pib.setWeekend_begin_time(request.getParameter("weekend_begin_time"));
		pib.setWeekend_end_time(request.getParameter("weekend_end_time"));
		pib.setSaturday_pay_nnm(request.getParameter("saturday_pay_nnm"));
		pib.setHoliday_pay_nm(request.getParameter("holiday_pay_nm"));
		pib.setFulltime_monthly(Integer.parseInt(request.getParameter("fulltime_monthly")));
		pib.setRates(Integer.parseInt(request.getParameter("rates")));
		pib.setTime_rate(Integer.parseInt(request.getParameter("time_rate")));
		pib.setAdd_rates(Integer.parseInt(request.getParameter("add_rates")));
		pib.setAdd_time_rate(Integer.parseInt(request.getParameter("add_time_rate")));
		pib.setDay_maximum(Integer.parseInt(request.getParameter("day_maximum")));
		
		CarDBBean cdb = CarDBBean.getInstance(); //DB贸府
		cdb.setCarpark(pib);
		
		return "/semi/admin/carpark/carparkInputPro.jsp";
	}
	
}
