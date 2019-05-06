/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernatetest.Model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author horabaixa
 */
@Entity
@Table (name  = "LLIBRE")
public class Llibre {
    
    @Id
    @Column (name = "IDLLIBRE")
    @GeneratedValue
    private Long id;
    
    @Column (name = "NOM")
    private String titol;
    
    @ManyToOne
    @JoinColumn (name = "IDAUTOR")
    private Autor autor;

    public Llibre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
    
    
}
