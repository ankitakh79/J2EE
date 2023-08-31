package DTODAOPattern.EmployeeDesign;

import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
      operations();
    }
    private static void operations(){
        boolean status= true;
        while(status){
            System.out.println("Enter your choice : ");
            System.out.println("1.INSERT");
            System.out.println("2.UPDATE");
            System.out.println("3.DISPLAY ONLY NAME AND SAL");
            System.out.println("4.DISPALY ALL DETAILS");
            System.out.println("5.DISPLAY ACCORDING TO ID");
            System.out.println("6.EXIT");
            int ch = sc.nextInt();
            switch(ch){
                case 1:
                    insertMA();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    displayMA();
                    break;
                case 5:
                    break;
                case 6:
                    break;

            }

        }
    }

    public static int insertMA(){
        System.out.println("Enter the emp id");
        int id = sc.nextInt();
        System.out.println("Enter the emp name");
        String name = sc.next();
        System.out.println("Enter the emp desgn");
        String desgn = sc.next();
        System.out.println("Enter the emp sal");
        double sal = sc.nextDouble();
        EmpDTO empDto = new EmpDTO();
        empDto.setId(id);
        empDto.setName(name);
        empDto.setDesgn(desgn);
        empDto.setSal(sal);
        EmpDAO empDAO = new EmpDAO();
        int res = empDAO.insertRecords(empDto);
        if(res>0){
            System.out.println("Employee added sucessfully!!!!");
        }else{
            System.out.println("something went Wrong");
        }
        return res;
    }
    public static void displayMA(){
        EmpDAO empDAO = new EmpDAO();
       ArrayList<EmpDTO> rec = empDAO.DisplayRecords();
       for(EmpDTO empDTO : rec){
           System.out.println("Employee id :"+empDTO.getId());
           System.out.println("Employee Name : " + empDTO.getName());
           System.out.println("Employee Desgn : "+empDTO.getDesgn());
           System.out.println("Employee sal : "+empDTO.getSal());

       }
    }
}
