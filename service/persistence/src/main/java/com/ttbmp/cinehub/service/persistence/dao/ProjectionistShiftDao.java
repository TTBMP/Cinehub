package com.ttbmp.cinehub.service.persistence.dao;

import com.ttbmp.cinehub.service.persistence.entity.ProjectionistShift;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Dao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Insert;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.Update;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

@Dao
public interface ProjectionistShiftDao {

    @Insert
    void insert(@NotNull ProjectionistShift projectionistShift) throws DaoMethodException;

    @Insert
    void insert(@NotNull List<ProjectionistShift> projectionistShiftList) throws DaoMethodException;

    @Update
    void update(@NotNull ProjectionistShift projectionistShift) throws DaoMethodException;

}
