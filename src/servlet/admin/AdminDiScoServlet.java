package servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Score;
import service.admin.AdminService;

@WebServlet("/admindisco")
public class AdminDiScoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String cid = request.getParameter("cid");
		Score sco = new Score();
		sco.setStudent_id(Integer.parseInt(id));
		sco.setCourse_id(Integer.parseInt(cid));
		AdminService as = new AdminService();
		List<Score> li = as.searchSco(sco);
		Score sce = new Score();
		for(Score sc : li) {
			sce.setId(sc.getId());
			sce.setStudent_id(sc.getStudent_id());
			sce.setCourse_id(sc.getCourse_id());
			sce.setScore(sc.getScore());
		}
		sce.setStatus("未审核");
		as.updateScore(sce);
		RequestDispatcher rds = request.getRequestDispatcher("admin_sco");
		rds.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
