package DTODAOPattern.CourseDesign;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
    private static Scanner sc = new Scanner(System.in);
    static CourseDao ca = new CourseDao();
    public static void main(String[] args) {
        operations();
    }

    private static void operations() {
        boolean status = true;
        while (status) {
            System.out.println("Enter the choice");
            System.out.println("1.INSERT");
            System.out.println("2.DISPLAY");
            System.out.println("3.UPDATE");
            System.out.println("4.RESET THE COURSE NAME ");
            System.out.println("5.DELETE");
            System.out.println("6.EXIT");
            int ch = sc.nextInt();
            switch (ch){
                case 1:
                    insertedRecords();
                    break;
                case 2:
                    displayRecords();
                    break;
                case 3:
                    updateRecord();
                    break;
                case 4:
                    updateName();
                    break;
                case 5:
                    displayNamesA();
                    break;
                case 6:
                    status= false;
                    System.exit(0);
                    System.out.println("Thank you for visiting");
                    break;

            }
        }


    }
    private static void insertedRecords(){
        System.out.println("enter the id");
        int cid = sc.nextInt();
        System.out.println("enter the course name ");
        String cname = sc.next();
        System.out.println("enter the course fees ");
        double cfees = sc.nextDouble();
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseId(cid);
        courseDto.setCourseName(cname);
        courseDto.setCourseFees(cfees);
        int res = ca.insertRecord(courseDto);
        if(res>0){
            System.out.println("record inserted successfully!!!");
        }else{
            System.out.println("Data is not inserted");
        }

    }
    private static  void displayRecords(){
        System.out.println("Enter the id ");
        int cid = sc.nextInt();
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseId(cid);
        ResultSet resultSet = ca.display(courseDto);

        try {
            if(resultSet.next()){
                System.out.println("Course id" + "\t"+"Course Name" +"\t"+"Course Fees" + "\t");
               System.out.println("---------------------------------------------------------");
                System.out.println(resultSet.getInt(1)+"\t "+"\t"+resultSet.getString(2)+"\t"+" \t"+resultSet.getDouble(3)+"\t"+" \t");

//                System.out.println("------------------------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private static  void updateRecord(){
        System.out.println("enter the id");
        int cid = sc.nextInt();
        System.out.println("enter the course name ");
        String cname = sc.next();
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseId(cid);
        courseDto.setCourseName(cname);
        int res = ca.Updated(courseDto);
        if(res>0){
            System.out.println("record updated successfully!!!");
        }else{
            System.out.println("Data is not inserted");
        }
    }

    private static void updateName(){

        System.out.println("Enter the old name ");
        String oName = sc.next();
        System.out.println("Enter the new name");
        String nName = sc.next();
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName(nName);
        int res = ca.UpdatedName(courseDto,oName);
        if(res>0){
            System.out.println("Name updated successfully!!!");
        }else{
            System.out.println("Name is not available");
        }
    }
    private static void displayNamesA(){
        ArrayList<String> arr = ca.displayNames();
        for(String data:arr){
            System.out.println(data);
        }
    }

    private static void displaydata(){
        ArrayList<CourseDto> a = ca.displayAllData();
      for(CourseDto data:a){
          System.out.print(data.getCourseId());
          System.out.println(data.getCourseName());
          System.out.println(data.getCourseFees());
      }
    }
}