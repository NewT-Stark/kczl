package servlet.teacher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Score;
import service.teacher.TeacherService;

@WebServlet("/updatesco")
public class TeacherUpdScoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Score sco = new Score();
		sco.setId(Integer.parseInt(request.getParameter("id")));
		sco.setStudent_id(Integer.parseInt(request.getParameter("student_id")));
		sco.setCourse_id(Integer.parseInt(request.getParameter("course_id")));
		sco.setScore(Double.parseDouble(request.getParameter("score")));
		sco.setStatus(request.getParameter("status"));
		TeacherService ts = new TeacherService();
		ts.updateScore(sco);
		RequestDispatcher rds = request.getRequestDispatcher("teach_sco");
		rds.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
