package DTODAOPattern.CourseDesign;

public class CourseDto {
    public int getCourseId() {
        return courseId;
    }



    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCourseFees() {
        return courseFees;
    }

    public void setCourseFees(double courseFees) {
        this.courseFees = courseFees;
    }

    private int courseId;
    private String courseName;
    private double courseFees;
}
