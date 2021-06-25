package servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.admin.AdminService;
import dto.Admin;

@WebServlet("/admin_login")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin adt = new Admin();
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		try{
			adt.setId(Integer.parseInt(id));
			adt.setPassword(request.getParameter("pwd"));
			String pst = request.getParameter("position");
			
			AdminService as = new AdminService();
			if(!("error").equals(as.adminLogin(adt))){
				session.setAttribute("id", id);
				session.setAttribute("name", as.adminLogin(adt));
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
