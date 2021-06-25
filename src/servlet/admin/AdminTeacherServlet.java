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

import dto.Teacher;
import service.admin.AdminService;

@WebServlet("/admin_tea")
public class AdminTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Teacher tea = new Teacher();
		HttpSession session = request.getSession();
		AdminService as = new AdminService();
		List<Teacher> list = new ArrayList<Teacher>();
		List<Teacher> li = as.adminTeacher(tea);
		for(Teacher te : li) {
			list.add(te);
		}
		session.setAttribute("admTea", list);
		RequestDispatcher rds = request.getRequestDispatcher("jsp/listTeacher.jsp");
		rds.forward(request, response);
	}
}
