package servlet.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Clazz;
import dto.Grade;
import service.admin.AdminService;

@WebServlet("/toaddstu")
public class AdminToaddStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Clazz cla = new Clazz();
		HttpSession session = request.getSession();
		AdminService as = new AdminService();
		Set<Clazz> li = as.seaCla(cla);
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
		session.setAttribute("cla", li);
		RequestDispatcher rds = request.getRequestDispatcher("jsp/addStudent.jsp");
		rds.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
