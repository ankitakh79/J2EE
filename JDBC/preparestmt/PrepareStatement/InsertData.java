package PrepareStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement psmt = null;
        String query = "insert into student values(1,'sam kolhe','A')";
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
            psmt = con.prepareStatement(query);
            int count = psmt.executeUpdate();
            System.out.println(count+"Data Inserted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
