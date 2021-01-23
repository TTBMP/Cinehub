package com.ttbmp.cinehub.core.entity;

/**
 * @author Massimo Mazzetti
 */

public class Cinema {
    private String name;


    public Cinema(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        Cinema other = (Cinema) obj;
        if (obj == null) {
            return false;
        }
        return this.name.equals(other.name);
    }
}
