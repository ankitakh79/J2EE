package CallableStatement.StoredProcedure;

import java.sql.*;
import java.util.Scanner;

public class InsetDisplay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the id : ");
        int id = sc.nextInt();
        System.out.println("Enter the name : ");
        String name = sc.next();
        System.out.println("Enter the address : ");
        String add = sc.next();
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
            cs = con.prepareCall("{call First(?,?,?)}");
            cs.setInt(1,id);
            cs.setString(2,name);
            cs.setString(3,add);
//            cs.registerOutParameter(4,Types.VARCHAR);

//            boolean status =cs.execute();
            cs.execute();
            System.out.println("The data inserted sucessfully");
            rs = cs.getResultSet();
//            if(status){

            while(rs.next()){
                System.out.println( "id :"+ rs.getInt(1));
                System.out.println("name : "+rs.getString(2));
                System.out.println("Designation :"+rs.getString(3));
                System.out.println("---------------------------------------------");
            }
//        }else{
//                System.out.println("There is no data to display");
//            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
