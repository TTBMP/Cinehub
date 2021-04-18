package com.ttbmp.cinehub.service.persistence.entity;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.ColumnInfo;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Entity;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.PrimaryKey;
/**
 * @author Ivan Palmieri
 */
@Entity(tableName = "biglietto")
public class Ticket {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "id_posto")
    private int seatId;

    @ColumnInfo(name = "id_proiezione")
    private int projectionId;

    @ColumnInfo(name = "id_utente")
    private String userId;

    @ColumnInfo(name = "prezzo")
    private double price;



    public Ticket() {
    }

    public Ticket(int id, int seatId, int projectionId, String userId, Double price ) {
        this.id = id;
        this.seatId = seatId;
        this.projectionId = projectionId;
        this.userId = userId;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
