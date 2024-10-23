package via.pro3.slaughterhouse.data;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import via.pro3.slaughterhouse.model.Animal;
import via.pro3.slaughterhouse.busniess.persistence.Persistence;
import via.pro3.slaughterhouse.busniess.persistence.PersistenceException;
import via.pro3.slaughterhouse.model.AnimalType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;


@Component
@Scope("singleton")
public class AnimalDAO implements Persistence {

    private final DatabaseHelper<Animal> helper;

    public AnimalDAO(DatabaseHelper<Animal> helper) {
        this.helper = helper;
    }

    @Override
    public Animal create(int id, AnimalType type, double weight) throws PersistenceException {
        try{
            helper.executeUpdate("INSERT INTO animal VALUES (?, ?, ?)", id, type, weight);
            return new Animal(id, type, weight);
        } catch (Exception e) {
            throw new PersistenceException((SQLException) e);
        }
    }

    @Override
    public Collection<Animal> readAll() throws PersistenceException {
        try {
            return helper.map(this::create, "SELECT * FROM \"animal\"");
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    private Animal create(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String type = resultSet.getString("type");
        double weight = resultSet.getDouble("weight");
        return new Animal(id, AnimalType.valueOf(type), weight);

    }

    @Override
    public void update(Animal animal) throws PersistenceException {

    }

    @Override
    public void delete(Animal animal) throws PersistenceException {

    }

    @Override
    public Animal read(int id) throws PersistenceException {
        return null;
    }
}
