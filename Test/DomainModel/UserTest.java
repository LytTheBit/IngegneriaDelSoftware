package DomainModel;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User user = new User(1,"Mario","Rossi");
    Author author = new Author(1, "Dante", "Alighieri");
    Editor editor = new Editor(1, "Medioevo");
    Book book = new Book(1, "Divina commedia", author, editor);
    @Test
    public void userIdReturnTest(){
        assertEquals(1, user.getId());
    }

    @Test
    public void userNameReturnTest(){
        assertEquals("Mario", user.getNome());
    }

    @Test
    public void userSurnameReturnTest(){
        assertEquals("Rossi", user.getCognome());
    }

    @Test
    public void userBookLoanTest(){
        user.setBookLoan(book);
        assertEquals(book, user.getBookLoan());
        assertEquals(null, user.getBookLoan());
    }

    @Test
    public void userStateTest(){
        assertEquals(1, user.getState());
        user.nextState();
        assertEquals(2, user.getState());
        user.nextState();
        assertEquals(3, user.getState());
        user.nextState();
        assertEquals(1, user.getState());
    }
}