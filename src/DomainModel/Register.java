package DomainModel;

public class Register {
    int idBook;
    int numCopy;
    int numTake;

    public Register(int idBook, int numCopy, int numTake) {
        this.idBook = idBook;
        this.numCopy = numCopy;
        this.numTake = numTake;
    }

    public int getIdBook() {
        return idBook;
    }

    public int getNumCopy() {
        return numCopy;
    }

    public int getNumTake() {
        return numTake;
    }
}
