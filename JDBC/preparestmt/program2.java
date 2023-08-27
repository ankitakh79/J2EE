package preparestmt;

import java.sql.*;

public class program2 {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;


        String query = "select * from employee where employee_sal =(select max(employee_sal) from employee)";
        String query1 = "select * from employee where employee_sal=(select min(employee_sal) from employee)";

        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
            rs = con.createStatement().executeQuery(query);

            System.out.println("id\tname\tsal");
            if(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                double sal = rs.getDouble(3);

                System.out.println(id +" "+ name+" "+ "maximum sal =" + sal);
            }
            rs = con.createStatement().executeQuery(query1);
            if(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                double sal = rs.getDouble(3);

                System.out.println(id+" "+name+" "+"Minimum sal = "+sal);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
