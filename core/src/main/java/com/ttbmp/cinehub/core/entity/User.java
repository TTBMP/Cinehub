package com.ttbmp.cinehub.core.entity;

/**
 * @author Fabio Buracchi
 */
public class User {

    private int id;
    private String name;
    private String surname;

    public User() {

    }

    public User(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
