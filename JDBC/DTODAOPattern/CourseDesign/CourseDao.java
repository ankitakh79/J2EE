package DTODAOPattern.CourseDesign;

import java.sql.*;
import java.util.ArrayList;

public class CourseDao {
    static Connection con ;

    static{
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","mysql123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String insertRecords="insert into courses values(?,?,?)";
    private static final String displayRecords = "select * from courses where course_id=?";
    private static final String updateRecords = "update courses set course_name=? where course_id=?";
    private static final String updateNames = "update courses set course_name=? where course_name=?";
    private static final String displayAll = "select * from courses";
    private static final String display = "select course_name from courses ";

    public  int insertRecord(CourseDto c1){
        PreparedStatement psmt ;
        try {
            psmt = con.prepareStatement(insertRecords);
            psmt.setInt(1,c1.getCourseId());
            psmt.setString(2,c1.getCourseName());
            psmt.setDouble(3,c1.getCourseFees());
            int count = psmt.executeUpdate();
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet display(CourseDto c1){
        PreparedStatement psmt ;
        ResultSet res;
        try {
            psmt = con.prepareStatement(displayRecords);
            psmt.setInt(1,c1.getCourseId());
           res= psmt.executeQuery();
           return res;
//            if(res.next()){
//                System.out.println( res.getInt(1));
//                System.out.println(res.getString(2));
//                System.out.println(res.getDouble(3));
//
//            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public int Updated(CourseDto c1) {
        PreparedStatement psmt ;
        try {
            psmt = con.prepareStatement(updateRecords);
            psmt.setInt(2,c1.getCourseId());
            psmt.setString(1,c1.getCourseName());
            int count = psmt.executeUpdate();
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int UpdatedName(CourseDto c1, String oName) {
        PreparedStatement psmt ;
        try {
            psmt = con.prepareStatement(updateNames);

            psmt.setString(1,c1.getCourseName());
            psmt.setString(2, oName);

            int count = psmt.executeUpdate();
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> displayNames(){
        PreparedStatement psmt;
        ResultSet res;
        ArrayList<String> data = new ArrayList<>();
        try {
            psmt = con.prepareStatement(display);
            res= psmt.executeQuery();
            CourseDto cd = new CourseDto();
            while(res.next()){
                String name = res.getString(1);
                data.add(name);
            }
            return  data;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<CourseDto> displayAllData(){
        PreparedStatement psmt;
        ResultSet res;
        ArrayList<CourseDto> data = new ArrayList<>();
        try {
            psmt = con.prepareStatement(displayAll);
            res= psmt.executeQuery();
            CourseDto cd = null;
            while(res.next()){
                cd = new CourseDto();
                int id = res.getInt(1);
                String name = res.getString(2);
                double fees = res.getDouble(3);
              cd.setCourseId(id);
              cd.setCourseName(name);
              cd.setCourseFees(fees);
              data.add(cd);
            }
            return  data;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
