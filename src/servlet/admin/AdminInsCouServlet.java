package servlet.admin;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Course;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.admin.AdminService;

@WebServlet("/insertcou")
public class AdminInsCouServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        AdminService as = new AdminService();
        BufferedReader reader = request.getReader();
        String json = reader.readLine();
        reader.close();
        JSONObject jso=JSONObject.fromObject(json.toString());
        for(int i = 0; i < JSONArray.fromObject(jso.getString("col1")).size(); i++) {
        	Course course = new Course();
        	course.setId(Integer.parseInt(JSONArray.fromObject(jso.getString("col1")).getString(i)));
        	course.setName(JSONArray.fromObject(jso.getString("col2")).getString(i));
        	course.setTeacher_id(Integer.parseInt(JSONArray.fromObject(jso.getString("col3")).getString(i)));
        	course.setSemester(JSONArray.fromObject(jso.getString("col4")).getString(i));
        	course.setCredits(Integer.parseInt(JSONArray.fromObject(jso.getString("col5")).getString(i)));
        	if(as.findCou(course) == null) {
        		as.insertCourse(course);
        	}else {
        		Course fin = (Course)as.findCou(course);
        		if(fin.getId().equals(course.getId())) {
            		as.updateCourse(course);
            	}else {
            		as.insertCourse(course);
            	}
        	}
        }
		RequestDispatcher rds = request.getRequestDispatcher("admin_cou");
		rds.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
