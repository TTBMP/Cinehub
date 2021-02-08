package com.ttbmp.cinehub.service.persistence.utils.jdbc.annotation;

import com.ttbmp.cinehub.service.persistence.utils.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Declares a foreign key on another {@link Entity}.
 * <p>
 * Foreign keys allows you to specify constraints across Entities that will ensure that the
 * relationship is valid when you modify the database.
 * <p>
 * When a foreign key constraint is specified, it is required to the referenced columns to be part of
 * a unique index in the parent table or the primary key of that table. You must create a unique
 * index in the parent entity that covers the referenced columns.
 * <p>
 * A foreign key constraint can be deferred until the transaction is complete. This is useful if
 * you are doing bulk inserts into the database in a single transaction. By default, foreign key
 * constraints are immediate but you can change this value by setting {@link #deferred()} to
 * {@code true}.
 */
@Retention(RetentionPolicy.CLASS)
public @interface ForeignKey {
    /**
     * Possible value for {@link #onDelete()} or {@link #onUpdate()}.
     * <p>
     * When a parent key is modified or deleted from the database, no special action is taken.
     * This means that the database will not make any effort to fix the constraint failure, instead,
     * reject the change.
     */
    int NO_ACTION = 1;
    /**
     * Possible value for {@link #onDelete()} or {@link #onUpdate()}.
     * <p>
     * The RESTRICT action means that the application is prohibited from deleting
     * (for {@link #onDelete()}) or modifying (for {@link #onUpdate()}) a parent key when there
     * exists one or more child keys mapped to it. The difference between the effect of a RESTRICT
     * action and normal foreign key constraint enforcement is that the RESTRICT action processing
     * happens as soon as the field is updated - not at the end of the current statement as it would
     * with an immediate constraint, or at the end of the current transaction as it would with a
     * {@link #deferred()} constraint.
     * <p>
     * Even if the foreign key constraint it is attached to is {@link #deferred()}, configuring a
     * RESTRICT action causes the database to return an error immediately if a parent key with
     * dependent child keys is deleted or modified.
     */
    int RESTRICT = 2;
    /**
     * Possible value for {@link #onDelete()} or {@link #onUpdate()}.
     * <p>
     * If the configured action is "SET NULL", then when a parent key is deleted
     * (for {@link #onDelete()}) or modified (for {@link #onUpdate()}), the child key columns of all
     * rows in the child table that mapped to the parent key are set to contain {@code NULL} values.
     */
    int SET_NULL = 3;
    /**
     * Possible value for {@link #onDelete()} or {@link #onUpdate()}.
     * <p>
     * The "SET DEFAULT" actions are similar to {@link #SET_NULL}, except that each of the child key
     * columns is set to contain the columns default value instead of {@code NULL}.
     */
    int SET_DEFAULT = 4;
    /**
     * Possible value for {@link #onDelete()} or {@link #onUpdate()}.
     * <p>
     * A "CASCADE" action propagates the delete or update operation on the parent key to each
     * dependent child key. For {@link #onDelete()} action, this means that each row in the child
     * entity that was associated with the deleted parent row is also deleted. For an
     * {@link #onUpdate()} action, it means that the values stored in each dependent child key are
     * modified to match the new parent key values.
     */
    int CASCADE = 5;

    /**
     * The parent Entity to reference. It must be a class annotated with {@link Entity} and
     * referenced in the same database.
     *
     * @return The parent Entity.
     */
    Class<?> entity();

    /**
     * The list of column names in the parent {@link Entity}.
     * <p>
     * Number of columns must match the number of columns specified in {@link #childColumns()}.
     *
     * @return The list of column names in the parent Entity.
     * @see #childColumns()
     */
    String[] parentColumns();

    /**
     * The list of column names in the current {@link Entity}.
     * <p>
     * Number of columns must match the number of columns specified in {@link #parentColumns()}.
     *
     * @return The list of column names in the current Entity.
     */
    String[] childColumns();

    /**
     * Action to take when the parent {@link Entity} is deleted from the database.
     * <p>
     * By default, {@link #NO_ACTION} is used.
     *
     * @return The action to take when the referenced entity is deleted from the database.
     */
    @Action int onDelete() default NO_ACTION;

    /**
     * Action to take when the parent {@link Entity} is updated in the database.
     * <p>
     * By default, {@link #NO_ACTION} is used.
     *
     * @return The action to take when the referenced entity is updated in the database.
     */
    @Action int onUpdate() default NO_ACTION;

    /**
     * * A foreign key constraint can be deferred until the transaction is complete. This is useful
     * if you are doing bulk inserts into the database in a single transaction. By default, foreign
     * key constraints are immediate but you can change it by setting this field to {@code true}.
     *
     * @return Whether the foreign key constraint should be deferred until the transaction is
     * complete. Defaults to {@code false}.
     */
    boolean deferred() default false;

    /**
     * Constants definition for values that can be used in {@link #onDelete()} and
     * {@link #onUpdate()}.
     */
    @IntDef({NO_ACTION, RESTRICT, SET_NULL, SET_DEFAULT, CASCADE})
    @Retention(RetentionPolicy.CLASS)
    @interface Action {
    }

}
