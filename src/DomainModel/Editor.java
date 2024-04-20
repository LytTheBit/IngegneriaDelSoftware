package DomainModel;

public class Editor {
    int id;
    String nome;

    public Editor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
