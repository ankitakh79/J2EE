package PrepareStatement.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudArrayList {

    public static void main(String[] args) {
        Connection con=null;
        PreparedStatement psmt = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("enter no of student you want to add :");
        int num = sc.nextInt();
       // var stud= new ArrayList<Student>();
        ArrayList <Student> stud = new ArrayList<>();
        for (int i=0;i<num;i++){
        System.out.println("Enter student roll number");
        int rollno = sc.nextInt();
        System.out.println("Enter student name");
        String name = sc.next();
        System.out.println("Enter student division");
        // String div = sc.next();
        char div = sc.next().charAt(0);
        stud.add(new Student(rollno,name,div));
        }
        System.out.println("data inserted ");
        System.out.println("Do you wnat to display the data Yes/No");
        String ans = sc.next();
        int count=0, sum = 0;
        if(ans.equalsIgnoreCase("yes")){
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
                psmt= con.prepareStatement("insert into student values(?,?,?)");
                for(int i=0;i<stud.size();i++){
                    psmt.setInt(1,stud.get(i).getRollNo());
                    psmt.setString(2,stud.get(i).getName());
                    psmt.setString(3,stud.get(i).getDiv()+"");
                   // psmt.addBatch();
                    count = psmt.executeUpdate();
                    sum = sum+ count;

                }
             //  int[] arr = psmt.executeUpdate();
                System.out.println(sum+" data inserted into db");

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("thank you for visiting");
        }
    }
}
