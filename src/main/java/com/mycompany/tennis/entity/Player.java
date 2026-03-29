package com.mycompany.tennis.entity;

public class Player {

    private Long id;
    private String lastname;
    private String firstname;
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
