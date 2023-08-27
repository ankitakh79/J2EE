package preparestmt;

import java.sql.*;
import java.util.Scanner;

public class Program1 {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null ;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the maximum sal");
        double maxSal = sc.nextDouble();
        System.out.println("Enter the minimum sal");
        double minSal = sc.nextDouble();
        String query = "select * from employee where employee_sal between "+maxSal+" and "+minSal+"";
        System.out.println("salaries");
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
            rs = con.createStatement().executeQuery(query);
            while(rs.next()){
                double sal = rs.getDouble(1);
                System.out.println("salaries" + sal);
                String name = rs.getString(2);
                System.out.println("name " + name);
                int id = rs.getInt(3);
                System.out.println("id" + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
