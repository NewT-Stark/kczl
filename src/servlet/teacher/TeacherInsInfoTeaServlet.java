package servlet.teacher;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Teacher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.teacher.TeacherService;

@WebServlet("/insertinfotea")
public class TeacherInsInfoTeaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        TeacherService ts = new TeacherService();
        BufferedReader reader = request.getReader();
        String json = reader.readLine();
        reader.close();
        JSONObject jso=JSONObject.fromObject(json.toString());
        Teacher teacher = new Teacher();
        teacher.setId(Integer.parseInt(request.getParameter("id")));
        teacher.setName(JSONArray.fromObject(jso.getString("col1")).getString(0));
        teacher.setSex(JSONArray.fromObject(jso.getString("col2")).getString(0));
        teacher.setContact(JSONArray.fromObject(jso.getString("col3")).getString(0));
        teacher.setTitle(JSONArray.fromObject(jso.getString("col4")).getString(0));
        if(ts.findTea(teacher) == null) {
        	ts.insertTeacher(teacher);
        }else {
        	Teacher fin = (Teacher)ts.findTea(teacher);
        	if(fin.getId().equals(teacher.getId())) {
            	ts.updateTeacher(teacher);
            }else {
            	ts.insertTeacher(teacher);
            }
        }
		RequestDispatcher rds = request.getRequestDispatcher("admin_cou");
		rds.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
