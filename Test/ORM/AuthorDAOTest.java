package ORM;

import java.util.ArrayList;

import DomainModel.Author;
import DomainModel.Book;
import DomainModel.Editor;
import DomainModel.User;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorDAOTest {
    AuthorDAO authorDAO = new AuthorDAO();
    @Test
    public void getAuthorTest() throws SQLException, ClassNotFoundException {
        ArrayList<Author> authors = authorDAO.getAllAuthor();
        Author author = authors.get(1);
        assertEquals(1, author.getId());
        assertEquals("NomeAutore", author.getNome());
        assertEquals("CognomeAutore", author.getCognome());
    }

    @Test
    public void isAuthorExistsTest() throws SQLException, ClassNotFoundException {
        assertEquals(true, authorDAO.isAuthorExists(1));
        assertNotEquals(true, authorDAO.isAuthorExists(3));
    }

    @Test
    public void insertAuthorTest() throws SQLException, ClassNotFoundException {
        Author author1 = new Author(2, "NomeAutoreTest", "CognomeAutoreTest");
        authorDAO.insertAuthor(author1);

        Author author2 = authorDAO.getAuthor(2);
        assertEquals(2, author2.getId());
        assertEquals("NomeAutoreTest", author2.getNome());
        assertEquals("CognomeAutoreTest", author2.getCognome());
    }
}