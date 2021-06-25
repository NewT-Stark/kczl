package servlet.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Grade;
import dto.Student;
import service.admin.AdminService;

@WebServlet("/admin_stu")
public class AdminStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student stu = new Student();
		HttpSession session = request.getSession();
		AdminService as = new AdminService();
		List<Student> list1 = new ArrayList<Student>();
		List<Student> li = as.adminStudent(stu);
		for(Student st : li) {
			list1.add(st);
		}
		Grade gr = new Grade();
		List<Grade> list2 = new ArrayList<Grade>();
		List<Grade> li2 = as.adminGrade(gr);
		for(Grade g : li2) {
			list2.add(g);
		}
		Map<Integer, String> gs = new HashMap<Integer, String>();
		for(Grade g : li2) {
			gs.put(g.getId(), g.getName());
		}
		session.setAttribute("gs", gs);
		session.setAttribute("admStu", list1);
		RequestDispatcher rds = request.getRequestDispatcher("jsp/listStudent.jsp");
		rds.forward(request, response);
	}
}
