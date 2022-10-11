package app.models;

import marvelos.src.illuminate.database.Model;

public class Product extends Model {
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
