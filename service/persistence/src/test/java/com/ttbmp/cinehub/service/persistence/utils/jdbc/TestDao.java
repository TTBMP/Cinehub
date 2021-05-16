package com.ttbmp.cinehub.service.persistence.utils.jdbc;

import com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation.*;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Fabio Buracchi
 */

@Dao
public interface TestDao {

    @Query("SELECT * FROM test_table")
    List<TestEntity> getAll() throws DaoMethodException;

    @Query("SELECT * FROM test_table WHERE cinema.id = :id")
    TestEntity getById(@Parameter(name = "id") @NotNull Integer id) throws DaoMethodException;

    @Query("SELECT * FROM test_table WHERE field1 = :f")
    TestEntity getByField(@Parameter(name = "f") @NotNull String field) throws DaoMethodException;

    @Insert
    void insert(@NotNull TestEntity entity) throws DaoMethodException;

    @Insert
    void insert(@NotNull List<TestEntity> entity) throws DaoMethodException;

    @Update
    void update(@NotNull TestEntity entity) throws DaoMethodException;

    @Update
    void update(@NotNull List<TestEntity> entity) throws DaoMethodException;

    @Delete
    void delete(@NotNull TestEntity entity) throws DaoMethodException;

    @Delete
    void delete(@NotNull List<TestEntity> entity) throws DaoMethodException;

}
