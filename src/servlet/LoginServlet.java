package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String pst = request.getParameter("position");
		request.setAttribute("id", id);
		request.setAttribute("pwd", pwd);
		request.setAttribute("position", pst);
		RequestDispatcher rds = null;
		if(("管理员").equals(pst)) {
			rds = request.getRequestDispatcher("admin_login");
		}else if(("教师").equals(pst)) {
			rds = request.getRequestDispatcher("teacher_login");
		}else {
			rds = request.getRequestDispatcher("student_login");
		}
		rds.forward(request, response);
	}
}
