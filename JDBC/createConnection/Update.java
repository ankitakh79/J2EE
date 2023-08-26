package createConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Update {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the employee id");
        int id = sc.nextInt();
        System.out.println("Enter the employee sal");
        double sal = sc.nextDouble();

        Connection con = null;
        Statement stmt = null;
         String query = "update employee set employee_sal = "+sal+" where employee_id = "+id+" ";

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
            int count = con.createStatement().executeUpdate(query);
            if(count >0){
                System.out.println(count + "data updated");
            }else{
                System.out.println("data not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
