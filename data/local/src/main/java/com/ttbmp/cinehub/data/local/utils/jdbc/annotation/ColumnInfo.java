package com.ttbmp.cinehub.data.local.utils.jdbc.annotation;

import com.ttbmp.cinehub.data.local.utils.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Allows specific customization about the column associated with this field.
 * <p>
 * For example, you can specify a column name for the field or change the column's type affinity.
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnInfo {
    /**
     * Constant to inherit the field name as the column name. If used, the field name
     * will be used as the column name.
     */
    String INHERIT_FIELD_NAME = "[field-name]";
    /**
     * Undefined type affinity. Will be resolved based on the type.
     *
     * @see #typeAffinity()
     */
    int UNDEFINED = 1;
    /**
     * Column affinity constant for strings.
     *
     * @see #typeAffinity()
     */
    int TEXT = 2;
    /**
     * Column affinity constant for integers or booleans.
     *
     * @see #typeAffinity()
     */
    int INTEGER = 3;
    /**
     * Column affinity constant for floats or doubles.
     *
     * @see #typeAffinity()
     */
    int REAL = 4;
    /**
     * Column affinity constant for binary data.
     *
     * @see #typeAffinity()
     */
    int BLOB = 5;
    /**
     * Collation sequence is not specified. The match will behave like {@link #BINARY}.
     *
     * @see #collate()
     */
    int UNSPECIFIED = 1;
    /**
     * Collation sequence for case-sensitive match.
     *
     * @see #collate()
     */
    int BINARY = 2;
    /**
     * Collation sequence for case-insensitive match.
     *
     * @see #collate()
     */
    int NOCASE = 3;
    /**
     * Collation sequence for case-sensitive match except that trailing space characters are
     * ignored.
     *
     * @see #collate()
     */
    int RTRIM = 4;
    /**
     * Collation sequence that uses system's current locale.
     *
     * @see #collate()
     */
    int LOCALIZED = 5;
    /**
     * Collation sequence that uses Unicode Collation Algorithm.
     *
     * @see #collate()
     */
    int UNICODE = 6;
    /**
     * A constant for {@link #defaultValue()} that makes the column to have no default value.
     */
    String VALUE_UNSPECIFIED = "[value-unspecified]";

    /**
     * Name of the column in the database. Defaults to the field name if not set.
     *
     * @return Name of the column in the database.
     */
    String name() default INHERIT_FIELD_NAME;

    /**
     * The type affinity for the column, which will be used when constructing the database.
     * <p>
     * If it is not specified, the value defaults to {@link #UNDEFINED} and it is resolved based
     * on the field's type.
     *
     * @return The type affinity of the column. This is either {@link #UNDEFINED}, {@link #TEXT},
     * {@link #INTEGER}, {@link #REAL}, or {@link #BLOB}.
     */
    @SuppressWarnings("unused") int typeAffinity() default UNDEFINED;

    /**
     * The collation sequence for the column, which will be used when constructing the database.
     * <p>
     * The default value is {@link #UNSPECIFIED}. In that case, Room does not add any
     * collation sequence to the column, and the database treats it like {@link #BINARY}.
     *
     * @return The collation sequence of the column. This is either {@link #UNSPECIFIED},
     * {@link #BINARY}, {@link #NOCASE}, {@link #RTRIM}, {@link #LOCALIZED} or {@link #UNICODE}.
     */
    @Collate int collate() default UNSPECIFIED;

    /**
     * The default value for this column.
     * <p>
     * Note that the default value you specify here will <em>NOT</em> be used if you simply
     * insert the {@link Entity} with {@link Insert @Insert}. In that case, any value assigned in
     * Java/Kotlin will be used. Use {@link Query @Query} with an <code>INSERT</code> statement
     * and skip this column there in order to use this default value.
     * <p>
     * NULL, CURRENT_TIMESTAMP and other SQL constant values are interpreted as such. If you want
     * to use them as strings for some reason, surround them with single-quotes.
     *
     * @return The default value for this column.
     * @see #VALUE_UNSPECIFIED
     */
    String defaultValue() default VALUE_UNSPECIFIED;

    /**
     * The column type constants that can be used in {@link #typeAffinity()}
     */
    @IntDef({UNDEFINED, TEXT, INTEGER, REAL, BLOB})
    @Retention(RetentionPolicy.RUNTIME)
    @interface SQLTypeAffinity {
    }

    @IntDef({UNSPECIFIED, BINARY, NOCASE, RTRIM, LOCALIZED, UNICODE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface Collate {
    }
}
