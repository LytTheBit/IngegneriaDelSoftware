package DomainModel;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EditorTest {
    Editor editor = new Editor(1, "Medioevo");
    @Test
    public void editorIdReturnTest(){
        assertEquals(1, editor.getId());
    }

    @Test
    public void editorNameReturnTest(){
        assertEquals("Medioevo", editor.getNome());
    }
}