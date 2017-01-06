package com.dbTest.join;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ModifyOk
 */
@WebServlet("/ModifyOk")
public class ModifyOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection conn;
	private Statement stmt;
	HttpSession httpSession;
	
	private String name, id, pw, phone1, phone2, phone3, gender;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyOk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("dopost modifyok");
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("EUC-KR");
		httpSession = request.getSession(); //¿Ã∫Œ∫–ø°º≠ ±≤¿Â»˜ «Ï∏…!!!!!!!!!!!!!»Â!!
		
		name = request.getParameter("name");
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		phone1 = request.getParameter("phone1");
		phone2 = request.getParameter("phone2");
		phone3 = request.getParameter("phone3");
		gender = request.getParameter("gender");
		
		if(pwConfirm()){
			System.out.println("ok");
		
			String query = "update member set name='" + name + "', phone1 ='" + phone1 + "', phone2 ='" + phone2 + "', phone3 = '" + phone3 + "', gender = '" + gender + "' where id = '" + httpSession.getAttribute("id") + "'";
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl", "dahye", "dahye");
				stmt = conn.createStatement();
				int i= stmt.executeUpdate(query);
				System.out.println("i : "+i);
				if(i == 1){
					System.out.println("update success");
					httpSession.setAttribute("name", name);
					response.sendRedirect("modifyResult.jsp");
				}else{
					System.out.println("update fail");
					response.sendRedirect("modify.jsp");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(stmt!=null) stmt.close();
					if(conn!=null) conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}else{
			System.out.println("ng");
		}
		
	}

	private boolean pwConfirm(){
		boolean pc = false;
		
		String sessionPw = (String)httpSession.getAttribute("pw");
		
		System.out.println("sessionPW : "+sessionPw);
		System.out.println("pw : " +pw);
		if(sessionPw.equals(pw)){
			pc = true;
		}else{
			pc = false;
		}
		
		return pc;
	}
}
