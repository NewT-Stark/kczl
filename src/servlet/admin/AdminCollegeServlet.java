package servlet.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.College;
import service.admin.AdminService;

@WebServlet("/admin_col")
public class AdminCollegeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		College col = new College();
		HttpSession session = request.getSession();
		AdminService as = new AdminService();
		List<College> list = new ArrayList<College>();
		List<College> li = as.adminCollege(col);
		for(College co : li) {
			list.add(co);
		}
		session.setAttribute("admcol", list);
		RequestDispatcher rds = request.getRequestDispatcher("jsp/listCollege.jsp");
		rds.forward(request, response);
	}
}
