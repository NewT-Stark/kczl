package service.teacher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.Dao;
import dto.Clazz;
import dto.Course;
import dto.Grade;
import dto.Score;
import dto.Semes;
import dto.Student;
import dto.Teacher;

public class TeacherService {
	Dao da = new Dao();

	public String teacherLogin(Teacher tea){
		Teacher te = (Teacher)da.find(tea);
		if(da.find(tea) != null) {
			return te.getName();
		}else {
			return "error";
		}
	}
	public int insertTeacher(Teacher tea) {
		if(da.insert(tea) != -1) {
			return 0;
		}else {
			return -1;
		}
	}
	public Object findTea(Teacher tea) {
		return da.find(tea);
	}
	public int updateTeacher(Teacher tea) {
		if(da.update(tea) != -1) {
			return 0;
		}else {
			return -1;
		}
	}
	public List<Score> TeacherScore(Score sco){
		List<Object> d = da.select(sco);
		List<Score> tr = new ArrayList<Score>();
		ObjectMapper objectMapper = new ObjectMapper();
		for(Object obj : d) {
			tr.add(objectMapper.convertValue(obj, Score.class));
		}
		return tr;
	}
	public Set<Semes> seaSem(Semes semester){
		List<Object> d = da.seasem(semester);
		List<Semes> tr = new ArrayList<Semes>();
		ObjectMapper objectMapper = new ObjectMapper();
		for(Object obj : d) {
			tr.add(objectMapper.convertValue(obj, Semes.class));
		}
		Set<Semes> set = new HashSet<Semes>();
		set.addAll(tr);
		return set;
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
	public List<Course> teacherCourse(Course co){
		List<Object> d = da.select(co);
		List<Course> tr = new ArrayList<Course>();
		ObjectMapper objectMapper = new ObjectMapper();
		for(Object obj : d) {
			tr.add(objectMapper.convertValue(obj, Course.class));
		}
		return tr;
	}
	public List<Student> teacherStudent(Student st){
		List<Object> d = da.select(st);
		List<Student> tr = new ArrayList<Student>();
		ObjectMapper objectMapper = new ObjectMapper();
		for(Object obj : d) {
			tr.add(objectMapper.convertValue(obj, Student.class));
		}
		return tr;
	}
	public List<Grade> teacherGrade(Grade gr){
		List<Object> d = da.select(gr);
		List<Grade> tr = new ArrayList<Grade>();
		ObjectMapper objectMapper = new ObjectMapper();
		for(Object obj : d) {
			tr.add(objectMapper.convertValue(obj, Grade.class));
		}
		return tr;
	}
	public Object findSco(Score sco) {
		return da.find(sco);
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
