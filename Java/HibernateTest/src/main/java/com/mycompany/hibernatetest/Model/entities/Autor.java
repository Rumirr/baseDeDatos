/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernatetest.Model.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author horabaixa
 */
@Entity
@Table(name = "AUTOR")
public class Autor {

    @Id
    @Column(name = "IDAUTOR")
    @GeneratedValue
    private Long id;

    @Column(name = "NOM")
    private String nom;

    @OneToMany (mappedBy = "autor")
    private List<Llibre> llibres;

    public Autor() {
    }

    @Override
    public String toString() {
        return String.format("%d - %s", id, nom);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
