package BankMnagementSystem;

import java.util.Scanner;

public class Bank {
  static   Scanner sc = new Scanner(System.in);
    private static void operations(){
        boolean status = true;
        System.out.println("Enter the username");
        String username = sc.next();
        System.out.println("Enter the password");
        String password = sc.next();

        boolean res= BankOperations.LoginValidation(username,password);
        if(res==true){

            System.out.println("Login Successfull!!!!");
            while(status) {
                System.out.println("1.DEPOSITE AMMOUNT");
                System.out.println("2.WITHDRAW AMMOUNT");
                System.out.println("3.CHECK BALANCE");
                System.out.println("4.ACCOUNT STATEMENT");
                System.out.println("5.LOG OUT");
                System.out.println("6.MONEY TRANSFER");
                System.out.println("7.EXIT");
                System.out.println("Enter your choice");
                int ch = sc.nextInt();
                switch (ch){
                    case 1:
                        System.out.println("enter the amount you want to deposite");
                        double depo = sc.nextDouble();
                        BankOperations.deposite(depo);
                        break;
                    case 2:
                        System.out.println("enter the amount you want to Withdraw");
                        double with = sc.nextDouble();
                        BankOperations.withdraw(with);
                        break;
                    case 3:
                        BankOperations.checkBal();
                        break;
                    case 4:
                        BankOperations.accountTransactions();
                        break;
                    case 5:
                        System.out.println("Thank you fro visiting!!!!");
                        BankOperations.userName= null;
                        BankOperations.accountNumber=0;
                        operations();
                        break;
                    case 7:
                        status= false;
                        System.exit(0);
                        break;
                    case 6:
                        System.out.println("Enter the account number for which you want to transfer the money");
                        int transferAcc = sc.nextInt();
                        System.out.println("Enter the amount you want to transfer");
                        double transferAmount = sc.nextDouble();
                        BankOperations.moneyTransfer(transferAcc,transferAmount);
                        break;
                    default:
                        System.out.println("Insufficient data");
                        break;
                }
            }
        }else{
            System.out.println("Login Invalid");
            operations();
        }
    }

    public static void main(String[] args) {

        operations();
    }
}
