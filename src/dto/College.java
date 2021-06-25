package dto;

public class College {
	private Integer id;
	private String name;
	
	public College() {
		super();
	}

	public College(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "College [id=" + id + ", name=" + name + "]";
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
}
