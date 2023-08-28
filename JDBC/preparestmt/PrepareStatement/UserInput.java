package PrepareStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UserInput {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement psmt = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter student roll number");
        int rollno = sc.nextInt();
        System.out.println("Enter student name");
        String name = sc.next();
        System.out.println("Enter student division");
//        String div = sc.next();
        char div = sc.next().charAt(0);
        String query = "insert into student values(?,?,?)";
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
            psmt = con.prepareStatement(query);

            psmt.setInt(1,rollno);
            psmt.setString(1,name);
            psmt.setString(2,div+"");
            int count = psmt.executeUpdate();
            System.out.println(count+"Data Inserted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
