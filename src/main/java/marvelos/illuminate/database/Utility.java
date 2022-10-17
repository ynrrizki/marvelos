package marvelos.illuminate.database;
public class Utility {
    public static String tableOf(Object obj) {
        return obj.getClass().getSimpleName().toLowerCase() + "s";
    }

    public static String tableOfProperties(String property) {
        return property.toLowerCase();
    }
}
