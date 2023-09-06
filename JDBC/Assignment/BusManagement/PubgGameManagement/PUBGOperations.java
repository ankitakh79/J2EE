package Assignments.PubgGameManagement;

import java.sql.*;

public class PUBGOperations {
    static Connection con = null;

    static {
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","mysql123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void worstDamage() {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String query = "SELECT Weapon_Name,damage FROM pubg ORDER BY damage ASC LIMIT 1;";
        try {
            psmt = con.prepareStatement(query);
            rs = psmt.executeQuery();
            if(rs.next()){
                System.out.println("------------WORST DAMAGE-----------------------------");
                System.out.println("WEAPON NAME : " + rs.getString(1));
                System.out.println("DAMAGE : "+ rs.getInt(2));
                System.out.println("---------------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void range() {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM pubg WHERE Renge BETWEEN 400 AND 600;";
        try {
            psmt = con.prepareStatement(query);
            rs = psmt.executeQuery();
            while(rs.next()){
                System.out.println("--------------------RANGE BETWEEN (400,600)---------------------------");
                System.out.println("WEAPON NAME : " + rs.getString(1));
                System.out.println("WEAPON TYPE : " + rs.getString(2));
                System.out.println("bULLET TYPE :" +rs.getDouble(3) );
                System.out.println("DAMAGE : "+ rs.getInt(4));
                System.out.println("MAX CAPACITY" + rs.getInt(5));
                System.out.println("RANGE" + rs.getInt(6));
                System.out.println("Shots to kill : " + rs.getInt(7));
                System.out.println("-----------------------------------------------");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void typesAndCount() {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String query = "SELECT COUNT(*), Weapon_Type FROM pubg GROUP BY Weapon_Type;";
        try {
            psmt = con.prepareStatement(query);
            rs = psmt.executeQuery();
            while(rs.next()){
                System.out.println("-------------------TYPES AND COUNT--------------------------");
                System.out.println("Count : " +rs.getInt(1));
                System.out.println("Weapon Type : " + rs.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void totalDamage() {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String query = "SELECT SUM(Damage) FROM (SELECT Damage FROM pubg WHERE Weapon_Type IN ('ASSAULT RIFFLE' ,'SHIPPER RIFFLE')) AS subquery;";
        try {
            psmt = con.prepareStatement(query);
            rs = psmt.executeQuery();
            if(rs.next()){
                System.out.println("------------------TOTAL DAMAGE-----------------------");
                System.out.println( rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void skippingFFInWeaponType() {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String query = "SELECT  Weapon_name,REPLACE(Weapon_Type, 'FF' , '') as types FROM pubg;";

        try {
            psmt = con.prepareStatement(query);
            rs = psmt.executeQuery();
            while ((rs.next())){
                System.out.println("Weapon name : " + rs.getString(1));
                System.out.println("Weapon type : " + rs.getString(2));
                System.out.println("----------------------------------------------");
            }
            if (rs.next()){
                System.out.println("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void nameNotContainingC() {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String query = "SELECT Weapon_name FROM pubg WHERE Weapon_name NOT  LIKE '%c%';";
        try {
            psmt = con.prepareStatement(query);
            rs = psmt.executeQuery();
            while(rs.next()){
                System.out.println("------------------WEAPON NAMES-----------------------");
                System.out.println("Weapon name : " + rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
