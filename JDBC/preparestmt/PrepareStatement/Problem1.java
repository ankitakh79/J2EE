package PrepareStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement psmt = null;
        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter student roll number");
//        int rollno = sc.nextInt();
        System.out.println("Enter student name");
        String name = sc.next();
        System.out.println("Enter student division");
//        String div = sc.next();
        char div = sc.next().charAt(0);
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
            psmt = con.prepareStatement("insert into student values(?,?,?)");
            psmt.setInt(1,0);
            psmt.setString(2,name);
            psmt.setString(3, String.valueOf(div));
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
