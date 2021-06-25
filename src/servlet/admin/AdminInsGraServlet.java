package servlet.admin;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Grade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.admin.AdminService;

@WebServlet("/insertgra")
public class AdminInsGraServlet extends HttpServlet {
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
        	Grade grade = new Grade();
        	grade.setId(Integer.parseInt(JSONArray.fromObject(jso.getString("col1")).getString(i)));
        	grade.setName(JSONArray.fromObject(jso.getString("col2")).getString(i));
        	grade.setCollege_id(Integer.parseInt(JSONArray.fromObject(jso.getString("col3")).getString(i)));
        	grade.setPro_id(Integer.parseInt(JSONArray.fromObject(jso.getString("col4")).getString(i)));
        	if(as.findGra(grade) == null) {
        		as.insertGrade(grade);
        	}else {
        		Grade fin = (Grade)as.findGra(grade);
        		if(fin.getId().equals(grade.getId())) {
            		as.updateGrade(grade);
            	}else {
            		as.insertGrade(grade);
            	}
        	}
        }
		RequestDispatcher rds = request.getRequestDispatcher("admin_gra");
		rds.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
