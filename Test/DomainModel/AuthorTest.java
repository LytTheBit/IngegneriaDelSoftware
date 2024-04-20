package DomainModel;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorTest {
    Author author = new Author(1, "Mario", "Rossi");
    @Test
    public void authorIdReturnTest(){
        assertEquals(1, author.getId());
    }

    @Test
    public void authorNameReturnTest(){
        assertEquals("Mario", author.getNome());
    }

    @Test
    public void authorSurnameReturnTest(){
        assertEquals("Rossi", author.getCognome());
    }
}