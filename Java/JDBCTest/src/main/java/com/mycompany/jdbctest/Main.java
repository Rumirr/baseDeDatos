/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbctest;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author horabaixa
 */
public class Main {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://192.168.3.133:3306/world";
    static final String USER = "root";
    static final String PASS = "Ageofempires2";

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement st = null;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            String continent = "Africa";
            int poblacio = 5000000;

            String query = "select * from country where Continent = ? and Population > ?";
            st = connection.prepareStatement(query);
            st.setString(1, continent);
            st.setInt(2, poblacio);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String countryName = rs.getString("Name");
                int pblacioPais = rs.getInt("Population");
                System.out.println(countryName + " " + pblacioPais);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (connection != null) {
                    connection.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
