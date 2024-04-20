import DomainModel.*;

import ORM.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Crea e pulisce il database per i test
        AuthorDAO authorDAO = new AuthorDAO();
        EditorDAO editorDAO = new EditorDAO();
        BookDAO bookDao = new BookDAO();
        UserDAO userDAO = new UserDAO();
        RegisterDAO registerDAO = new RegisterDAO();
        authorDAO.clearAuthor();
        editorDAO.clearEditor();
        bookDao.clearBook();
        userDAO.clearUtenti();
        registerDAO.clearRegister();

        //Creazione database temporaneo
        Author author = new Author(1, "NomeAutore", "CognomeAutore");
        authorDAO.insertAuthor(author);

        Editor editor =new Editor(1, "Editore");
        editorDAO.insertEditor(editor);

        Book book = new Book(1, "TitoloLibro", author, editor);
        bookDao.insertBook(book);

        User user = new User(1, "nome", "cognome");
        userDAO.insertUser(user);

        //Avvio dei test
        Result result = JUnitCore.runClasses(AuthorTest.class, EditorTest.class, BookTest.class, RegisterTest.class, UserTest.class, AuthorDAOTest.class, EditorDAOTest.class, BookDAOTest.class, RegisterDAOTest.class, UserDAOTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        if (result.wasSuccessful()) {
            System.out.println("Tutti i test sono passati con successo!");
        }
    }
}