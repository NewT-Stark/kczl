package service.admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.Dao;
import dto.Admin;
import dto.Clazz;
import dto.College;
import dto.Course;
import dto.Grade;
import dto.Professional;
import dto.Score;
import dto.Student;
import dto.Teacher;

public class AdminService {
	Dao da = new Dao();

	public String adminLogin(Admin adt){
		Admin ad = (Admin)da.find(adt);
		if(da.find(adt) != null) {
			return ad.getName();
		}else {
			return "error";
		}
	}
	public int updateAdmin(Admin adm) {
		if(da.update(adm) != -1) {
			return 0;
		}else {
			return -1;
		}
	}
	public Object findAdm(Admin adm) {
		return da.find(adm);
	}
	public List<Teacher> adminTeacher(Teacher tea){
		List<Object> d = da.select(tea);
		List<Teacher> tr = new ArrayList<Teacher>();
		ObjectMapper objectMapper = new ObjectMapper();
		for(Object obj : d) {
			tr.add(objectMapper.convertValue(obj, Teacher.class));
		}
		return tr;
	}
	public int addTeacher(Teacher tea) {
		if(da.insert(tea) != -1) {
			return 0;
		}else {
			return -1;
		}
	}
	public int delTeacher(Teacher tea) {
		if(da.delete(tea) != -1) {
			return 0;
		}else {
			return -1;
		}
	}
	public int updateTeacher(Teacher tea) {
		if(da.update(tea) != -1) {
			return 0;
		}else {
			return -1;
		}
	}
	public Object findTea(Teacher tea) {
		return da.find(tea);
	}
	public List<Teacher> seaTea(Teacher tea){
		List<Object> d = da.search(tea);
		List<Teacher> tr = new ArrayList<Teacher>();
		ObjectMapper objectMapper = new ObjectMapper();
		for(Object obj : d) {
			tr.add(objectMapper.convertValue(obj, Teacher.class));
			System.out.println(tr.toString());
		}
		return tr;
	}
	public List<Student> adminStudent(Student stu){
		List<Object> d = da.select(stu);
		List<Student> tr = new ArrayList<Student>();
		ObjectMapper objectMapper = new ObjectMapper();
		for(Object obj : d) {
			tr.add(objectMapper.convertValue(obj, Student.class));
		}
		return tr;
	}
	public int addStudent(Student stu) {
		if(da.insert(stu) != -1) {
			return 0;
		}else {
			return -1;
		}
	}
	public int delStudent(Student stu) {
		if(da.delete(stu) != -1) {
			return 0;
		}else {
			return -1;
		}
	}
	public int updateStudent(Student stu) {
		if(da.update(stu) != -1) {
			return 0;
		}else {
			return -1;
		}
	}
	public Object findStu(Student stu) {
		return da.find(stu);
	}
	public List<Student> seaStu(Student stu){
		List<Object> d = da.search(stu);
		List<Student> tr = new ArrayList<Student>();
		ObjectMapper objectMapper = new ObjectMapper();
		for(Object obj : d) {
			tr.add(objectMapper.convertValue(obj, Student.class));
		}
		return tr;
	}
	public Set<Clazz> seaCla(Clazz clazz){
		List<Object> d = da.seacla(clazz);
		List<Clazz> tr = new ArrayList<Clazz>();
		ObjectMapper objectMapper = new ObjectMapper();
		for(Object obj : d) {
			tr.add(objectMapper.convertValue(obj, Clazz.class));
		}
		Set<Clazz> set = new HashSet<Clazz>();
		set.addAll(tr);
		return set;
	}
	public List<College> adminCollege(College col){
		List<Object> d = da.select(col);
		List<College> tr = new ArrayList<College>();
		ObjectMapper objectMapper = new ObjectMapper();
		for(Object obj : d) {
			tr.add(objectMapper.convertValue(obj, College.class));
		}
		return tr;
	}
	public int insertCollege(College col) {
		if(da.insert(col) != -1) {
			return 0;
		}else {
			return -1;
		}
	}
	public Object findCol(College col) {
		return da.find(col);
	}
	public int updateCollege(College col) {
		if(da.update(col) != -1) {
			return 0;
		}else {
			return -1;
		}
	}
	public List<Course> adminCourse(Course cou){
		List<Object> d = da.select(cou);
		List<Course> tr = new ArrayList<Course>();
		ObjectMapper objectMapper = new ObjectMapper();
		for(Object obj : d) {
			tr.add(objectMapper.convertValue(obj, Course.class));
		}
		return tr;
	}
	public int insertCourse(Course cou) {
		if(da.insert(cou) != -1) {
			return 0;
		}else {
			return -1;
		}
	}
	public Object findCou(Course cou) {
		return da.find(cou);
	}
	public int updateCourse(Course cou) {
		if(da.update(cou) != -1) {
			return 0;
		}else {
			return -1;
		}
	}
	public List<Professional> adminProfessional(Professional pro){
		List<Object> d = da.select(pro);
		List<Professional> tr = new ArrayList<Professional>();
		ObjectMapper objectMapper = new ObjectMapper();
		for(Object obj : d) {
			tr.add(objectMapper.convertValue(obj, Professional.class));
		}
		return tr;
	}
	public int insertProfessional(Professional pro) {
		if(da.insert(pro) != -1) {
			return 0;
		}else {
			return -1;
		}
	}
	public Object findPro(Professional pro) {
		return da.find(pro);
	}
	public int updateProfessional(Professional pro) {
		if(da.update(pro) != -1) {
			return 0;
		}else {
			return -1;
		}
	}
	public List<Grade> adminGrade(Grade gr){
		List<Object> d = da.select(gr);
		List<Grade> tr = new ArrayList<Grade>();
		ObjectMapper objectMapper = new ObjectMapper();
		for(Object obj : d) {
			tr.add(objectMapper.convertValue(obj, Grade.class));
		}
		return tr;
	}
	public int insertGrade(Grade gra) {
		if(da.insert(gra) != -1) {
			return 0;
		}else {
			return -1;
		}
	}
	public Object findGra(Grade gra) {
		return da.find(gra);
	}
	public int updateGrade(Grade gra) {
		if(da.update(gra) != -1) {
			return 0;
		}else {
			return -1;
		}
	}
	public List<Score> adminScore(Score sc){
		List<Object> d = da.select(sc);
		List<Score> tr = new ArrayList<Score>();
		ObjectMapper objectMapper = new ObjectMapper();
		for(Object obj : d) {
			tr.add(objectMapper.convertValue(obj, Score.class));
		}
		return tr;
	}
	public int updateScore(Score sco) {
		if(da.update(sco) != -1) {
			return 0;
		}else {
			return -1;
		}
	}
	public List<Score> searchSco(Score sc){
		List<Object> d = da.searchs(sc);
		List<Score> tr = new ArrayList<Score>();
		ObjectMapper objectMapper = new ObjectMapper();
		for(Object obj : d) {
			tr.add(objectMapper.convertValue(obj, Score.class));
		}
		return tr;
	}
}
