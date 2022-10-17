package marvelos.illuminate;

public class DBCollection {
    public String[] column;
    public String[] value;

    public DBCollection(String[] column, String[] value) {
        this.column = column;
        this.value = value;
    }

    public String getRow(String columnLabel) {
        for (int i = 0; i < column.length; i++) {
            if (this.column[i].equals(columnLabel)) {
                return column[i];
            }
        }
        return "column not found";
    }

    public String getColumn(int index) {
        return column[index];
    }

    public String getValue(int index) {
        return value[index];
    }
}
