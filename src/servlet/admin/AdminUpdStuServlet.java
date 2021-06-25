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

@WebServlet("/updatestu")
public class AdminUpdStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student stu = new Student();
		stu.setId(Integer.parseInt(request.getParameter("id")));
		stu.setName(request.getParameter("name"));
		stu.setPassword(request.getParameter("password"));
		stu.setSex(request.getParameter("sex"));
		stu.setClazz(Integer.parseInt(request.getParameter("clazz")));
		stu.setBirthday(request.getParameter("birthday"));
		AdminService as = new AdminService();
		as.updateStudent(stu);
		RequestDispatcher rds = request.getRequestDispatcher("jsp/informStu.jsp");
		rds.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
