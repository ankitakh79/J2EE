package CallableStatement.StoredFunction;

import java.sql.*;

public class Maxnum {
    public static void main(String[] args) {
        Connection con =null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
            CallableStatement cs = null;
            cs = con.prepareCall("{?= call Maxnum(?,?,?)}");
            cs.setInt(2,77);
            cs.setInt(3,90);
            cs.setInt(4,88);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            System.out.println(cs.getInt(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
