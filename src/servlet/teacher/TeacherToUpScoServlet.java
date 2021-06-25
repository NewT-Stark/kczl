package servlet.teacher;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Score;
import service.teacher.TeacherService;

@WebServlet("/toupdatesco")
public class TeacherToUpScoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Score sco = new Score();
		sco.setStudent_id(Integer.parseInt(request.getParameter("id")));
		sco.setCourse_id(Integer.parseInt(request.getParameter("cid")));
		TeacherService ts = new TeacherService();
		List<Score> li = ts.searchSco(sco);
		Score sce = new Score();
		for(Score sc : li) {
			sce.setId(sc.getId());
			sce.setStudent_id(sc.getStudent_id());
			sce.setCourse_id(sc.getCourse_id());
			sce.setScore(sc.getScore());
			sce.setStatus(sc.getStatus());
		}
		request.setAttribute("sco", sce);
		RequestDispatcher rds = request.getRequestDispatcher("jsp/updateScore.jsp");
		rds.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
