package controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import controller.CommandAction;

public class ControllerUsingURI extends HttpServlet {
	static ServletContext app;
	private Map commandMap = new HashMap(); //��ɾ�� ��ɾ� ó�� Ŭ������ ������ ����
	
	//��ɾ�� ó��Ŭ������ ���εǾ� �ִ� properties ������ ��� Map��ü�� commandMap�� ����
	//��ɾ�� ó��Ŭ������ ���εǾ� �ִ� properties ������ Command.properties����
	public void init(ServletConfig config) throws ServletException{
		String props = config.getInitParameter("propertyConfig");
		//web.xml���� propertyConfig�� �ش��ϴ� init-param�� ���� �о��
		Properties pr = new Properties(); //��ɾ�� ó��Ŭ������ ���������� ������ Properties��ü ����
		FileInputStream f = null;
		try{
			//��θ� �̿��� �� ����(��ü��� �����ϴ� configFilePath)
			app=config.getServletContext();
			String configFilePath = app.getRealPath(props);
			f = new FileInputStream(configFilePath); //Command.properties������ ������ �о��
			
			pr.load(f);//Command.properties������ ������ Properties ��ü�� ����
		}catch(IOException e){
			throw new ServletException(e);
		}finally{
			if(f != null) try{ f.close(); } catch(IOException e){}
		}
		Iterator keyIter = pr.keySet().iterator();//Iterator��ü�� Enumeration ��ü�� Ȯ���Ų ������ ��ü
		while(keyIter.hasNext()){//��ü�� �ϳ��� ������ �� ��ü������ Properties��ü�� ����� ��ü�� ����
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			try{
				Class commandClass = Class.forName(className);//�ش� ���ڿ��� Ŭ������ �����.
				Object commandInstance = commandClass.newInstance();//�ش� Ŭ������ ��ü�� ����
				commandMap.put(command, commandInstance);//Map��ü�� commandMap�� ��ü ����
			}catch(ClassNotFoundException e){
				throw new ServletException(e);
			}catch(InstantiationException e){
				throw new ServletException(e);
			}catch(IllegalAccessException e){
				throw new ServletException(e);
			}
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		requestPro(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		requestPro(request, response);
	}
	
	//������� ��û�� �м��ؼ� �ش� �۾��� ó��
	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String view = null;
		CommandAction com = null;
		try{
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath()) == 0){
				command = command.substring(request.getContextPath().length());
			}
			com = (CommandAction) commandMap.get(command);
			view = com.requestPro(request, response);
		}catch(Throwable e){
			throw new ServletException(e);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}
