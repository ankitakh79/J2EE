package Assignments.Employee;

import java.sql.*;
import java.util.Scanner;

public class Employee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        System.out.println("Enter the choice you wanted to display :");
        int ch = sc.nextInt();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
            cs = con.prepareCall("{call hikeDisplay(?)}");
            cs.setInt(1,ch);
            cs.execute();
            System.out.println("Your updation is done sucessfully!!!!!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
