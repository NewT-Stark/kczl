package servlet.teacher;

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
import service.teacher.TeacherService;

@WebServlet("/teach_cou")
public class TeacherCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Course cou = new Course();
		HttpSession session = request.getSession();
		TeacherService ts = new TeacherService();
		
		List<Course> list2 = new ArrayList<Course>();
		List<Course> li2 = ts.teacherCourse(cou);
		for(Course c : li2) {
			list2.add(c);
		}
		session.setAttribute("teacou", list2);
		RequestDispatcher rds = request.getRequestDispatcher("jsp/teacherCou.jsp");
		rds.forward(request, response);
	}
}
