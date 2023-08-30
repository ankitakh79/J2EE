package BankMnagementSystem;

import java.sql.*;

public class BankOperations {
    static    Connection con= null;
    static int accountNumber = 0;
    static String userName=null;
    static{

        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean LoginValidation(String username, String password) {
        PreparedStatement psmt = null;
        String query = "select * from userlogin where username=? and user_password=?";
        ResultSet rs= null;
        try {
           psmt= con.prepareStatement(query);
            psmt.setString(1,username);
            psmt.setString(2,password);
            rs = psmt.executeQuery();
            if(rs.next()){
                userName=rs.getNString(2);
                accountNumber=rs.getInt(1);
                return true;
            }else{
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void checkBal() {
        PreparedStatement psmt = null;
        String query = "SELECT total_amt FROM bankaccountpassbook FULL JOIN userlogin ON acc_ref=acc_no WHERE acc_no=? ORDER BY transaction_id DESC LIMIT 1;";
        try {
            psmt= con.prepareStatement(query);
            ResultSet res = null;
            psmt.setDouble(1,accountNumber);
            res = psmt.executeQuery();
            if(res.next()){
                double ans =res.getDouble(1);
                System.out.println("Account bal =" + res.getDouble(1));

            }else {
                System.out.println("SOMETHING WENT WRONG");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deposite(double depo) {
        PreparedStatement psmt = null;
        String query = "SELECT total_amt FROM bankaccountpassbook FULL JOIN userlogin ON acc_ref=acc_no WHERE acc_no=? ORDER BY transaction_id DESC LIMIT 1;";
        try {
            psmt= con.prepareStatement(query);
            ResultSet res = null;
            psmt.setDouble(1,accountNumber);
            res = psmt.executeQuery();
            if(res.next()){
                double ans =res.getDouble(1)+depo;
                System.out.println("Account bal =" + (res.getDouble(1)+depo));

                psmt=con.prepareStatement("insert into bankaccountpassbook (total_amt,deposite,acc_ref,withdraw) values (?,?,?,?)");
                psmt.setDouble(1,ans);
                psmt.setDouble(2,depo);
                psmt.setDouble(3,accountNumber);
                psmt.setDouble(4,0);
                int x = psmt.executeUpdate();
                System.out.println(x + "amount deposited");

            }else {
                System.out.println("SOMETHING WENT WRONG");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void withdraw(double with) {
        PreparedStatement psmt = null;
        String query = "SELECT total_amt FROM bankaccountpassbook FULL JOIN userlogin ON acc_ref=acc_no WHERE acc_no=? ORDER BY transaction_id DESC LIMIT 1;";
        try {
            psmt= con.prepareStatement(query);
            ResultSet res = null;
            psmt.setDouble(1,accountNumber);
            res = psmt.executeQuery();
            if(res.next()){
                double ans =res.getDouble(1)-with;
                System.out.println("Account bal" + (res.getDouble(1)-with));

                psmt=con.prepareStatement("insert into bankaccountpassbook (total_amt,deposite,acc_ref,withdraw) values (?,?,?,?)");
                psmt.setDouble(1,ans);
                psmt.setDouble(2,0);
                psmt.setDouble(3,accountNumber);
                psmt.setDouble(4,with);
                int x = psmt.executeUpdate();
                System.out.println(x + "amount Withdraw");

            }else {
                System.out.println("SOMETHING WENT WRONG");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void accountTransactions() {
        PreparedStatement psmt=null;
        try {
            psmt = con.prepareStatement("select * from  bankaccountpassbook where acc_ref=?");
            psmt.setDouble(1,accountNumber);
            ResultSet res = null;
            res = psmt.executeQuery();
            System.out.println("---------------------TRANSACTION DETAILS------------------------");
            while(res.next()){

                System.out.println("Total amount : " +res.getDouble(2));
                System.out.println("Deposited amount : " + res.getDouble(3));
                System.out.println("Withdrawal amount : "+ res.getDouble(4));
                System.out.println("--------------------------------------------------------");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void moneyTransfer(int transferAcc, double transferAmount) {
        PreparedStatement psmt = null;
        String query = "SELECT total_amt FROM bankaccountpassbook FULL JOIN userlogin ON acc_ref=acc_no WHERE acc_no=? ORDER BY transaction_id DESC LIMIT 1;";
        try {
            psmt= con.prepareStatement(query);
            ResultSet res = null;
            psmt.setDouble(1,accountNumber);
            res = psmt.executeQuery();
            if(res.next()){
                //TOTAL BAL OF LOGGED ACCOUNT
                double loggedAccTAmount = res.getDouble(1);

                //RETRIEVE THE ACCOUNT WHICH WE WANT TO TRANSFER THE MONEY
                int transferAccNo = transferAcc;
                double transferAmt = transferAmount;
                String transferQuery = "select * from bankaccountpassbook where acc_ref=?";
                psmt = con.prepareStatement(transferQuery);
                psmt.setInt(1,transferAccNo);
                ResultSet transferRes = psmt.executeQuery();
                if(transferRes.next()){
                    double totalAmountOfTA = transferRes.getDouble("total_amt");
                    if(loggedAccTAmount >= transferAmt){
                        //deduct the transfer amt from logged-in user.
                        double updateLoggedInBal = loggedAccTAmount - transferAmt;

                        //add transfer amt to transfer acc
                        double updateTransferbal = totalAmountOfTA + transferAmt;

                        //update account detatils
//                        String updateQuery = "update bankaccountpassbook set total_amt=? where  acc_ref=?";
//                        PreparedStatement updatepsmt = con.prepareStatement(updateQuery);
//
//                        //update logged-in user
//                        updatepsmt.setDouble(1,updateLoggedInBal);
//                        updatepsmt.setInt(2,accountNumber);
//                        updatepsmt.executeUpdate();
//
//                        //update transfer acc
//                        updatepsmt.setDouble(1,updateTransferbal);
//                        updatepsmt.setInt(2,transferAccNo);
//                        updatepsmt.executeUpdate();
//
//                        System.out.println("Money transferred successfully!");

                        //inserting the details
                        String insert="insert into  bankaccountpassbook (total_amt,withdraw,acc_ref,transfer_into_acc,deposite) values(?,?,?,?,?)";
                        psmt= con.prepareStatement(insert);
                        psmt.setDouble(1,updateLoggedInBal);
                        psmt.setDouble(2,transferAmt);
                        psmt.setInt(3,accountNumber);
                        psmt.setInt(4,transferAcc);
                        psmt.setDouble(5,0);
                        int count = psmt.executeUpdate();

                        String insert2="insert into  bankaccountpassbook (total_amt,withdraw,acc_ref,transfer_into_acc,deposite) values(?,?,?,?,?)";
                        psmt= con.prepareStatement(insert2);
                        psmt.setDouble(1,updateTransferbal);
                        psmt.setDouble(2,0);
                        psmt.setInt(3,transferAccNo);
                        psmt.setInt(4,accountNumber);
                        psmt.setDouble(5,transferAmt);
                        int ct = psmt.executeUpdate();
                    }else{
                        System.out.println("Insufficient balance");
                    }
                }

            }else {
                System.out.println("SOMETHING WENT WRONG");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
