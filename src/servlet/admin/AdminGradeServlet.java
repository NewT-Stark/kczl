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

import dto.Grade;
import service.admin.AdminService;

@WebServlet("/admin_gra")
public class AdminGradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Grade gra = new Grade();
		HttpSession session = request.getSession();
		AdminService as = new AdminService();
		List<Grade> list = new ArrayList<Grade>();
		List<Grade> li = as.adminGrade(gra);
		for(Grade co : li) {
			list.add(co);
		}
		session.setAttribute("admgra", list);
		RequestDispatcher rds = request.getRequestDispatcher("jsp/listGrade.jsp");
		rds.forward(request, response);
	}
}
