package com.ttbmp.cinehub.service.persistence.entity;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.ColumnInfo;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Entity;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.PrimaryKey;

import java.sql.Time;
/**
 * @author Ivan Palmieri
 */
@Entity(tableName = "proiezione")
public class Projection {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "id_sala")
    private int hallId;

    @ColumnInfo(name = "id_proiezionista")
    private String employeeId;

    @ColumnInfo(name = "id_film")
    private int movieId;

    @ColumnInfo(name = "data")
    private String date;

    @ColumnInfo(name = "inizio")
    private String startTime;

    public Projection() {
    }

    public Projection(int id, int hallId, String employeeId, int movieId, String date, String startTime) {
        this.id = id;
        this.hallId = hallId;
        this.employeeId = employeeId;
        this.movieId = movieId;
        this.date = date;
        this.startTime = startTime;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
