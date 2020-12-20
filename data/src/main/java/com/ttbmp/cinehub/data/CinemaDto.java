package com.ttbmp.cinehub.data;

import com.ttbmp.cinehub.data.utils.jdbc.annotation.ColumnInfo;
import com.ttbmp.cinehub.data.utils.jdbc.annotation.Entity;
import com.ttbmp.cinehub.data.utils.jdbc.annotation.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "cinema")
public class CinemaDto implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "nome")
    private String name;

    @ColumnInfo(name = "indirizzo")
    private String address;

    @ColumnInfo(name = "telefono")
    private String telephone;

    public CinemaDto() {
    }

    public CinemaDto(int id, String name, String address, String telephone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}