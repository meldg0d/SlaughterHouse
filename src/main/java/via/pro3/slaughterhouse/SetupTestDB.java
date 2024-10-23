package via.pro3.slaughterhouse;

import via.pro3.slaughterhouse.data.DatabaseHelper;
import via.pro3.slaughterhouse.model.Animal;

import java.sql.SQLException;

public class SetupTestDB {
    public static void main(String[] args) throws SQLException {
        DatabaseHelper<Animal> helper = new DatabaseHelper<>("jdbc:postgresql://localhost:5432/postgres?currentSchema=slaughterhouse", "postgres", "1234");
    }
}
