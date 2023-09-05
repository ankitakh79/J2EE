package Assignments.IPLMatchManagement;

import java.sql.*;

public class MatchesOperations {
    static Connection con= null;
    static{

        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","mysql123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void qualified() {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM ipl WHERE win >=8;";
        try {
            psmt= con.prepareStatement(query);
            rs = psmt.executeQuery();
            while(rs.next()){
                System.out.println("Number :" + rs.getInt(1));
                System.out.println("Team name :" + rs.getString(2));
                System.out.println("Matches Played : " + rs.getInt(3));
                System.out.println("Matches wins : " + rs.getDouble(4));
                System.out.println("Matches loss : " + rs.getDouble(5));
                System.out.println("Matches tied : " + rs.getInt(6));
                System.out.println("Run rate : " + rs.getDouble(7));
                System.out.println("-------------------------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void winAndLoss() {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM ipl WHERE win=loss;";
        try {
            psmt= con.prepareStatement(query);
            rs = psmt.executeQuery();
            while(rs.next()){
                System.out.println("Number :" + rs.getInt(1));
                System.out.println("Team name :" + rs.getString(2));
                System.out.println("Matches Played : " + rs.getInt(3));
                System.out.println("Matches wins : " + rs.getDouble(4));
                System.out.println("Matches loss : " + rs.getDouble(5));
                System.out.println("Matches tied : " + rs.getInt(6));
                System.out.println("Run rate : " + rs.getDouble(7));
                System.out.println("-------------------------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void tiedMatches() {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM ipl WHERE tied IS NOT NULL;";
        try {
            psmt= con.prepareStatement(query);
            rs = psmt.executeQuery();
            while(rs.next()){
                System.out.println("Number :" + rs.getInt(1));
                System.out.println("Team name :" + rs.getString(2));
                System.out.println("Matches Played : " + rs.getInt(3));
                System.out.println("Matches wins : " + rs.getDouble(4));
                System.out.println("Matches loss : " + rs.getDouble(5));
                System.out.println("Matches tied : " + rs.getInt(6));
                System.out.println("Run rate : " + rs.getDouble(7));
                System.out.println("-------------------------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void greaterWins() {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM ipl WHERE win >loss;";
        try {
            psmt= con.prepareStatement(query);
            rs = psmt.executeQuery();
            while(rs.next()){
                System.out.println("Number :" + rs.getInt(1));
                System.out.println("Team name :" + rs.getString(2));
                System.out.println("Matches Played : " + rs.getInt(3));
                System.out.println("Matches wins : " + rs.getDouble(4));
                System.out.println("Matches loss : " + rs.getDouble(5));
                System.out.println("Matches tied : " + rs.getInt(6));
                System.out.println("Run rate : " + rs.getDouble(7));
                System.out.println("-------------------------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void points() {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String query = "SELECT  ROW_NUMBER() OVER (ORDER BY (SUM(win) + IFNULL(SUM(tied), 0) / 2) DESC) AS ank ,\n" +
                "  team,(SUM(win) + IFNULL(SUM(tied), 0)/2 )*2 AS points FROM ipl GROUP BY team  ORDER BY points DESC;";
        try {
            psmt= con.prepareStatement(query);
            rs = psmt.executeQuery();
            while(rs.next()){
                System.out.println("Number :" + rs.getInt(1));
                System.out.println("Team name :" + rs.getString(2));
                System.out.println("points : " + rs.getDouble(3));
                System.out.println("-------------------------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void performance() {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String query = "SELECT ipl.team, ipl.win, ipl.tied, (SUM(ipl.win) + IFNULL(SUM(ipl.tied), 0) / 2) * 2 AS total_points, avg_table.avg_points\n" +
                "FROM ipl\n" +
                "JOIN (\n" +
                "    SELECT AVG(total_points) AS avg_points\n" +
                "    FROM (\n" +
                "        SELECT SUM(win) + IFNULL(SUM(tied), 0) / 2 AS total_points\n" +
                "        FROM ipl\n" +
                "        GROUP BY team\n" +
                "    ) AS subquery\n" +
                ") AS avg_table\n" +
                "GROUP BY ipl.team, ipl.win, ipl.tied, avg_table.avg_points\n" +
                "HAVING total_points = (SELECT MAX(total_points) FROM (SELECT team, (SUM(win) + IFNULL(SUM(tied), 0) / 2) * 2 AS total_points FROM ipl GROUP BY team) AS subquery)\n" +
                "   OR total_points = (SELECT MIN(total_points) FROM (SELECT team, (SUM(win) + IFNULL(SUM(tied), 0) / 2) * 2 AS total_points FROM ipl GROUP BY team) AS subquery);";
        try {
            psmt= con.prepareStatement(query);
            rs = psmt.executeQuery();
            while(rs.next()){
                if(rs.getDouble(2)>5){
                    System.out.println("BEST PERFORMANCE TEAM");
                    System.out.println("team name : " + rs.getString(1));
                    System.out.println("Matches win : " + rs.getDouble(2));
                    System.out.println("Matches Tied :" + rs.getInt(3));
                    System.out.println("Toatl Points : " + rs.getDouble(4));
                    System.out.println("Avg points : " + rs.getDouble(5));
                }
                if(rs.getDouble(2)<5){
                    System.out.println("WORST PERFORMANCE TEAM");
                    System.out.println("team name : " + rs.getString(1));
                    System.out.println("Matches win : " + rs.getDouble(2));
                    System.out.println("Matches Tied :" + rs.getInt(3));
                    System.out.println("Toatl Points : " + rs.getDouble(4));
                    System.out.println("Avg points : " + rs.getDouble(5));
                }

                System.out.println("-------------------------------------------------");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //AVERAGE TEAM
        String avgQuery = "SELECT * FROM (\n" +
                "  SELECT *, ROW_NUMBER() OVER (ORDER BY win DESC) AS ank\n" +
                "  FROM ipl\n" +
                ") AS subquery\n" +
                "WHERE ank = (\n" +
                "  SELECT CEIL(AVG(ank))\n" +
                "  FROM (\n" +
                "    SELECT ROW_NUMBER() OVER (ORDER BY win DESC) AS ank\n" +
                "    FROM ipl\n" +
                "  ) AS avg_subquery\n" +
                ");";
        try {
            psmt = con.prepareStatement(avgQuery);
            ResultSet res = null;
            res= psmt.executeQuery();
            if(res.next()){
                System.out.println("AVERAGE PERFORMANCE TEAM");
                System.out.println("Team name: " + res.getString(2));
                System.out.println("Total Matches : " + res.getInt(3));
                System.out.println("Matches win : " + res.getDouble(4));
                System.out.println("Matches loss : " + res.getDouble(5));
                System.out.println("Mateches tied : " + res.getInt(6));
                System.out.println("Run rate : " + res.getDouble(7));
            }
            System.out.println("-------------------------------------------------");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

}


    public static void avgTeam() {
        PreparedStatement psmt = null;
        String avgQuery = "SELECT * FROM (\n" +
                "  SELECT *, ROW_NUMBER() OVER (ORDER BY win DESC) AS ank\n" +
                "  FROM ipl\n" +
                ") AS subquery\n" +
                "WHERE ank = (\n" +
                "  SELECT CEIL(AVG(ank))\n" +
                "  FROM (\n" +
                "    SELECT ROW_NUMBER() OVER (ORDER BY win DESC) AS ank\n" +
                "    FROM ipl\n" +
                "  ) AS avg_subquery\n" +
                ");";
        try {
            psmt = con.prepareStatement(avgQuery);
            ResultSet res = null;
            res= psmt.executeQuery();
            if(res.next()){
            System.out.println("AVERAGE PERFORMANCE TEAM");
            System.out.println("Team name: " + res.getString(2));
            System.out.println("Total Matches : " + res.getInt(3));
            System.out.println("Matches win : " + res.getDouble(4));
            System.out.println("Matches loss : " + res.getDouble(5));
            System.out.println("Mateches tied : " + res.getInt(6));
            System.out.println("Run rate : " + res.getDouble(7));
        }
        } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }
}
