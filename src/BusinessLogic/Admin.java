package BusinessLogic;

import DomainModel.User;
import DomainModel.*;
import ORM.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Admin {
    BookDAO bookDAO;
    EditorDAO editorDAO;
    AuthorDAO authorDAO;
    UserDAO userDAO;
    int numAuthor=0;
    int numEditor=0;
    int numBook=0;
    int numUser=0;

    public Admin(BookDAO bookDAO, EditorDAO editorDAO, AuthorDAO authorDAO, UserDAO userDao) {
        this.bookDAO = bookDAO;
        this.editorDAO = editorDAO;
        this.authorDAO = authorDAO;
        this.userDAO = userDao;
    }

    public void uploadBook(Book book) throws SQLException, ClassNotFoundException {
        bookDAO.insertBook(book);
        numBook++;
    }
    public void uploadEditor(Editor editor) throws SQLException, ClassNotFoundException {
        editorDAO.insertEditor(editor);
        numEditor++;
    }
    public void uploadAuthor(Author author) throws SQLException, ClassNotFoundException {
        authorDAO.insertAuthor(author);
        numAuthor++;
    }
    public void uploadUser(User user) throws SQLException, ClassNotFoundException {
        userDAO.insertUser(user);
        numUser++;
    }

    public ArrayList<Book> getAllBook() throws SQLException, ClassNotFoundException {
        return bookDAO.getAllBook();
    }
    public ArrayList<Editor> getAllEditor() throws SQLException, ClassNotFoundException {
        return editorDAO.getAllEditor();
    }
    public ArrayList<Author> getAllAuthor() throws SQLException, ClassNotFoundException {
        return authorDAO.getAllAuthor();
    }
    public ArrayList<User> getAllUser() throws SQLException, ClassNotFoundException {
        return userDAO.getAllUser();
    }


    public void dataBooks() throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT `libri`.`id`, `libri`.`titolo`, `registro`.`numero_prestiti_totali` FROM `jdbc-book`.`registro`, `jdbc-book`.`libri` WHERE `registro`.`idLibro`=`libri`.`id` GROUP BY `libri`.`id` ORDER BY `libri`.`id`;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String titolo = rs.getString("titolo");
            int libriRichiesti = rs.getInt("numero_prestiti_totali");

            System.out.println(id +"-"+titolo+": "+libriRichiesti);
        }
    }
    public void dataEditors() throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT `editore`.*, SUM(`registro`.`numero_prestiti_totali`) AS libriRichiesti FROM `jdbc-book`.`registro`, `jdbc-book`.`editore`, `jdbc-book`.`libri` WHERE `registro`.`idLibro`=`libri`.`id` AND `libri`.`idEditore`=`Editore`.`id` GROUP BY `Editore`.`id` ORDER BY `editore`.`id`;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            int libriRichiesti = rs.getInt("libriRichiesti");

            System.out.println(id +"-"+nome+": "+libriRichiesti);
        }
    }
    public void dataAuthors() throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT `autori`.`id`, `autori`.`nome`, `autori`.`cognome`, SUM(`registro`.`numero_prestiti_totali`) AS libriRichiesti FROM `jdbc-book`.`registro`, `jdbc-book`.`autori`, `jdbc-book`.`libri` WHERE `registro`.`idLibro`=`libri`.`id` AND `libri`.`idAutore`=`autori`.`id` GROUP BY `autori`.`id` ORDER BY `autori`.`id`;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            int libriRichiesti = rs.getInt("libriRichiesti");

            System.out.println(id +"-"+nome+" "+cognome+": "+libriRichiesti);
        }
    }
}
