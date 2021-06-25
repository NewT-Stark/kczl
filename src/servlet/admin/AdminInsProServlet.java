package servlet.admin;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Professional;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.admin.AdminService;

@WebServlet("/insertpro")
public class AdminInsProServlet extends HttpServlet {
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
        	Professional profess = new Professional();
        	profess.setId(Integer.parseInt(JSONArray.fromObject(jso.getString("col1")).getString(i)));
        	profess.setName(JSONArray.fromObject(jso.getString("col2")).getString(i));
        	if(as.findPro(profess) == null) {
        		as.insertProfessional(profess);
        	}else {
        		Professional fin = (Professional)as.findPro(profess);
        		if(fin.getId().equals(profess.getId())) {
            		as.updateProfessional(profess);
            	}else {
            		as.insertProfessional(profess);
            	}
        	}
        }
		RequestDispatcher rds = request.getRequestDispatcher("admin_pro");
		rds.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
