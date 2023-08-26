package createConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UserInsert {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the employee id");
        int id = sc.nextInt();
        System.out.println("Enter the employee name");
        String name = sc.next();
        System.out.println("Enter the employee salary");
        double sal = sc.nextDouble();

        Connection con = null;
        Statement stmt = null;
        String query = "insert into employee values("+id+",'"+name+"',"+sal+")";



        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
            int count = con.createStatement().executeUpdate(query);


            System.out.println(count + "data inserted" );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
