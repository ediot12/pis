package board;

import java.sql.Timestamp;

public class ReportDataBean {
	private int num; //��ȣ
	private String kind;//��������
	private String writer;//�ۼ���
	private String subject; //����
	private Timestamp regdt; //�ۼ���
	private String content;	//����
	
	
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
