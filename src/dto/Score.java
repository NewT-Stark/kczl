package dto;

public class Score {
	private Integer id;
	private Integer student_id;
	private Integer course_id;
	private Double score;
	private String status;
	
	public Score() {
		super();
	}
	
	public Score(Integer id, Integer student_id, Integer course_id, Double score, String status) {
		super();
		this.id = id;
		this.student_id = student_id;
		this.course_id = course_id;
		this.score = score;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Score [id=" + id + ", student_id=" + student_id + ", course_id=" + course_id + ", score=" + score + ", status=" + status + "]";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
