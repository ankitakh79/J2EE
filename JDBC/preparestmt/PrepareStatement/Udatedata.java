package PrepareStatement;

import java.sql.*;
import java.util.Scanner;

public class Udatedata {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet res = null;
        Scanner sc = new Scanner(System.in);

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb", "root", "mysql123");
            System.out.println("1.Inset the data /n 2.Display the data /n 3.update the data /n 4.delete the data");
            System.out.println("Enter your choice:");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    //Inset the values

                    psmt = con.prepareStatement("insert into emp values(?,?,?)");
                    System.out.println("Enter emp id");
                    int id = sc.nextInt();
                    System.out.println("enter emp name");
                    String name = sc.next();
                    System.out.println("Enter emp sal");
                    double sal = sc.nextDouble();
                    psmt.setInt(1, id);
                    psmt.setString(2, name);
                    psmt.setDouble(3, sal);
                    int count = psmt.executeUpdate();
                    System.out.println(count + "table is created");
                    break;
                case 2:
                    psmt = con.prepareStatement("select * from emp where emp_id=?");

                    System.out.println("enter the id of employee whose details you wanted");
                    int sid = sc.nextInt();
                    psmt.setInt(1, sid);
                    res = psmt.executeQuery();
                    if (res.next()) {
                        int eid = res.getInt(1);
                        String ename = res.getString(2);
                        double esal = res.getDouble(3);
                        System.out.println("Employee details");
                        System.out.println("Employee id is " + eid);
                        System.out.println("Employee name is " + ename);
                        System.out.println("Employee sal is " + esal);
                        System.out.println("-------------------------");

                    } else {
                        System.out.println("user not found");
                    }
                    break;

                case 3:
                    boolean status = true;
                    while (status) {
                        System.out.println("1.UPDATE THE EMP NAME");
                        System.out.println("2.UPDATE THE EMP SAL");
                        System.out.println("3.UPDATE THE EMP ID");
                        System.out.println("4.Exit");
                        System.out.println("Enter the choice:");
                        int choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                psmt = con.prepareStatement("update emp set emp_name=? where emp_name=?");
                                System.out.println("enter the name you wanted update");
                                String setName = sc.next();
                                System.out.println("Enter the previous name");
                                String prevName = sc.next();
                                psmt.setString(1, setName);
                                psmt.setString(2, prevName);
                                psmt.executeUpdate();
                                System.out.println("emp name updated successfully!!!");
                                break;
                            case 2:
                                psmt = con.prepareStatement("update emp set emp_sal=? where emp_id=?");
                                System.out.println("enter the sal you wanted update");
                                double setSal = sc.nextDouble();
                                System.out.println("Enter the id");
                                int previd = sc.nextInt();
                                psmt.setDouble(1, setSal);
                                psmt.setInt(2, previd);
                                psmt.executeUpdate();
                                System.out.println("emp salary updated successfully!!!");
                                break;
                            case 3:
                                psmt = con.prepareStatement("update emp set emp_id=? where emp_id=?");
                                System.out.println("enter the id you wanted update");
                                int setid = sc.nextInt();
                                System.out.println("Enter the previous id");
                                int prevId = sc.nextInt();
                                psmt.setInt(1, setid);
                                psmt.setInt(2, prevId);
                                psmt.executeUpdate();
                                break;
                            case 4:
                                status = false;
                                break;
                        }
                        break;
                    }
                case 4:
                    psmt= con.prepareStatement("delete from emp where emp_id=? or emp_name=? or emp_sal=?");
                    System.out.println("enter the user you want to delete ");
                    int delId= sc.nextInt();
                    psmt.setInt(1,delId);
                    psmt.executeUpdate();
                    System.out.println("user deleted successfully!!!!");
                break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
