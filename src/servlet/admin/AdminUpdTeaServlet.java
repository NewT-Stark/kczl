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

@WebServlet("/updatetea")
public class AdminUpdTeaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Teacher tea = new Teacher();
		tea.setId(Integer.parseInt(request.getParameter("id")));
		tea.setName(request.getParameter("name"));
		tea.setPassword(request.getParameter("password"));
		tea.setSex(request.getParameter("sex"));
		tea.setContact(request.getParameter("contact"));
		tea.setTitle(request.getParameter("title"));
		AdminService as = new AdminService();
		as.updateTeacher(tea);
		RequestDispatcher rds = request.getRequestDispatcher("jsp/informTea.jsp");
		rds.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
