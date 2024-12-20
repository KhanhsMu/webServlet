package model;

import java.sql.Timestamp;

public class project {
    private int idProject;        
    private String title;         
    private String description;   
    private String teacherName;   
    private String studentName;   
    private Timestamp submissionTime;  // Thêm thuộc tính submissionTime
    private String submittedFile;  // Thêm thuộc tính submittedFile

    // Constructor không tham số
    public project() {
    }

	public int getIdProject() {
		return idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Timestamp getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(Timestamp submissionTime) {
		this.submissionTime = submissionTime;
	}
	public String getFormattedSubmissionTime() {
	    return submissionTime != null ? submissionTime.toString() : "Chưa nộp";
	}
	public String getSubmittedFile() {
		return submittedFile;
	}

	public void setSubmittedFile(String submittedFile) {
		this.submittedFile = submittedFile;
	}

	public project(int idProject, String title, String description, String teacherName, String studentName,
			Timestamp submissionTime, String submittedFile) {
		super();
		this.idProject = idProject;
		this.title = title;
		this.description = description;
		this.teacherName = teacherName;
		this.studentName = studentName;
		this.submissionTime = submissionTime;
		this.submittedFile = submittedFile;
	}

  
    
}


