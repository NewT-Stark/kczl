package servlet.admin;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.College;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.admin.AdminService;

@WebServlet("/insertcol")
public class AdminInsColServlet extends HttpServlet {
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
        	College college = new College();
        	college.setId(Integer.parseInt(JSONArray.fromObject(jso.getString("col1")).getString(i)));
        	college.setName(JSONArray.fromObject(jso.getString("col2")).getString(i));
        	if(as.findCol(college) == null) {
        		as.insertCollege(college);
        	}else {
        		College fin = (College)as.findCol(college);
        		if(fin.getId().equals(college.getId())) {
            		as.updateCollege(college);
            	}else {
            		as.insertCollege(college);
            	}
        	}
        }
		RequestDispatcher rds = request.getRequestDispatcher("admin_col");
		rds.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
