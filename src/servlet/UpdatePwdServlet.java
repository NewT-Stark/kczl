package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Admin;
import dto.Student;
import dto.Teacher;
import service.admin.AdminService;

@WebServlet("/updatepwd")
public class UpdatePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		System.out.println(password);
		AdminService as = new AdminService();
		Admin adm = new Admin();
		adm.setId(Integer.parseInt(id));
		Teacher tea = new Teacher();
		tea.setId(Integer.parseInt(id));
		Student stu = new Student();
		stu.setId(Integer.parseInt(id));
		if(as.findAdm(adm) != null) {
			Admin ad = new Admin();
			Admin obj = (Admin)as.findAdm(adm);
			ad.setId(Integer.parseInt(id));
			ad.setName(obj.getName());
			ad.setPassword(password);
			as.updateAdmin(ad);
    	}else if(as.findTea(tea) != null) {
			Teacher te = new Teacher();
			Teacher obj = (Teacher)as.findTea(tea);
			te.setId(Integer.parseInt(id));
			te.setName(obj.getName());
			te.setPassword(password);
			te.setSex(obj.getSex());
			te.setContact(obj.getContact());
			te.setTitle(obj.getTitle());
			as.updateTeacher(te);
		}else {
			Student st = new Student();
			Student obj = (Student)as.findStu(stu);
			st.setId(Integer.parseInt(id));
			st.setName(obj.getName());
			st.setPassword(password);
			st.setSex(obj.getSex());
			st.setBirthday(obj.getBirthday());
			st.setClazz(obj.getClazz());
			as.updateStudent(st);
		}
		RequestDispatcher rds = request.getRequestDispatcher("login.jsp");
		rds.forward(request, response);
	}
}
