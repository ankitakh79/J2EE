package Assignments.Employee;

import java.sql.*;
import java.util.Scanner;

class EmployeeOperation{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
            cs = con.prepareCall("{call Name()}");
            cs.execute();
            rs = cs.getResultSet();
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


