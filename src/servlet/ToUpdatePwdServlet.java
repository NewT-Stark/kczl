package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Admin;
import dto.Student;
import dto.Teacher;
import service.admin.AdminService;

@WebServlet("/toupdatepwd")
public class ToUpdatePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		AdminService as = new AdminService();
		HttpSession session = request.getSession();
		Admin adm = new Admin();
		adm.setId(Integer.parseInt(id));
		Teacher tea = new Teacher();
		tea.setId(Integer.parseInt(id));
		Student stu = new Student();
		stu.setId(Integer.parseInt(id));
		if(as.findAdm(adm) != null) {
			Admin ad = (Admin)as.findAdm(adm);
			session.setAttribute("obj", ad);
    	}else if(as.findTea(tea) != null) {
			Teacher te = (Teacher)as.findTea(tea);
			session.setAttribute("obj", te);
		}else {
			Student st = (Student)as.findStu(stu);
			session.setAttribute("obj", st);
		}
		RequestDispatcher rds = request.getRequestDispatcher("jsp/updatePwd.jsp");
		rds.forward(request, response);
	}
}
