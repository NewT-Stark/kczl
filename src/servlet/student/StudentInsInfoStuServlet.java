package servlet.student;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Student;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.student.StudentService;

@WebServlet("/insertinfostu")
public class StudentInsInfoStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        StudentService ss = new StudentService();
        BufferedReader reader = request.getReader();
        String json = reader.readLine();
        reader.close();
        JSONObject jso=JSONObject.fromObject(json.toString());
        Student student = new Student();
        student.setId(Integer.parseInt(request.getParameter("id")));
        student.setName(JSONArray.fromObject(jso.getString("col1")).getString(0));
        student.setSex(JSONArray.fromObject(jso.getString("col2")).getString(0));
        student.setBirthday(JSONArray.fromObject(jso.getString("col3")).getString(0));
        student.setClazz(Integer.parseInt(JSONArray.fromObject(jso.getString("col4")).getString(0)));
        if(ss.findStu(student) == null) {
        	ss.insertStudent(student);
        }else {
        	Student fin = (Student)ss.findStu(student);
        	if(fin.getId().equals(student.getId())) {
        		ss.updateStudent(student);
            }else {
            	ss.insertStudent(student);
            }
        }
		RequestDispatcher rds = request.getRequestDispatcher("jsp/informStu.jsp");
		rds.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
