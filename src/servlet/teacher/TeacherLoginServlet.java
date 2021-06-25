package servlet.teacher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.teacher.TeacherService;
import dto.Teacher;

@WebServlet("/teacher_login")
public class TeacherLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Teacher tea = new Teacher();
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		try{
			tea.setId(Integer.parseInt(id));
			tea.setPassword(request.getParameter("pwd"));
			String pst = request.getParameter("position");
			
			TeacherService ts = new TeacherService();
			if(!("error").equals(ts.teacherLogin(tea))){
				session.setAttribute("id", id);
				session.setAttribute("name", ts.teacherLogin(tea));
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
