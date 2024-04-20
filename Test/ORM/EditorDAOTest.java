package ORM;

import DomainModel.Author;
import DomainModel.Editor;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EditorDAOTest {
    EditorDAO editorDAO = new EditorDAO();
    @Test
    public void getEditorTest() throws SQLException, ClassNotFoundException {
        ArrayList<Editor> editors = editorDAO.getAllEditor();
        Editor editor = editors.get(0);
        assertEquals(1, editor.getId());
        assertEquals("Editore", editor.getNome());
    }

    @Test
    public void isEditorExistsTest() throws SQLException, ClassNotFoundException {
        assertEquals(true, editorDAO.isEditorExists(1));
        assertNotEquals(true, editorDAO.isEditorExists(3));
    }

    @Test
    public void insertEditorTest() throws SQLException, ClassNotFoundException {
        Editor editor1 = new Editor(2, "EditorTest");
        editorDAO.insertEditor(editor1);

        Editor editor2 = editorDAO.getEditor(2);
        assertEquals(2, editor2.getId());
        assertEquals("EditorTest", editor2.getNome());
    }
}