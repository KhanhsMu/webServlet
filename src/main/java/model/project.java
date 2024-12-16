package model;

public class project {
    private int idProject;        
    private String title;         
    private String description;   
    private String teacherName;   
    private String studentName;   

    // Constructor không tham số
    public project() {
    }

    // Constructor có tham số
    public project(int idProject, String title, String description, String teacherName, String studentName) {
        this.idProject = idProject;
        this.title = title;
        this.description = description;
        this.teacherName = teacherName;
        this.studentName = studentName;
    }

    // Getter và Setter
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

}


