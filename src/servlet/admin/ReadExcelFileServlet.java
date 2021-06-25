package servlet.admin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dto.Teacher;
import util.MyUtil;
import util.ReadExcelFile;

@WebServlet("/readExcelFileServlet")
@MultipartConfig

public class ReadExcelFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String savePath = request.getServletContext().getRealPath("/uploadFile");
		//如果文件目录不存在，创建新目录
		File f = new File(savePath);
		if(!f.exists()) {
			f.mkdirs();
		}
		Part part = request.getPart("filePath");
		//获得文件名
		String fileName = MyUtil.getFileName(part);
		String newFileName = null;
		//选择了上传文件
		if(fileName != null && fileName.length() > 0) {
			int lastIndex = fileName.lastIndexOf(".");
			String fileType = fileName.substring(lastIndex);
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHHmmssSSS");
			newFileName = sdf.format(now) + fileType;
			//上传文件
			part.write(savePath + File.separator + newFileName);
		}
		ReadExcelFile ref = new ReadExcelFile();
		//读取Excel内容存入List
		List<Teacher> ls = ref.getExcelInfo(new File(savePath, newFileName));
		//将List内容批量保存到数据库
		Connection con = null;
		PreparedStatement ps = null;
		//加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			//建立连接
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/zzxx?characterEncoding=utf-8&useSSL=false","root","123");
			ps = con.prepareStatement("insert into teacher values(null,?,?,?,?,?)");
			if(ls != null){
				for(int i = 0; i < ls.size(); i++){//多条记录
					ps.setString(1, ls.get(i).getName());
					ps.setString(2, ls.get(i).getPassword());
					ps.setString(3, ls.get(i).getSex());
					ps.setString(4, ls.get(i).getContact());
					ps.setString(5, ls.get(i).getTitle());
					ps.addBatch();
				}
				//批量保存
				ps.executeBatch();
			}
			ps.close();
			con.close();
			System.out.println(111);
			response.sendRedirect("admin_tea");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}