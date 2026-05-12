package Project;
import java.sql.*;


public class dbconnector  {
        public static void main(String[] args) throws Exception {

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hotel",
                    "root",
                    "imrankhan804943"
            );

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO booking VALUES (1, 'Test', 100)"
            );

            ps.executeUpdate();

            System.out.println("Inserted!");
        }
    }
