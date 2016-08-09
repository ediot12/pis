package chart;

import java.sql.*;

public class ChartDataBean {
	private Timestamp c_date;//³¯Â¥
	private String c_ip;//ip
	
	public Timestamp getC_date() {
		return c_date;
	}
	public void setC_date(Timestamp c_date) {
		this.c_date = c_date;
	}
	public String getC_ip() {
		return c_ip;
	}
	public void setC_ip(String c_ip) {
		this.c_ip = c_ip;
	}
	
}
