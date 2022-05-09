package org.example;
import java.sql.*;
public class DatabaseConnection {
    private final String DB_URL = "jdbc:mariadb://localhost:3306/jdbcactivity";
    private final String USER = "root";
    private final String PASS = "rootpassword";
    private final Driver driver = new org.mariadb.jdbc.Driver();

    protected Connection conn = null;
    protected  PreparedStatement ps = null;
    protected  ResultSet rs = null;

    public DatabaseConnection() {
    }
//    public void connect() {
//
//        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//             PreparedStatement ps = null;
//             ResultSet rs = null;){
//            //DriverManager.registerDriver(driver);
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
    public void connect() {

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //DriverManager.registerDriver(driver);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void dispose(){
        try{
            if(!rs.equals(null)){
                if(!rs.isClosed()) rs.close();
            }
            if(!ps.equals(null)){
                if(!ps.isClosed()) ps.close();
            }
            if(!conn.equals(null)){
                if(!conn.isClosed()){
                    conn.close();
                    System.out.println("Connection is Closed: " + conn.isClosed());
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
