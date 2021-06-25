package dto;

public class Student {
	private Integer id;
	private String name;
	private String password;
	private String sex;
	private String birthday;
	private Integer clazz;
	
	public Student() {
		super();
	}
	
	public Student(Integer id, String name, String password, String sex, String birthday, Integer clazz) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.birthday = birthday;
		this.clazz = clazz;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", password=" + password + ", sex=" + sex + ", birthday="
				+ birthday + ", clazz=" + clazz + "]";
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Integer getClazz() {
		return clazz;
	}
	public void setClazz(Integer clazz) {
		this.clazz = clazz;
	}
}