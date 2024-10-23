package via.pro3.slaughterhouse.busniess.persistence;

import java.sql.SQLException;

public class PersistenceException extends Exception {
    public PersistenceException() {
    }

    public PersistenceException(SQLException cause) {
        super(cause);
    }
}
