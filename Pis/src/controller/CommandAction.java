package controller;

import javax.servlet.http.*;

//��û �Ķ���ͷ� ��ɾ �����ϴ� ����� ���� �������̽�
public interface CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable;
}
