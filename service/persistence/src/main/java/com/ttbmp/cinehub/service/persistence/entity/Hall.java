package com.ttbmp.cinehub.service.persistence.entity;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.ColumnInfo;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Entity;

/**
 * @author Ivan Palmieri, Massimo Mazzetti
 */
@Entity(tableName = "sala")
public class Hall {

    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "id_cinema")
    private int cinemaId;

    @ColumnInfo(name = "numero")
    private String number;

    public Hall() {
    }

    public Hall(int id, int cinemaId, String number) {
        this.id = id;
        this.cinemaId = cinemaId;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
