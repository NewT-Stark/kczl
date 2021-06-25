package servlet.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Teacher;
import service.admin.AdminService;

@WebServlet("/addtea")
public class AdminAddTeaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Teacher tea = new Teacher();
		tea.setName(request.getParameter("name"));
		tea.setPassword(request.getParameter("password"));
		tea.setSex(request.getParameter("sex"));
		tea.setContact(request.getParameter("contact"));
		tea.setTitle(request.getParameter("title"));
		AdminService as = new AdminService();
		as.addTeacher(tea);
		RequestDispatcher rds = request.getRequestDispatcher("admin_tea");
		rds.forward(request, response);
	}
}
