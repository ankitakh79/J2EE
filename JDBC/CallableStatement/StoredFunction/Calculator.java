package CallableStatement.StoredFunction;

import java.sql.*;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first number");
        int num1 = sc.nextInt();
        System.out.println("Enter the second number");
        int num2 = sc.nextInt();
        System.out.println("enter the Choice");
        int ch = sc.nextInt();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
            CallableStatement cs = null;
            cs = con.prepareCall("{ ? = call Calculator(?,?,?)}");
            cs.setInt(2,num1);
            cs.setInt(3,num2);
            cs.setInt(4,ch);
            cs.registerOutParameter(1, Types.DOUBLE);
            cs.execute();
            System.out.println("Answer is " + cs.getDouble(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
