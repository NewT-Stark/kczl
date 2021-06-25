package dto;

public class Grade {
	private Integer id;
	private String name;
	private Integer college_id;
	private Integer pro_id;
	
	public Grade() {
		super();
	}
	
	public Grade(Integer id, String name, Integer college_id, Integer pro_id) {
		super();
		this.id = id;
		this.name = name;
		this.college_id = college_id;
		this.pro_id = pro_id;
	}
	
	@Override
	public String toString() {
		return "Grade [id=" + id + ", name=" + name + ", college_id=" + college_id + ", pro_id=" + pro_id + "]";
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
	public Integer getCollege_id() {
		return college_id;
	}
	public void setCollege_id(Integer college_id) {
		this.college_id = college_id;
	}
	public Integer getPro_id() {
		return pro_id;
	}
	public void setPro_id(Integer pro_id) {
		this.pro_id = pro_id;
	}
	
	
}
