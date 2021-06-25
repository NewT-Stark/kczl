package dto;

public class Clazz {
	private Integer id;

	public Clazz() {
		super();
	}

	public Clazz(Integer id) {
		super();
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Clazz [id=" + id + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
