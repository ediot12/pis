package board;

import java.sql.*;

public class CommentDataBean {
	private int comment_num; //�ڸ�Ʈ��ȣ
	private int content_num; //�ڸ�Ʈ�� �����ִ� �� ��ȣ
	private String commenter;//�ۼ���
	private String kind; //����or�亯
	private String commentt; //�ڸ�Ʈ����
	private Timestamp reg_date;//��¥

	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public String getCommentt() {
		return commentt;
	}
	public void setCommentt(String commentt) {
		this.commentt = commentt;
	}

	public int getContent_num() {
		return content_num;
	}
	public void setContent_num(int content_num) {
		this.content_num = content_num;
	}
	public String getCommenter() {
		return commenter;
	}
	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
}
