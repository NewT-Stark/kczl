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

@WebServlet("/touptea")
public class AdminToUpTeaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Teacher tea = new Teacher();
		tea.setId(Integer.parseInt(request.getParameter("id")));
		AdminService as = new AdminService();
		Teacher teac = (Teacher)as.findTea(tea);
		request.setAttribute("teac", teac);
		RequestDispatcher rds = request.getRequestDispatcher("jsp/updateTeacher.jsp");
		rds.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
