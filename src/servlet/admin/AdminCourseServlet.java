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

import dto.Course;
import service.admin.AdminService;

@WebServlet("/admin_cou")
public class AdminCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Course cou = new Course();
		HttpSession session = request.getSession();
		AdminService as = new AdminService();
		List<Course> list = new ArrayList<Course>();
		List<Course> li = as.adminCourse(cou);
		for(Course co : li) {
			list.add(co);
		}
		session.setAttribute("admcou", list);
		RequestDispatcher rds = request.getRequestDispatcher("jsp/listCourse.jsp");
		rds.forward(request, response);
	}
}
