package com.ttbmp.cinehub.service.persistence.entity;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.ColumnInfo;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Entity;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.PrimaryKey;

@Entity(tableName = "turno_proiezionista")
public class ProjectionistShift {


    @PrimaryKey
    @ColumnInfo(name = "turno_id")
    private int shiftId;

    @ColumnInfo(name = "sala_id")
    private int hallId;

    public ProjectionistShift() {
    }

    public ProjectionistShift(int shiftId, int hallId) {
        this.shiftId = shiftId;
        this.hallId = hallId;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }
}
