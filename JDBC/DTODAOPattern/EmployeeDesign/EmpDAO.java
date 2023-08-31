package DTODAOPattern.EmployeeDesign;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class EmpDAO {
    static Connection con;
    static {
        try {
          con=  DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb","root","mysql123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String insertQuery = "insert into employe2 values(?,?,?,?)";
    private static final String display = "select * from employe2";
    public int insertRecords(EmpDTO empDto) {
        PreparedStatement psmt;
        try {
            psmt=con.prepareStatement(insertQuery);
            psmt.setInt(1,empDto.getId());
            psmt.setString(2,empDto.getName());
            psmt.setString(3,empDto.getDesgn());
            psmt.setDouble(4,empDto.getSal());
            int count =  psmt.executeUpdate();
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void recordDisplay(){
        PreparedStatement psmt = null;
        ResultSet res = null;
        try {
            psmt= con.prepareStatement(display);
            res = psmt.executeQuery();
            EmpDTO empDTO = null;
            ArrayList<EmpDTO> arr = new ArrayList<>();
            while(res.next()){
                empDTO = new EmpDTO();
                empDTO.getId();
                empDTO.getName();
                empDTO.getDesgn();
                empDTO.getSal();
               arr.add(empDTO);



            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<EmpDTO> DisplayRecords() {
        PreparedStatement psmt;
        ResultSet res;
        ArrayList<EmpDTO> emp = new ArrayList<>();
        try {
            psmt = con.prepareStatement(display);
            res = psmt.executeQuery();
            EmpDTO empDTO = null;

            if(res.next()){
                empDTO = new EmpDTO();
                empDTO.setId(res.getInt(1));
                empDTO.setName(res.getString(2));
                empDTO.setDesgn(res.getString(3));
                empDTO.setSal(res.getDouble(4));
                emp.add(empDTO);
            }
            return emp;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
