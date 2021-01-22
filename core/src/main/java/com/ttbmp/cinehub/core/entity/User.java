package com.ttbmp.cinehub.core.entity;

/**
 * @author Fabio Buracchi
 */
public class User {

    private int id;
    private String name;

    public User() {

    }

    public User(int id) {
        this.id = id;
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
