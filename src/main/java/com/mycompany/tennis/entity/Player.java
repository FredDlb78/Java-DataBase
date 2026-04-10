package com.mycompany.tennis.entity;

import javax.persistence.*;

@Entity
@Table(name = "JOUEUR")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NOM")
    private String lastname;
    @Column(name = "PRENOM")
    private String firstname;
    @Column(name = "SEXE")
    private Character sex;

    public Long getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public Character getSex() {
        return sex;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

}
