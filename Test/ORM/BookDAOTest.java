package ORM;

import DomainModel.Author;
import DomainModel.Book;
import DomainModel.Editor;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BookDAOTest {
    BookDAO bookDAO = new BookDAO();
    @Test
    public void getBookTest() throws SQLException, ClassNotFoundException {
        ArrayList<Book> books = bookDAO.getAllBook();
        Book book = books.get(0);
        assertEquals(1, book.getId());
        assertEquals("TitoloLibro", book.getTitle());
        assertEquals("NomeAutore", book.getAuthor().getNome());
        assertEquals("CognomeAutore", book.getAuthor().getCognome());
        assertEquals("Editore", book.getEditor().getNome());
    }

    @Test
    public void BookExistsTest() throws SQLException, ClassNotFoundException {
        assertEquals(true, bookDAO.isBookExists(1));
        assertNotEquals(true, bookDAO.isBookExists(3));
    }

    @Test
    public void insertAuthorTest() throws SQLException, ClassNotFoundException {
        Author author = new Author(4, "NomeAutoreTest", "CognomeAutoreTest");
        Editor editor = new Editor(4, "EditoreTest");
        Book book1 = new Book(2, "TitoloTest", author, editor);
        bookDAO.insertBook(book1);

        Book book2 = bookDAO.getBook(2);
        assertEquals(2, book2.getId());
        assertEquals("TitoloTest", book2.getTitle());
        assertEquals("NomeAutoreTest", book2.getAuthor().getNome());
        assertEquals("CognomeAutoreTest", book2.getAuthor().getCognome());
        assertEquals("EditoreTest", book2.getEditor().getNome());
    }
}