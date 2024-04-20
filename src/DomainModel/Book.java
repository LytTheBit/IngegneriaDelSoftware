package DomainModel;

public class Book {
    int id;
    String title;
    Author author;
    Editor editor;

    public Book(int id, String title, Author author, Editor editor) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.editor = editor;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public Author getAuthor() {
        return author;
    }
    public Editor getEditor() {
        return editor;
    }
}
