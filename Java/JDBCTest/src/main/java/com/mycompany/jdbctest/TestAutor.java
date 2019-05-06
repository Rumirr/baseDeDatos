/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbctest;

import com.mycompany.jdbctest.model.Autor;
import com.mycompany.jdbctest.model.AutorCRUD;

/**
 *
 * @author horabaixa
 */
public class TestAutor {

    public static void main(String[] args) {
        Autor a = new Autor();
        a.setNom("Autor java");
        AutorCRUD.insert(a);
    }
}
