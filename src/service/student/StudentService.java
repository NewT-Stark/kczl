package service.student;

import dao.Dao;
import dto.Student;

public class StudentService {
	Dao da = new Dao();

	public String studentLogin(Student stu){
		Student st = (Student)da.find(stu);
		if(da.find(stu) != null) {
			return st.getName();
		}else {
			return "error";
		}
	}
	public int insertStudent(Student stu) {
		if(da.insert(stu) != -1) {
			return 0;
		}else {
			return -1;
		}
	}
	public Object findStu(Student stu) {
		return da.find(stu);
	}
	public int updateStudent(Student stu) {
		if(da.update(stu) != -1) {
			return 0;
		}else {
			return -1;
		}
	}

}
