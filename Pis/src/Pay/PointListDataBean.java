package Pay;

import java.sql.Timestamp;

public class PointListDataBean {
		private int num;
		private String id;
	    private String parking_name;
	    private String info;
	    private int point;
	    private int use_point;
	    private Timestamp reg_date;
    
	    
	    
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		public String getParking_name() {
			return parking_name;
		}
		public void setParking_name(String parking_name) {
			this.parking_name = parking_name;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
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
		public Timestamp getReg_date() {
			return reg_date;
		}
		public void setReg_date(Timestamp reg_date) {
			this.reg_date = reg_date;
		}


}
