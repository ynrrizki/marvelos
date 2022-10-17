package marvelos.illuminate.database;

import java.sql.ResultSet;

import marvelos.illuminate.DBCollection;
import marvelos.illuminate.database.mysql.Database;

import java.sql.SQLException;
import java.util.*;

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
    protected String table = Utility.tableOf(this);

    /**
     *  The primary key for the model.
     *
     *  @var string
     */
    protected String id = "id";

    /**
     *  The primary key value for the model.
     *
     *  @var integer
     */
    private int _id;

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
     *  The getter of the data table
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
     * Save the model to the database.
     *
     * @param  array  $options
     * @return bool
     */
    public void save(DBCollection query) {
        StringBuffer queryInitialize;
        if(getTable() == null) {
            queryInitialize = new StringBuffer("INSERT INTO " + Utility.tableOf(this) + " ");
        } else {
            queryInitialize = new StringBuffer("INSERT INTO " + getTable() + " ");
        }
        String column = columnBracket(query);
        String value = valueBracket(query);

        instance.queryUpdate(queryInitialize.append(column) + " VALUES " + value);

    }

    public Model find(int id) {
        this._id = id;
        setQuery(" WHERE " + this.id + " = '" + id + "'");
        return this;
    }

    public void update(DBCollection query) {
        this.find(_id);
        StringBuffer queryInitialize;
        if (getTable() == null) {
            queryInitialize = new StringBuffer("UPDATE " + Utility.tableOf(this) + " SET ");
        } else {
            queryInitialize = new StringBuffer("UPDATE " + getTable() + " SET ");
        }
        instance.queryUpdate(updateProcess(query, queryInitialize));
    }

    public void delete() {
        this.find(_id);
        StringBuffer queryInitialize;
        if (getTable() == null) {
            queryInitialize = new StringBuffer("DELETE FROM " + Utility.tableOf(this) + getQuery());
        } else {
            queryInitialize = new StringBuffer("DELETE FROM " + getTable() + getQuery());
        }
        instance.queryUpdate(queryInitialize.toString());
    }

    protected String updateProcess(DBCollection query, StringBuffer queryInitialize) {
        String[] column = new String[query.column.length];
        String[] value = new String[query.value.length];
        StringBuffer setColumn;
        String[] result = new String[query.column.length];
        for ( int i = 0; i < query.column.length; i++) {
            column[i] = query.column[i];
            value[i] = query.value[i];
            setColumn = new StringBuffer(column[i] + " = " + "'" + value[i] + "'");
            if (i != query.column.length - 1) {
                setColumn.append(", ");
            }
            result[i] += queryInitialize.append(setColumn) + getQuery() + ";";
        }
        return result[query.column.length - 1].replace("null", "");
    }

    protected String valueBracket(DBCollection query) {
        // value
        String[] valueCode = new String[query.value.length];
        for(int i = 0; i < query.value.length; i++) {
            valueCode[i] = "'" + query.value[i] + "'";
        }
        StringBuffer valueArr = new StringBuffer(Arrays.toString(valueCode));
        StringBuffer valueStart = valueArr.replace(0, 1, "(");
        StringBuffer valueEnd = valueStart.replace(valueStart.length() - 1, valueStart.length(), ")");
        StringBuffer value = valueEnd;
        return value.toString();
    }

    protected String columnBracket(DBCollection query) {
        // column
        StringBuffer columnArr = new StringBuffer(Arrays.toString(query.column));
        StringBuffer columnStart = columnArr.replace(0, 1, "(");
        StringBuffer columnEnd = columnStart.replace(columnStart.length() - 1, columnStart.length(), ")");
        StringBuffer column = columnEnd;
        return column.toString();
    }


}
