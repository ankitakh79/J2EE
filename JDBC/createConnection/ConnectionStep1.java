package createConnection;

public class ConnectionStep1 {

    public static void main(String[] args) {

        try {
            //load the driver
            Class.forName( "com.mysql.cj.jdbc.Driver");
            System.out.println("connection established");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
