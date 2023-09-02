package Assignments.CustomerSP;

import java.sql.*;

public class CustomerO {
    static Connection con= null;
    static{

        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","mysql123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static  void insert(int id, String name, int age, double sal, String add){
        CallableStatement cs = null;
        try {
            cs = con.prepareCall("{call CInsert(?,?,?,?,?) }");
            cs.setInt(1,id);
            cs.setString(2,name);
            cs.setInt(3,age);
            cs.setDouble(4,sal);
            cs.setString(5,add);
            cs.execute();
            System.out.println("Your data inserted successfully!!!!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }


    public static void Display() {
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs=con.prepareCall("{call CDisplay()}");


//            cs.registerOutParameter(1, Types.VARCHAR);

            cs.execute();
            rs= cs.getResultSet();
            if(rs.next()){
                System.out.println(rs.getString(1));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
