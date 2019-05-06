/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbctest.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;

/**
 *
 * @author horabaixa
 */
public class AutorCRUD {

    public static void insert(Autor autor) {

        Connection con = null;
        PreparedStatement st = null;

        try {
            con = SessionFactory.getSession();

            String query = "insert into AUTOR(NOM) values(?)";
            st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, autor.getNom());

            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();

            if (rs.next()) {
                autor.setIdAutot(rs.getInt(1));
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AutorCRUD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                SessionFactory.closeSession(con);
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AutorCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static ArrayList<Autor> listAuthors() {

        ArrayList<Autor> autors = new ArrayList<Autor>();

        Connection con = null;
        PreparedStatement st = null;
        try {
            con = SessionFactory.getSession();
            
            String sql = "select * from AUTOR";
            
            st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            
            while (rs.next()) {
                Autor a = new Autor();
                a.setIdAutot(rs.getInt("IDAUTOR"));
                a.setNom(rs.getString("NOM"));
                
                autors.add(a);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AutorCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return autors;
    }

}
