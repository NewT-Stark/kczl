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

import dto.Professional;
import service.admin.AdminService;

@WebServlet("/admin_pro")
public class AdminProfessionalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Professional pro = new Professional();
		HttpSession session = request.getSession();
		AdminService as = new AdminService();
		List<Professional> list = new ArrayList<Professional>();
		List<Professional> li = as.adminProfessional(pro);
		for(Professional co : li) {
			list.add(co);
		}
		session.setAttribute("admpro", list);
		RequestDispatcher rds = request.getRequestDispatcher("jsp/listProfessional.jsp");
		rds.forward(request, response);
	}
}
