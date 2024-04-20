package DomainModel;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    Author author = new Author(1, "Dante", "Alighieri");
    Editor editor = new Editor(1, "Medioevo");
    Book book = new Book(1, "Divina commedia", author, editor);
    @Test
    public void bookIdReturnTest(){
        assertEquals(1, book.getId());
        assertEquals(1, book.getAuthor().getId());
        assertEquals(1, book.getEditor().getId());
    }

    @Test
    public void bookTitleReturnTest(){
        assertEquals("Divina commedia", book.getTitle());
    }
}