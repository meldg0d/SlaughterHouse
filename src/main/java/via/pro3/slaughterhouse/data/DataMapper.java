package via.pro3.slaughterhouse.data;

import via.pro3.slaughterhouse.busniess.persistence.PersistenceException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataMapper<T> {
    T create(ResultSet rs) throws SQLException, PersistenceException;
}
