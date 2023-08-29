package CallableStatement.StoredFunction;

import java.sql.*;

public class AreaOfCircle {
    public static void main(String[] args) {
        Connection con =null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
            CallableStatement cs = null;
            cs = con.prepareCall("{ ? = call AreaOfCircle(?)}");
            cs.setDouble(2,9.0);
            cs.registerOutParameter(1, Types.DOUBLE);
            cs.execute();
            System.out.println(cs.getDouble(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
