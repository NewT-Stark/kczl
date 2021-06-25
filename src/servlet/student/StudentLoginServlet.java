package servlet.student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.student.StudentService;
import dto.Student;

@WebServlet("/student_login")
public class StudentLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student stu = new Student();
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		try{
			stu.setId(Integer.parseInt(id));
			stu.setPassword(request.getParameter("pwd"));
			String pst = request.getParameter("position");
			
			StudentService ss = new StudentService();
			if(!("error").equals(ss.studentLogin(stu))){
				session.setAttribute("id", id);
				session.setAttribute("name", ss.studentLogin(stu));
				session.setAttribute("position", pst);
				response.sendRedirect("jsp/index.jsp");
			}else{
				session.setAttribute("error","1");
				response.sendRedirect("jsp/login.jsp");
			}
		}catch(Exception e){
			session.setAttribute("error","1");
			response.sendRedirect("jsp/login.jsp");
		}
		
	}
}
