package ORM;
import java.sql.*;
import java.util.ArrayList;

import DomainModel.*;

public class BookDAO {
    public void clearBook() throws SQLException, ClassNotFoundException{
        //Valori diversi da 0
        Connection connection = DatabaseConnection.getConnection();
        String sql = "DELETE FROM `jdbc-book`.`libri` WHERE id!=0";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        //Valori uguali a 0
        sql = "DELETE FROM `jdbc-book`.`libri` WHERE id=0";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();

        System.out.println("Pulita la tabella Libri");
    }
    public Book getBook(int idcode) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT `libri`.`id`, `libri`.`titolo`, `libri`.`idAutore`, `libri`.`idEditore` FROM `jdbc-book`.`libri` WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idcode);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String nameBook = rs.getString("titolo");
            int idAuthor = rs.getInt("idAutore");
            int idEditor = rs.getInt("idEditore");

            AuthorDAO authorDao = new AuthorDAO();
            Author author = authorDao.getAuthor(idAuthor); //new Author(idAuthor, "test", "test");
            EditorDAO editorDao = new EditorDAO();
            Editor editor = editorDao.getEditor(idEditor);

            Book book = new Book(idcode, nameBook, author, editor);
            return book;
        }
        return null;
    }
    public ArrayList<Book> getAllBook() throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnection.getConnection();
        String sql = "SELECT `libri`.`id`, `libri`.`titolo`, `libri`.`idAutore`, `libri`.`idEditore` FROM `jdbc-book`.`libri`";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList<Book> books = new ArrayList<Book>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nameBook = rs.getString("titolo");
            int idAuthor = rs.getInt("idAutore");
            int idEditor = rs.getInt("idEditore");

            AuthorDAO authorDao = new AuthorDAO();
            Author author = authorDao.getAuthor(idAuthor);
            EditorDAO editorDao = new EditorDAO();
            Editor editor = editorDao.getEditor(idEditor);

            Book book = new Book(id, nameBook, author, editor);
            books.add(book);
        }
        return books;
    }

    public ArrayList<Book> getBookByAuthor(int idcode) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT `libri`.`id`, `libri`.`titolo`, `libri`.`idAutore`, `libri`.`idEditore` FROM `jdbc-book`.`libri` WHERE idAutore=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idcode);
        ResultSet rs = preparedStatement.executeQuery();

        ArrayList<Book> books = new ArrayList<Book>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nameBook = rs.getString("titolo");
            int idAuthor = rs.getInt("idAutore");
            int idEditor = rs.getInt("idEditore");

            AuthorDAO authorDao = new AuthorDAO();
            Author author = authorDao.getAuthor(idAuthor);
            EditorDAO editorDao = new EditorDAO();
            Editor editor = editorDao.getEditor(idEditor);

            Book book = new Book(id, nameBook, author, editor);
            books.add(book);
        }
        return books;
    }

    public ArrayList<Book> getBookByEditor(int idcode) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT `libri`.`id`, `libri`.`titolo`, `libri`.`idAutore`, `libri`.`idEditore` FROM `jdbc-book`.`libri` WHERE idEditore=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idcode);
        ResultSet rs = preparedStatement.executeQuery();

        ArrayList<Book> books = new ArrayList<Book>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nameBook = rs.getString("titolo");
            int idAuthor = rs.getInt("idAutore");
            int idEditor = rs.getInt("idEditore");

            AuthorDAO authorDao = new AuthorDAO();
            Author author = authorDao.getAuthor(idAuthor);
            EditorDAO editorDao = new EditorDAO();
            Editor editor = editorDao.getEditor(idEditor);

            Book book = new Book(id, nameBook, author, editor);
            books.add(book);
        }
        return books;
    }

    public void insertBook(Book book) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnection.getConnection();
        String sql = "INSERT INTO libri (id, titolo, idAutore, idEditore) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, book.getId());
        ps.setString(2, book.getTitle());
        ps.setInt(3, book.getAuthor().getId());
        ps.setInt(4, book.getEditor().getId());

        //controllo se l'autore e l'editore sono già presenti
        //nel database altrimenti li aggiungo
        AuthorDAO authorDao = new AuthorDAO();
        if((authorDao.isAuthorExists(book.getAuthor().getId()))==false) {
            authorDao.insertAuthor(book.getAuthor());
        }
        EditorDAO editorDao = new EditorDAO();
        if((editorDao.isEditorExists(book.getEditor().getId()))==false) {
            editorDao.insertEditor(book.getEditor());
        }


        //inserisco il libro nel registro
        RegisterDAO registerDao = new RegisterDAO();
        registerDao.insertRegister(book);


        ps.executeUpdate();
        ps.close();
    }

    public boolean isBookExists(int idcode) throws SQLException, ClassNotFoundException{
        boolean exists = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // Connessione al database
        Connection connectionn = DatabaseConnection.getConnection();

        // Query per cercare il libro nella tabella libri
        String query = "SELECT * FROM libri WHERE id = ?";
        stmt = connectionn.prepareStatement(query);
        stmt.setInt(1, idcode);
        rs = stmt.executeQuery();

        // Verifica se il risultato contiene qualche riga
        if (rs.next()) {
            exists = true; // il libro è presente nella tabella
        }
        else{
            exists = false;
        }

        return exists;
    }
}
