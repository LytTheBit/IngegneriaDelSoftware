package BusinessLogic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import DomainModel.*;
import ORM.*;

public class UserController {

    public void whatUserDo(User user) throws SQLException, ClassNotFoundException {
        System.out.println("L'utente è nello stato "+ user.getState());
        if(user.getState()==1){
            System.out.println(user.getNome()+" cerca un libro");
            this.askBook(user);
        }
        else if (user.getState()==2) {
            System.out.println(user.getNome()+" legge un libro");
            this.readBook(user);
        }
        else if(user.getState()==3){
            System.out.println(user.getNome()+" restituisce un libro");
            this.returnBook(user);
        }
        //A seconda dello stato del utente fa fare al utente qualcosa:
        //ovviamente si ipotiza in questo caso che un utente possa avere
        //solo un libro per volta
    }

    public UserController() {
    }
    public void askBook(User user) throws SQLException, ClassNotFoundException {
        BookDAO bookDao = new BookDAO();
        ArrayList<Book> books = AskDataBook();//bookDao.getAllBook();

        //cerca tra i libri in biblioteca e ne sceglie uno a caso
        Random rand = new Random();
        int index = rand.nextInt(books.size());
        Book book = books.get(index);

        //controlla se il libro richiesto è disponibile
        RegisterDAO registerDao = new RegisterDAO();
        Register register = registerDao.getRegister(book.getId()); //prende il registro del libro cercato
        if(register.getNumCopy()==0){
            System.out.println(user.getNome() +" "+ user.getCognome()+" non è riuscito a prenotare il libro: " + book.getTitle());
        }
        else{
            System.out.println(user.getNome() +" "+ user.getCognome()+" ha prenotato e ritirato il libro: " + book.getTitle());
            registerDao.takeBookCopy(register);
            user.setBookLoan(book);
            user.nextState();
        }
    }
    public void readBook(User user){
        Random rand = new Random();
        int finito = rand.nextInt(10);
        System.out.println(user.getNome() +" "+ user.getCognome()+" sta leggendo");
        if(finito>6){
            //ha finito il libro
            System.out.println(user.getNome() +" "+ user.getCognome()+" ha finito di leggere il libro");
            user.nextState();
        }
    }
    public void returnBook(User user) throws SQLException, ClassNotFoundException {
        RegisterDAO registerDao = new RegisterDAO();
        Book book = user.getBookLoan();
        registerDao.returnBookCopy(book.getId());
        System.out.println(user.getNome() +" "+ user.getCognome()+" ha restituito il libro: " + book.getTitle());
        user.nextState();
    }

    private ArrayList<Book> AskDataBook() throws SQLException, ClassNotFoundException {
        BookDAO bookDAO = new BookDAO();
        return bookDAO.getAllBook();
    }
}
