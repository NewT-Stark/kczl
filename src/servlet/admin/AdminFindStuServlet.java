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

import dto.Student;
import service.admin.AdminService;

@WebServlet("/findstu")
public class AdminFindStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student stu = new Student();
		stu.setId(Integer.parseInt(request.getParameter("id")));
		HttpSession session = request.getSession();
		AdminService as = new AdminService();
		List<Student> list = new ArrayList<Student>();
		List<Student> li = as.seaStu(stu);
		for(Student te : li) {
			list.add(te);
		}
		session.setAttribute("admStu", list);
		RequestDispatcher rds = request.getRequestDispatcher("jsp/listStudent.jsp");
		rds.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
