package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Initialize.TableRefreshAction;
import Initialize.TableResetAction;

public class ControllerUsingURI extends HttpServlet {
	static ServletContext app;
	private Map commandMap = new HashMap(); // ��ɾ�� ��ɾ� ó�� Ŭ������ ������ ����

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	// ��ɾ�� ó��Ŭ������ ���εǾ� �ִ� properties ������ ��� Map��ü�� commandMap�� ����
	// ��ɾ�� ó��Ŭ������ ���εǾ� �ִ� properties ������ Command.properties����
	public void init(ServletConfig config) throws ServletException {

		//////////////////////// ������ �ִ� �κ�
		String props = config.getInitParameter("propertyConfig");
		// web.xml���� propertyConfig�� �ش��ϴ� init-param�� ���� �о��
		Properties pr = new Properties(); // ��ɾ�� ó��Ŭ������ ���������� ������ Properties��ü
											// ����
		FileInputStream f = null;
		try {
			// ��θ� �̿��� �� ����(��ü��� �����ϴ� configFilePath)
			app = config.getServletContext();
			String configFilePath = app.getRealPath(props);
			f = new FileInputStream(configFilePath); // Command.properties������
														// ������ �о��

			pr.load(f);// Command.properties������ ������ Properties ��ü�� ����
		} catch (IOException e) {
			throw new ServletException(e);
		} finally {
			if (f != null)
				try {
					f.close();
				} catch (IOException e) {
				}
		}
		Iterator keyIter = pr.keySet().iterator();// Iterator��ü�� Enumeration ��ü��
													// Ȯ���Ų ������ ��ü
		while (keyIter.hasNext()) {// ��ü�� �ϳ��� ������ �� ��ü������ Properties��ü�� ����� ��ü��
									// ����
			String command = (String) keyIter.next();
			String className = pr.getProperty(command);
			try {
				Class commandClass = Class.forName(className);// �ش� ���ڿ��� Ŭ������
																// �����.
				Object commandInstance = commandClass.newInstance();// �ش� Ŭ������
																	// ��ü�� ����
				commandMap.put(command, commandInstance);// Map��ü�� commandMap��
															// ��ü ����
			} catch (ClassNotFoundException e) {
				throw new ServletException(e);
			} catch (InstantiationException e) {
				throw new ServletException(e);
			} catch (IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}

	// ������� ��û�� �м��ؼ� �ش� �۾��� ó��
	private void requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String view = null;
		CommandAction com = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String checkTime = null;
		Calendar ca = Calendar.getInstance();

		SimpleDateFormat smf2 = new SimpleDateFormat("yyyy-MM-dd");

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select checkdate from firstdate where rownum=1");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				checkTime = rs.getString(1);
			}
			
			TableRefreshAction trea = TableRefreshAction.getInstance();
			trea.refreshTable();

			if (!smf2.format(ca.getTime()).equals(checkTime)) {
				TableResetAction tra = TableResetAction.getInstance();
				tra.initDB();
			}
			
			/////���� ������ �ִ� �κ�
			String command = request.getRequestURI();
			if (command.indexOf(request.getContextPath()) == 0) {
				command = command.substring(request.getContextPath().length());
			}
			com = (CommandAction) commandMap.get(command);
			view = com.requestPro(request, response);
		} catch (Throwable e) {
			throw new ServletException(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);

	}

}
