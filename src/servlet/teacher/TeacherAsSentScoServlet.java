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

@WebServlet("/assentsco")
public class TeacherAsSentScoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Score sco = new Score();
		TeacherService ts = new TeacherService();
		List<Score> li = ts.TeacherScore(sco);
		Score sce = new Score();
		for(Score sc : li) {
			if(("未审核").equals(sc.getStatus())) {
				sce.setId(sc.getId());
				sce.setStudent_id(sc.getStudent_id());
				sce.setCourse_id(sc.getCourse_id());
				sce.setScore(sc.getScore());
				sce.setStatus("已送审");
				ts.updateScore(sce);
			}
		}
		RequestDispatcher rds = request.getRequestDispatcher("teach_sco");
		rds.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
