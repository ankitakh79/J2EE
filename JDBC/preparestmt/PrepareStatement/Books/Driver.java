package PrepareStatement.Books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Connection con= null;
        PreparedStatement psmt = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of books that you want to insert");
        int size = sc.nextInt();
        ArrayList<Book> book = new ArrayList<>();
        for(int i=0;i<size;i++){
            System.out.println("enter book id");
            int id= sc.nextInt();
            System.out.println("enter book name");
            String name = String.valueOf(sc.next().split(" "));
            System.out.println("enter book author");
            String author = String.valueOf(sc.next().split(" "));

           book.add(new Book(id,name,author));
        }
        for(Book b:book){
            System.out.println(b);
        }
        System.out.println("Do you want to store this data in your database: yes/no");
        String ans = sc.next();
        int count = 0, sum = 0;
        if(ans.equalsIgnoreCase("yes")){
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
                psmt=con.prepareStatement("insert into book values(?,?,?)");
                for(int i=0;i<book.size();i++)
                {
                    psmt.setInt(1,book.get(i).getBook_id());
                    psmt.setString(2,book.get(i).getBook_name());
                    psmt.setString(3,book.get(i).getBook_author());
                    count = psmt.executeUpdate();

                }
                sum = sum+count;
                System.out.println(sum + "data inserted");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }else {
            System.out.println("Thank you for visiting!!!!");
        }
    }
}
