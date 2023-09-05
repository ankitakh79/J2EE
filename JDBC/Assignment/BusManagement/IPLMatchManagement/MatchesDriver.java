package Assignments.IPLMatchManagement;

import java.util.Scanner;

public class MatchesDriver {
   static Scanner sc = new Scanner(System.in);
   static boolean st = true;
    public static void operations()  {
        while(st) {
            System.out.println("Enter the Choice :");
            System.out.println("1.Display the 4 teams which are qualified.");
            System.out.println("2.Display team details whose performance is best/worst + avg.");
            System.out.println("3.Display teams  whose win & loss per is 50-50.");
            System.out.println("4.Display the team names who's matches are tied.");
            System.out.println("5.Display the teams whose winning matches are greater than as compare to losing mathes. ");
            System.out.println("6.Display all the teams according to the points");
            System.out.println("7.Exit");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    MatchesOperations.qualified();
                    break;
                case 2:
                    MatchesOperations.performance();
                    break;
                case 3:
                    MatchesOperations.winAndLoss();
                    break;
                case 4:
                    MatchesOperations.tiedMatches();
                    break;
                case 5:
                    MatchesOperations.greaterWins();
                    break;
                case 6:
                    MatchesOperations.points();
                    break;
                case 7:
                    st= false;
                    break;
                default:
                    System.out.println("Something went wrong!!!!!");
                    break;
            }
        }
    }
    public static void main(String[] args) {
        operations();
    }
}
