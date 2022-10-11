package models;

import app.models.Product;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductModel {
    @Test
    public void getDataModel() {
        Product product = new Product();
        ResultSet rs = product.all();
        try {
            int i = 0;
            while(rs.next()) {
//                System.out.println(rs.getArray(i++));
                System.out.println(rs.getString("name"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
