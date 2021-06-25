package dto;

public class Semes {
	private String semester;

	public Semes() {
		super();
	}

	public Semes(String semester) {
		super();
		this.semester = semester;
	}

	@Override
	public String toString() {
		return "Semes [semester=" + semester + "]";
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}
	
}
