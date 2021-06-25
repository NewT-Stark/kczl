package dto;

public class Course {
	private Integer id;
	private String name;
	private Integer teacher_id;
	private String semester;
	private Integer credits;
	
	public Course() {
		super();
	}
	
	public Course(Integer id, String name, Integer teacher_id, String semester, Integer credits) {
		super();
		this.id = id;
		this.name = name;
		this.teacher_id = teacher_id;
		this.semester = semester;
		this.credits = credits;
	}
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", teacher_id=" + teacher_id + ", semester =" + semester + ", credits=" + credits + "]";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(Integer teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public Integer getCredits() {
		return credits;
	}
	public void setCredits(Integer credits) {
		this.credits = credits;
	}
	
}
