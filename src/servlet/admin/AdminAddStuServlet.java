package servlet.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Student;
import service.admin.AdminService;

@WebServlet("/addstu")
public class AdminAddStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Student stu = new Student();
		stu.setName(request.getParameter("name"));
		stu.setPassword(request.getParameter("password"));
		stu.setSex(request.getParameter("sex"));
		stu.setClazz(Integer.parseInt(request.getParameter("clazz")));
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String birthday = year.concat(".").concat(month).concat(".").concat(day);
		stu.setBirthday(birthday);
		AdminService as = new AdminService();
		as.addStudent(stu);
		RequestDispatcher rds = request.getRequestDispatcher("admin_stu");
		rds.forward(request, response);
	}
}
