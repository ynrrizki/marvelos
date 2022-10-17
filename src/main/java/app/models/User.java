package app.models;

import marvelos.illuminate.database.Model;

public class User extends Model {
    protected String table;

    @Override
    public void setTable(String table) {
        table = this.table;
        super.setTable(table);
    }

    @Override
    public String getTable() {
        return table;
    }
}

