package preparestmt;

import java.sql.*;

public class First {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        String query = "select * from employee";

        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
            rs = con.createStatement().executeQuery(query);
            System.out.println("id\tname\tsal");
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                double sal = rs.getDouble(3);

                System.out.println(id+"\t"+name+"\t"+sal+"\t");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
