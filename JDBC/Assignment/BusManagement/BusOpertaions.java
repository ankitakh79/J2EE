package Assignments.BusManagement;

import java.sql.*;
import java.util.Scanner;

public class BusOpertaions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
//            System.out.println("Enter the source");
//            String src = sc.next();
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "mysql123");

                cs = con.prepareCall("{call DisplayData()}");
//                cs.setString(1, src);

                cs.execute();
                rs = cs.getResultSet();
                while (rs.next()) {
                    System.out.println("-------------------- BUS DETAILS ------------------------------");
                    System.out.println("Bus no : " + rs.getInt(1));
                    System.out.println("Bus name" + rs.getString(2));
                    System.out.println("Bus capacity : " + rs.getInt(3));
                    System.out.println("Bus route no : " + rs.getInt(4));
                    System.out.println("source :" + rs.getString(5));
                    System.out.println("Destination : " + rs.getString(6));
                    System.out.println("no of Stations : " + rs.getInt(7));
                    System.out.println("--------------------------------------------------");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }




}
