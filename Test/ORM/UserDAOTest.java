package ORM;

import DomainModel.Author;
import DomainModel.User;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest{
    UserDAO userDAO = new UserDAO();
    @Test
    public void getUserTest() throws SQLException, ClassNotFoundException {
        ArrayList<User> users = userDAO.getAllUser();
        User user = users.get(0);
        assertEquals(1, user.getId());
        assertEquals("nome", user.getNome());
        assertEquals("cognome", user.getCognome());
    }

    @Test
    public void insertUserTest() throws SQLException, ClassNotFoundException {
        User user1 = new User(2, "nomeTest", "cognomeTest");
        userDAO.insertUser(user1);

        User user2 = userDAO.getUser(2);

        assertEquals(2, user2.getId());
        assertEquals("nomeTest", user2.getNome());
        assertEquals("cognomeTest", user2.getCognome());
    }
}