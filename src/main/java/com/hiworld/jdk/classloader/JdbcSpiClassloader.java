package com.hiworld.jdk.classloader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcSpiClassloader {

    public static void main(String[] args) {
        
//        String driver = "com.mysql.jdbc.Driver";
        String URL = "jdbc:mysql://10.199.128.61:13306/vip_udp";  
        
        String username = "vip_udp";
        String password = "udppvip";
        
        Connection connection = null;
        try {
//            Class.forName(driver);
            connection = DriverManager.getConnection(URL, username, password);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from field_offline a where a.field_classify='PD_SHARE'");
            while(rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } 
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        
        System.out.println("over...");
    }

}
