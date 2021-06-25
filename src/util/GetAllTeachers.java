package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Teacher;

public class GetAllTeachers {
	public static ArrayList<Teacher> getAllTeachers(){
		Connection Conn = null;
        ResultSet rs = null;
        PreparedStatement ps= null;
        ArrayList<Teacher> allTeachers = null;
        try{
        	Conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/cjgl?characterEncoding=utf-8&useSSL=false","root","123");
        	ps = Conn.prepareStatement("select * from teacher ");
        	rs = ps.executeQuery();
        	allTeachers = new ArrayList<Teacher>();
			while(rs.next()){
				Teacher tea = new Teacher();
				tea.setId(rs.getInt(1));
				tea.setName(rs.getString(2));
				tea.setPassword(rs.getString(3));
				tea.setSex(rs.getString(4));
				tea.setContact(rs.getString(5));
				tea.setTitle(rs.getString(6));
				allTeachers.add(tea);
				System.out.println(tea.toString());
			}
			rs.close();
			ps.close();
			Conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
        return allTeachers;
	}
}