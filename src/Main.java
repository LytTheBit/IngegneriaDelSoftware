import java.util.ArrayList;
import java.util.Random;

import BusinessLogic.Admin;
import BusinessLogic.UserController;
import DomainModel.*;
import ORM.*;
public class Main {
    public static void main(String[] args) {
        try{
            AuthorDAO authorDAO = new AuthorDAO();
            EditorDAO editorDAO = new EditorDAO();
            BookDAO bookDao = new BookDAO();
            UserDAO userDAO = new UserDAO();
            RegisterDAO registerDAO = new RegisterDAO();
            Random rand = new Random();

            authorDAO.clearAuthor();
            editorDAO.clearEditor();
            bookDao.clearBook();
            userDAO.clearUtenti();
            registerDAO.clearRegister();


            Admin admin=new Admin(bookDao, editorDAO, authorDAO, userDAO);


            for (int i = 1; i <= 10; i++) {
                Author author = new Author(i, "NomeAutore" + i, "CognomeAutore" + i);
                //authorDAO.insertAuthor(author);
                admin.uploadAuthor(author);
            }


            for (int i = 1; i <= 5; i++) {
                Editor editor =new Editor(i, "Editore" + i);
                //editorDAO.insertEditor(editor);
                admin.uploadEditor(editor);
            }


            //Inserisco i dati dei libri nel database, generando casualmente i libri
            BookDAO bookDAO = new BookDAO();
            for (int i = 1; i <= 20; i++) {
                //Sceglie un autore casuale per il libro generato
                ArrayList<Author> authors = admin.getAllAuthor(); //authorDAO.getAllAuthor();
                int index1 = rand.nextInt(authors.size());
                Author author = authors.get(index1);

                //Sceglie un editore casuale per il libro generato
                ArrayList<Editor> editors = admin.getAllEditor(); //editorDAO.getAllEditor();
                int index2 = rand.nextInt(editors.size());
                Editor editor = editors.get(index2);


                Book book = new Book(i, "TitoloLibro" + i, author, editor);
                admin.uploadBook(book);
            }

            System.out.println(" + ------------------------------------------ + ");
            System.out.println(" + -------------------TEST------------------- + ");
            System.out.println(" + ------------------------------------------ + ");


            ArrayList<Book> books = bookDao.getAllBook();
            for (Book book1 : books) {
                System.out.print(book1.getTitle());
                System.out.print(" - "+book1.getAuthor().getNome());
                System.out.print(" "+book1.getAuthor().getCognome());
                System.out.println(" - "+book1.getEditor().getNome());
            }

            //TEST SUL LIBRO 10
            /*
            RegisterDAO registerDao = new RegisterDAO();
            Register register = registerDao.getRegister(10);
            System.out.println("numero copie: "+register.getNumCopy());

            registerDao.takeBookCopy(register);
            register=registerDao.getRegister(register.getIdBook()); //aggiorno il registro
            System.out.println("numero copie dopo il prestito: "+register.getNumCopy());

            registerDao.returnBookCopy(register.getIdBook());
            register=registerDao.getRegister(register.getIdBook());
            System.out.println("numero copie dopo la restituzione: "+register.getNumCopy());
            */

            //PRINT DI TUTTI I LIBRI, GLI AUTORI, E GLI EDITORI
            /*
            //test libri
            ArrayList<Book> books2 = bookDao.getAllBook();
            for (Book book2 : books2) {
                System.out.print(book2.getTitle());
                System.out.print(" - "+book2.getAuthor().getNome());
                System.out.print(" "+book2.getAuthor().getCognome());
                System.out.println(" - "+book2.getEditor().getNome());
            }

            System.out.println(" + ------------------------------------------ + ");
            //test editor
            ArrayList<Editor> editors = editorDAO.getAllEditor();
            for (Editor editor1 : editors) {
                System.out.print(editor1.getId());
                System.out.println(" - "+editor1.getNome());
            }

            System.out.println(" + ------------------------------------------ + ");
            //test autore
            ArrayList<Author> authors = authorDAO.getAllAuthor();
            for (Author author1 : authors) {
                System.out.print(author1.getId());
                System.out.print(" - "+author1.getNome());
                System.out.println(" "+author1.getCognome());
            }
            */

            //Genera e inserisce gli utenti nel database
            for (int i = 1; i <= 20; i++) {
                User user = new User(i, "NomeUtente" + i, "CognomeUtente" + i);
                admin.uploadUser(user);
            }

            UserController userController = new UserController();
            ArrayList<User> users = admin.getAllUser(); //userDAO.getAllUser();

            System.out.println(" START simulazione");
            for (int i = 1; i <= 500; i++) {
                int index3 = rand.nextInt(users.size());
                User user = users.get(index3);
                System.out.print(user.getNome()+": ");

                userController.whatUserDo(user);
                System.out.println("");
            }
            System.out.println(" END simulazione");


            System.out.println();
            System.out.println("NUMERO DI LIBRI PRESTATI PER OGNI AUTORE");
            admin.dataAuthors();

            System.out.println();
            System.out.println("NUMERO DI LIBRI PRESTATI PER OGNI EDITORE");
            admin.dataEditors();

            System.out.println();
            System.out.println("NUMERO DI PRESTITI PER OGNI LIBRO");
            admin.dataBooks();
        }
        catch (Exception e){
        }
    }
}