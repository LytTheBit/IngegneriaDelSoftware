package ORM;

import DomainModel.Author;
import DomainModel.Book;
import DomainModel.Editor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EditorDAO {
    public void clearEditor() throws SQLException, ClassNotFoundException{
        //Diversi da 0
        Connection connection = DatabaseConnection.getConnection();
        String sql = "DELETE FROM `jdbc-book`.`editore` WHERE id!=0";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        //Uguali a 0
        sql = "DELETE FROM `jdbc-book`.`editore` WHERE id=0";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();

        System.out.println("Pulita la tabella editori");
    }
    public Editor getEditor(int idcode) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT `editore`.`id`, `editore`.`nome` FROM `jdbc-book`.`editore` WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idcode);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("nome");

            Editor editor = new Editor(idcode, name);
            return editor;
        }
        return null;
    }

    public ArrayList<Editor> getAllEditor() throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnection.getConnection();
        String sql = "SELECT `editore`.* FROM `jdbc-book`.`editore`";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList<Editor> editors = new ArrayList<Editor>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("nome");

            Editor editor = new Editor(id, name);
            editors.add(editor);
        }
        return editors;
    }

    public boolean isEditorExists(int idcode) throws SQLException, ClassNotFoundException{
        boolean exists = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // Connessione al database
        Connection connectionn = DatabaseConnection.getConnection();

        // Query per cercare l'autore nella tabella autori
        String query = "SELECT * FROM editore WHERE id = ?";
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

    public void insertEditor(Editor editor) throws SQLException, ClassNotFoundException{
        Connection con = DatabaseConnection.getConnection();
        String sql = "INSERT INTO editore (id, nome) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, editor.getId());
        ps.setString(2, editor.getNome());

        ps.executeUpdate();
        ps.close();
    }
}
