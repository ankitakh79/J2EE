package preparestmt;

import java.sql.*;
import java.util.Scanner;

public class program3 {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the username");
        String name1 = sc.next();
        System.out.println("Enter the password ");
        String pass1 = sc.next();
        boolean status = false;
       String query = "select username,pass from login";


        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
            rs = con.createStatement().executeQuery(query);
            System.out.println(rs);
            System.out.println(con);

            while(rs.next()){

                String name = rs.getString(1);
                String pass = rs.getString(2);
                if(name.equals(name1) && pass.equals(pass1)){
                    System.out.println(name +" " + pass);
                    System.out.println("login is successfull");
                    status= true;
                }

            }
            if(!status){
                System.out.println("Login usuccessfull");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
