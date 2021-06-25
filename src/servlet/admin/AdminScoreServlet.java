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

import dto.Course;
import dto.Score;
import dto.Student;
import service.admin.AdminService;

@WebServlet("/admin_sco")
public class AdminScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Score sco = new Score();
		HttpSession session = request.getSession();
		AdminService as = new AdminService();
		List<Score> list = new ArrayList<Score>();
		List<Score> li = as.adminScore(sco);
		for(Score sc : li) {
			list.add(sc);
		}
		
		Student st = new Student();
		List<Student> list3 = new ArrayList<Student>();
		List<Student> li3 = as.adminStudent(st);
		for(Student s : li3) {
			list3.add(s);
		}
		Map<Integer, String> ss = new HashMap<Integer, String>();
		for(Student s : li3) {
			ss.put(s.getId(), s.getName());
		}
		Course co = new Course();
		List<Course> list2 = new ArrayList<Course>();
		List<Course> li2 = as.adminCourse(co);
		for(Course c : li2) {
			list2.add(c);
		}
		Map<Integer, String> cs = new HashMap<Integer, String>();
		for(Course c : li2) {
			cs.put(c.getId(), c.getName());
		}
		session.setAttribute("na", ss);
		session.setAttribute("cs", cs);
		session.setAttribute("admsco", list);
		RequestDispatcher rds = request.getRequestDispatcher("jsp/admsentSco.jsp");
		rds.forward(request, response);
	}
}
