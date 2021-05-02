package com.ttbmp.cinehub.service.persistence.entity;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.ColumnInfo;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Entity;

/**
 * @author Palmieri Ivan
 */
@Entity(tableName = "posto")
public class Seat {

    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "id_sala")
    private int hallId;

    @ColumnInfo(name = "posizione")
    private String position;

    public Seat() {
    }

    public Seat(int id, int hallId, String position) {
        this.id = id;
        this.hallId = hallId;
        this.position = position;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
