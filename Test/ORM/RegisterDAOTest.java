package ORM;

import DomainModel.Editor;
import DomainModel.Register;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterDAOTest {
    RegisterDAO registerDAO = new RegisterDAO();
    @Test
    public void getRegisterTest() throws SQLException, ClassNotFoundException {
        Register register = registerDAO.getRegister(1);
        assertEquals(1, register.getIdBook());
        assertEquals(0, register.getNumTake());
    }

    @Test
    public void takeBookCopyTest() throws SQLException, ClassNotFoundException {
        Register register = registerDAO.getRegister(1);

        registerDAO.takeBookCopy(register);

        register = registerDAO.getRegister(1);
        assertEquals(1, register.getIdBook());
        assertEquals(0, register.getNumCopy());
        assertEquals(1, register.getNumTake());
    }

    @Test
    public void returnBookCopyTest() throws SQLException, ClassNotFoundException {
        registerDAO.returnBookCopy(2);

        Register register = registerDAO.getRegister(2);

        assertEquals(2, register.getIdBook());
        assertEquals(2, register.getNumCopy());
        assertEquals(0, register.getNumTake());
    }
}