package CallableStatement.StoredFunction;

import java.sql.*;
import java.util.Scanner;

public class AddTwoNumbers {
    public static void main(String[] args) {
        Connection con =null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
            CallableStatement cs = null;
            cs = con.prepareCall("{? =call addTwoNumbers(?,?) }");
            cs.setInt(2,20);
            cs.setInt(3,30);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            System.out.println(cs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static class PercentageCal {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the Marks for Math");
            int num1 = sc.nextInt();
            System.out.println("Enter the Marks for physics");
            int num2 = sc.nextInt();
            System.out.println("Enter the Marks for chemistry");
            int num3 = sc.nextInt();
            Connection con =null;
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
                CallableStatement cs = null;
                cs = con.prepareCall("{? =call PercentageCal(?,?,?) }");
                cs.setInt(2,num1);
                cs.setInt(3,num2);
                cs.setInt(4,num3);
                cs.registerOutParameter(1, Types.VARCHAR);
                cs.execute();
                System.out.println("percentage is : " + cs.getString(1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
