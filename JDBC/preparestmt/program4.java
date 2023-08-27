package preparestmt;

import java.sql.*;
import java.util.Scanner;

public class program4 {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the usename");
        String name1 = sc.next();
        System.out.println("Enter the password ");
        String pass1 = sc.next();
        boolean status = false;
        String query = "select username,pass from login where username='"+name1+"' and pass= '"+pass1+"'";
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
            rs = con.createStatement().executeQuery(query);
            if(rs.next()){
                System.out.println("login successfull");
            }else{
                System.out.println("login not done");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
