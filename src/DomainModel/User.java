package DomainModel;

public class User {
    int id;
    String nome;
    String Cognome;
    Book bookLoan;
    int state = 1; //ogni utente ha 3 stati: (1)ricerca, (2)lettura, (3)restitusione

    public User(int id, String nome, String cognome) {
        this.id = id;
        this.nome = nome;
        Cognome = cognome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setBookLoan(Book bookLoan) {
        this.bookLoan = bookLoan;
    }
    public Book getBookLoan() {
        Book temp = bookLoan;
        bookLoan=null;
        return temp;
    }

    public void nextState() {
        if(state==1){
            state=2;
        }
        else if(state==2){
            state=3;
        }
        else if(state==3){
            state=1;
        }
        //System.out.println("L'utente è ora nello stato "+ state);
    }
    public int getState() {
        return state;
    }
}
