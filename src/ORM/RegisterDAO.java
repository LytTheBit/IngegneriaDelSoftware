package ORM;
import java.sql.*;

import DomainModel.*;

public class RegisterDAO {
    public void clearRegister() throws SQLException, ClassNotFoundException{
        //Diversi da 0
        Connection connection = DatabaseConnection.getConnection();
        String sql = "DELETE FROM `jdbc-book`.`registro` WHERE idLibro!=0";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        //Uguali a 0
        sql = "DELETE FROM `jdbc-book`.`registro` WHERE idLibro=0";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();

        System.out.println("Pulita la tabella registro");
    }
    public Register getRegister(int idcode) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT `registro`.`idLibro`, `registro`.`numero_copie_disponibili`, `registro`.`numero_prestiti_totali` FROM `jdbc-book`.`registro` WHERE idLibro=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idcode);
        ResultSet rs = preparedStatement.executeQuery();

        Register register = null; // Inizializza il registro a null

        // Verifica se il ResultSet contiene una riga
        if (rs.next()) {
            // Ottieni i valori delle colonne solo se il ResultSet contiene almeno una riga
            int A = rs.getInt("idLibro");
            int B = rs.getInt("numero_copie_disponibili");
            int C = rs.getInt("numero_prestiti_totali");

            // Crea un nuovo oggetto Register con i valori ottenuti
            register = new Register(A, B, C);
        }
        // Chiudi le risorse

        return register;
    }

    public void insertRegister(Book book) throws SQLException, ClassNotFoundException {
        try {
            Connection con = DatabaseConnection.getConnection();
            String sql = "INSERT INTO `jdbc-book`.`registro` (idLibro, numero_copie_disponibili, numero_prestiti_totali) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, book.getId());
            int a=1;
            ps.setInt(2, a);
            int b=0;
            ps.setInt(3, b);

            ps.executeUpdate();
            ps.close();
        } catch (Throwable e) {
            //e.printstacktrace();
            System.out.println(e);
        }
    }
    public void takeBookCopy(Register register) throws SQLException, ClassNotFoundException {
        try {
            Connection con = DatabaseConnection.getConnection();
            //String sql = "UPDATE jdbc-book.registro SET numero_copie_disponibili = numero_copie_disponibili-1, numero_prestiti_totali = numero_prestiti_totali+1 WHERE idLibro=?";
            String sql = "UPDATE `jdbc-book`.`registro` SET numero_copie_disponibili = ?, numero_prestiti_totali = ? WHERE idLibro=?";
            PreparedStatement ps = con.prepareStatement(sql);

            //System.out.println("Il numero di copie passa da "+register.getNumCopy());
            int A = register.getNumCopy()-1;
            //System.out.println("Il numero di copie passa a "+A);
            int B = register.getNumTake()+1;

            ps.setInt(1, A);
            ps.setInt(2, B);
            ps.setInt(3, register.getIdBook());

            ps.executeUpdate();
            ps.close();
        } catch (Throwable e) {
            //e.printstacktrace();
            System.out.println(e);
        }

    }
    public void returnBookCopy(int idcode) throws SQLException, ClassNotFoundException {
        try {
            Connection con = DatabaseConnection.getConnection();
            String sql = "UPDATE registro SET numero_copie_disponibili = numero_copie_disponibili+1 WHERE idLibro = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idcode);

            ps.executeUpdate();
            ps.close();
        } catch (Throwable e) {
            //e.printstacktrace();
            System.out.println(e);
        }
    }
}
