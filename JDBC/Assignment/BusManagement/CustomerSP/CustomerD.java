package Assignments.CustomerSP;

import java.util.Scanner;

public class CustomerD {
   static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Enter the choice");
        System.out.println("1.Insert");
        System.out.println("2.Update");
        System.out.println("3.Display");
        System.out.println("4.Delete");
        System.out.println("5.Exit");
        boolean status = true;
        int ch = sc.nextInt();
        while(status){
        switch (ch){
            case 1:
                System.out.println("Enter the id");
                int id = sc.nextInt();
                System.out.println("Enter the name");
                String name = sc.next();
                System.out.println("Enter the age");
                int age = sc.nextInt();
                System.out.println("Enter the salary");
                double sal = sc.nextDouble();
                System.out.println("Enter the address");
                String add = sc.next();
                CustomerO.insert(id,name,age,sal,add);
                break;
            case 2:
//                System.out.println("enter the id");
//                int Iid = sc.nextInt();
                CustomerO.Display();
                break;
                case 3:
                break;
            case 4:
                break;
            case 5:
                status= false;
                System.exit(0);
                break;

        }}
    }
}
