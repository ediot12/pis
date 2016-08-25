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
	private Map commandMap = new HashMap(); // 명령어와 명령어 처리 클래스를 쌍으로 저장

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	// 명령어와 처리클래스가 매핑되어 있는 properties 파일을 밁어서 Map객체인 commandMap에 저장
	// 명령어와 처리클래스가 매핑되어 있는 properties 파일은 Command.properties파일
	public void init(ServletConfig config) throws ServletException {

		//////////////////////// 기존에 있던 부분
		String props = config.getInitParameter("propertyConfig");
		// web.xml에서 propertyConfig에 해당하는 init-param의 값을 읽어옴
		Properties pr = new Properties(); // 명령어와 처리클래스의 매핑정보를 저장할 Properties객체
											// 생성
		FileInputStream f = null;
		try {
			// 경로를 이용해 모델 선택(전체경로 설정하는 configFilePath)
			app = config.getServletContext();
			String configFilePath = app.getRealPath(props);
			f = new FileInputStream(configFilePath); // Command.properties파일의
														// 내용을 읽어옴

			pr.load(f);// Command.properties파일의 정보를 Properties 객체에 저장
		} catch (IOException e) {
			throw new ServletException(e);
		} finally {
			if (f != null)
				try {
					f.close();
				} catch (IOException e) {
				}
		}
		Iterator keyIter = pr.keySet().iterator();// Iterator객체는 Enumeration 객체를
													// 확장시킨 개념의 객체
		while (keyIter.hasNext()) {// 객체를 하나씩 꺼내서 그 객체명으로 Properties객체에 저장된 객체에
									// 접근
			String command = (String) keyIter.next();
			String className = pr.getProperty(command);
			try {
				Class commandClass = Class.forName(className);// 해당 문자열을 클래스로
																// 만든다.
				Object commandInstance = commandClass.newInstance();// 해당 클래스의
																	// 객체를 생성
				commandMap.put(command, commandInstance);// Map객체인 commandMap에
															// 객체 저장
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

	// 사용자의 요청을 분석해서 해당 작업을 처리
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
			
			/////이하 기존에 있던 부분
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
