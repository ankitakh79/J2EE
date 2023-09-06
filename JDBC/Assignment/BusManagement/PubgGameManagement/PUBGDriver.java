package Assignments.PubgGameManagement;

import java.util.Scanner;

public class PUBGDriver {
    static Scanner sc = new Scanner(System.in);
    static boolean status = true;
    public static void operations(){
        while(status) {
            System.out.println("ENTER YOUR CHOICE");
            System.out.println("1.DISPLAY WAEPON NAME WHOSE DAMAGE IS WORST.");
            System.out.println("2.DISPLAY THE WEAPON DETAILS WHOSE RANGE IS BETWEEN 400 TO 600.");
            System.out.println("3.DISPLAY THE WEAPON TYPE AND THEIR COUNT PRESENT INSIDE THE TABLE.");
            System.out.println("4.DISPLAY THE TOTAL DAMAGE DONE BY ASSAULT RIFFLE AND SHRIPPER RIFFLE.");
            System.out.println("5.DISPLAY THE WEAPON NAMES WHICH DOES NOT CONTAIN 'C' CHARACTER.");
            System.out.println("6.DISPLAY ALL THE WEAPON NAME AND WEAPON TYPE AFTER REMAINING 'FF' FROM THEIR NAME.");
            System.out.println("7.EXIT");
            int ch = sc.nextInt();
            switch (ch){
                case 1:
                    PUBGOperations.worstDamage();
                    break;
                case 2:
                    PUBGOperations.range();
                    break;
                case 3:
                    PUBGOperations.typesAndCount();
                    break;
                case 4:
                    PUBGOperations.totalDamage();
                    break;
                case 5:
                    PUBGOperations.nameNotContainingC();
                    break;
                case 6:
                    PUBGOperations.skippingFFInWeaponType();
                    break;
                case 7:
                    status = false;
                    break;
            }
        }
    }
    public static void main(String[] args) {
     operations();
    }
}
