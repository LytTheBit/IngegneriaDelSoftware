package ORM;

import DomainModel.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    public void clearUtenti() throws SQLException, ClassNotFoundException{
        //Pulizia valori diversi da 0
        Connection connection = DatabaseConnection.getConnection();
        String sql = "DELETE FROM `jdbc-book`.`utenti` WHERE id!=0";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        //Pulizia valori uguali a 0
        sql = "DELETE FROM `jdbc-book`.`utenti` WHERE id=0";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        System.out.println("Pulita la tabella utenti");
    }
    public User getUser(int idcode) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT `utenti`.* FROM `jdbc-book`.`utenti` WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idcode);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("nome");
            String surname = rs.getString("cognome");


            User user = new User(idcode, name, surname);
            return user;
        }
        return null;
    }

    public ArrayList<User> getAllUser() throws SQLException, ClassNotFoundException {
        Connection con = DatabaseConnection.getConnection();
        String sql = "SELECT `utenti`.* FROM `jdbc-book`.`utenti`";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList<User> users = new ArrayList<User>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("nome");
            String surname = rs.getString("cognome");

            User user = new User(id, name, surname);
            users.add(user);
        }
        return users;
    }


    public void insertUser(User user) throws SQLException, ClassNotFoundException{
        Connection connection = DatabaseConnection.getConnection();
        String sql = "INSERT INTO utenti (id, nome, cognome) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, user.getId());
        ps.setString(2, user.getNome());
        ps.setString(3, user.getCognome());

        ps.executeUpdate();
        ps.close();
    }

}
