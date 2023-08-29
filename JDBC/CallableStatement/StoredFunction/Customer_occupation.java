package CallableStatement.StoredFunction;

import java.sql.*;
import java.util.Scanner;

public class Customer_occupation {
    public static void main(String[] args) {
        Connection con =null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
            CallableStatement cs = null;
            cs = con.prepareCall("{ ? = call  Customer_occupation(?)}");
            cs.setInt(2,40);
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.execute();
            System.out.println(cs.getString(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Demo1 {
        public static void main(String[] args) {
            Connection con =null;
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
                CallableStatement cs = null;
                cs = con.prepareCall("{? =call Demo1(?) }");
                cs.setInt(2,1);
                cs.registerOutParameter(1, Types.VARCHAR);
                cs.execute();
                System.out.println(cs.getString(1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class EvenOdd {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the number");
            int num = sc.nextInt();
            Connection con =null;
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
                CallableStatement cs = null;
                cs = con.prepareCall("{? =call EvenOdd(?) }");
                cs.setInt(2,num);

                cs.registerOutParameter(1, Types.VARCHAR);
                cs.execute();
                System.out.println(num + "is "+cs.getString(1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
