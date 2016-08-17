package Pay;

import java.sql.Date;
import java.sql.Timestamp;

public class PayDataBean {
	private String id;
	private int point;
	private int use_point;
	private int balance_point;
	private Timestamp pdate;
	private String info;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getUse_point() {
		return use_point;
	}
	public void setUse_point(int use_point) {
		this.use_point = use_point;
	}
	public int getBalance_point() {
		return balance_point;
	}
	public void setBalance_point(int balance_point) {
		this.balance_point = balance_point;
	}
	public Timestamp getPdate() {
		return pdate;
	}
	public void setPdate(Timestamp pdate) {
		this.pdate = pdate;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

	
}
