package servlet.admin;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Clazz;
import dto.Student;
import service.admin.AdminService;

@WebServlet("/toupstu")
public class AdminToUpStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student stu = new Student();
		Clazz cla = new Clazz();
		HttpSession session = request.getSession();
		stu.setId(Integer.parseInt(request.getParameter("id")));
		AdminService as = new AdminService();
		Student stud = (Student)as.findStu(stu);
		Set<Clazz> li = as.seaCla(cla);
		request.setAttribute("stud", stud);
		session.setAttribute("cla", li);
		RequestDispatcher rds = request.getRequestDispatcher("jsp/updateStudent.jsp");
		rds.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
