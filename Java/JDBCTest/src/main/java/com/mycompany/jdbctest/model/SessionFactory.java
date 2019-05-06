/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbctest.model;

import java.sql.*;

/**
 *
 * @author horabaixa
 */
public class SessionFactory {
    
    /*-------------------Manejat la sessió amb la BBDD---------------*/
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://192.168.3.133:3306/prova";
    private static final String USER = "root";
    private static final String PASS = "Ageofempires2";
    
    
    
    /*iniciar i tancar sessió*/
    public static Connection getSession() throws ClassNotFoundException, SQLException{
        
        Connection connection;
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DB_URL, USER,PASS);

        return connection;
    }
    
    public static void closeSession(Connection con) throws SQLException{
        if (con != null) {
            con.close();
        }
    }
    
}
