package dto;

public class Teacher {
	private Integer id;
	private String name;
	private String password;
	private String sex;
	private String contact;
	private String title;
	
	public Teacher() {
		super();
	}
	
	public Teacher(Integer id, String name, String password, String sex, String contact, String title) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.contact = contact;
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", password=" + password + ", sex=" + sex + ", contact="
				+ contact + ", title=" + title + "]";
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
