package com.dbTest.join;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginOk
 */
@WebServlet("/LoginOk")
public class LoginOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection conn;
	private Statement stmt;
	private ResultSet resultS;
	
	private String name, id, pw;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginOk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost loginOk");
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response){
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		
		String query = "select name, id, pw from member where id='"+ id + "' and pw = '"+pw + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl", "dahye", "dahye");
			stmt = conn.createStatement();
			resultS = stmt.executeQuery(query);
			
			while(resultS.next()){
				name = resultS.getString("name");
				id = resultS.getString("id");
				pw = resultS.getString("pw");
			}
			
			if( name != null){
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("name", name);
				httpSession.setAttribute("id", id);
				httpSession.setAttribute("pw", pw);
				
				response.sendRedirect("loginResult.jsp");
				
			}else{
				response.sendRedirect("login.html");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultS != null) resultS.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
