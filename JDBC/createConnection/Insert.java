package createConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        String url = "jdbc:mysql://localhost:3306/firstdb";
        String username = "root";
        String password = "mysql123";
        String query = "delete from employee where employee_id=2";
        String query1 = "insert into employee values(5,'Anki',2500.70)";

        try {
            con =  DriverManager.getConnection(url,username,password);

            stmt = con.createStatement();
            int count = stmt.executeUpdate(query);
            int count1 = stmt.executeUpdate(query1);
            System.out.println(count + "data inserted" + count1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
