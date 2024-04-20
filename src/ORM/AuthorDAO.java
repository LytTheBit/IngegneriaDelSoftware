package ORM;

import DomainModel.Author;
import DomainModel.Book;
import DomainModel.Editor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorDAO {
    public void clearAuthor() throws SQLException, ClassNotFoundException{
        //Elimino i valori diversi da 0
        Connection connection = DatabaseConnection.getConnection();
        String sql = "DELETE FROM `jdbc-book`.`autori` WHERE id!=0";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        //Elimino i valori uguali a 0
        sql = "DELETE FROM `jdbc-book`.`autori` WHERE id=0";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();

        System.out.println("Pulita la tabella Autori");
    }
    public Author getAuthor(int idcode) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT `autori`.`id`, `autori`.`nome`, `autori`.`cognome` FROM `jdbc-book`.`autori` WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idcode);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("nome");
            String surname = rs.getString("cognome");


            Author author = new Author(idcode, name, surname);
            return author;
        }
        return null;
    }

    public ArrayList<Author> getAllAuthor() throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnection.getConnection();
        String sql = "SELECT `autori`.* FROM `jdbc-book`.`autori`";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList<Author> authors = new ArrayList<Author>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("nome");
            String surname = rs.getString("cognome");

            Author author = new Author(id, name, surname);
            authors.add(author);
        }
        return authors;
    }

    public boolean isAuthorExists(int idcode) throws SQLException, ClassNotFoundException{
        boolean exists = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // Connessione al database
        Connection connectionn = DatabaseConnection.getConnection();

        // Query per cercare l'autore nella tabella autori
        String query = "SELECT * FROM autori WHERE id = ?";
        stmt = connectionn.prepareStatement(query);
        stmt.setInt(1, idcode);
        rs = stmt.executeQuery();

        // Verifica se il risultato contiene qualche riga
        if (rs.next()) {
            exists = true; // L'autore è presente nella tabella
        }
        else{
            exists = false;
        }

        return exists;
    }

    public void insertAuthor(Author author) throws SQLException, ClassNotFoundException{
        Connection connection = DatabaseConnection.getConnection();
        String sql = "INSERT INTO autori (id, nome, cognome) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, author.getId());
        ps.setString(2, author.getNome());
        ps.setString(3, author.getCognome());

        ps.executeUpdate();
        ps.close();
    }
}
