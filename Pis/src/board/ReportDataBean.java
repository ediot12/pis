package board;

import java.sql.Timestamp;

public class ReportDataBean {
	private int num; //번호
	private String kind;//불편종류
	private String writer;//작성자
	private String subject; //제목
	private Timestamp regdt; //작성일
	private String content;	//내용
	
	
	public int getNum() {
		return num;
	} 
	public void setNum(int num) {
		this.num = num;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Timestamp getRegdt() {
		return regdt;
	}
	public void setRegdt(Timestamp regdt) {
		this.regdt = regdt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
}
