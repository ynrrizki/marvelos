package marvelos.src.illuminate.database;

import java.sql.ResultSet;

import marvelos.src.illuminate.database.mysql.Database;

import java.sql.SQLException;

public abstract class Model {

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    private String query = "";

    /**
     *  The database for the instance.
     *
     *  @var string
     */
    private static Database instance;

    /**
     *  The table associated with the model.
     *
     *  @var string
     */
    protected String table;

    /**
     *  The primary key for the model.
     *
     *  @var integer
     */
    private Integer id;

    /**
     *  The name of the "created at" column.
     *
     *  @var string|null
     */
    private static final String CREATED_AT = "created_at";

    /**
     *  The name of the "updated at" column.
     *
     *  @var string|null
     */
    private static final String UPDATED_AT = "updated_at";

    public Model() {
        instance = Database.getInstance();
    }

    /**
     *  The getter of the property table
     * @return string
     */
    public String getTable() {
        return table;
    }

    /**
     * The setter of the property table
     * @param
     */
    public void setTable(String table) {
        this.table = table;
    }

    /**
     *  Get all of the models from the database.
     *
     * @return ResultSet
     */
    public ResultSet all() {
        if (getTable() == null) {
            return instance.query("SELECT * FROM " + Utility.tableOf(this));
        } else {
            return instance.query("SELECT * FROM " + getTable());
        }
    }

    /**
     *  The getter of the property table
     * @return this
     */
    public Model where(String field, String value) {
        setQuery(" WHERE " + field + " = '" + value + "'");
        return this;
    }

    /**
     *  The getter of the property table
     * @return Integer
     */
    public Integer count() {
        ResultSet rs;
        if (getTable() == null) {
            rs = instance.query("SELECT COUNT(*) as count FROM " + Utility.tableOf(this) + getQuery());
        } else {
            rs = instance.query("SELECT COUNT(*) as count FROM " + getTable() + getQuery());
        }

        try {
            while(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     *  The getter of the property table
     * @return ResultSet
     */
    public ResultSet get() {
        ResultSet rs;
        if (getTable() == null) {
            rs = instance.query("SELECT * FROM " + Utility.tableOf(this) + getQuery());
        } else {
            rs = instance.query("SELECT * FROM " + getTable() + getQuery());
        }
        return rs;
    }

    /**
     *
     */
    public void save() {

    }


}
