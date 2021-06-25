package servlet;

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
import dto.Teacher;
import service.admin.AdminService;

@WebServlet("/information")
public class InformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		AdminService as = new AdminService();
		HttpSession session = request.getSession();
		Teacher tea = new Teacher();
		tea.setId(Integer.parseInt(id));
		Student stu = new Student();
		stu.setId(Integer.parseInt(id));
		if(as.findTea(tea) != null) {
			Teacher te = (Teacher)as.findTea(tea);
			session.setAttribute("obj", te);
			RequestDispatcher rds = request.getRequestDispatcher("jsp/informTea.jsp");
			rds.forward(request, response);
		}else {
			Student st = (Student)as.findStu(stu);
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
			session.setAttribute("obj", st);
			RequestDispatcher rds = request.getRequestDispatcher("jsp/informStu.jsp");
			rds.forward(request, response);
		}
	}

}
