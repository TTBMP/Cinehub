package com.ttbmp.cinehub.data.local.utils.jdbc.annotation;

import com.ttbmp.cinehub.data.local.utils.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Set of conflict handling strategies for various {@link Dao} methods.
 */
@Retention(RetentionPolicy.RUNTIME)
@IntDef({OnConflictStrategy.REPLACE, OnConflictStrategy.ABORT, OnConflictStrategy.IGNORE})
public @interface OnConflictStrategy {
    /**
     * OnConflict strategy constant to replace the old data and continue the transaction.
     * <p>
     * An {@link Insert} Dao method that returns the inserted rows ids will never return -1 since
     * this strategy will always insert a row even if there is a conflict.
     */
    int REPLACE = 1;

    /**
     * OnConflict strategy constant to abort the transaction. <em>The transaction is rolled
     * back.</em>
     */
    int ABORT = 2;

    /**
     * OnConflict strategy constant to ignore the conflict.
     * <p>
     * An {@link Insert} Dao method that returns the inserted rows ids will return -1 for rows
     * that are not inserted since this strategy will ignore the row if there is a conflict.
     */
    int IGNORE = 3;

}
