package com.ttbmp.cinehub.service.persistence.entity;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.ColumnInfo;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Entity;

/**
 * @author Ivan Palmieri
 */
@Entity(tableName = "dipendente")
public class Employee {

    @ColumnInfo(name = "id_utente")
    private String idUser;

    @ColumnInfo(name = "id_cinema")
    private int cinemaId;

    @ColumnInfo(name = "ruolo")
    private String role;

    public Employee() {
    }

    public Employee(String idUser, int cinemaId, String role) {
        this.idUser = idUser;
        this.cinemaId = cinemaId;
        this.role = role;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
