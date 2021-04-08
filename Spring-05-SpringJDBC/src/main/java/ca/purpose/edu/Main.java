package ca.purpose.edu;

import ca.purpose.edu.dao.authordao.AuthorDao;
import ca.purpose.edu.dao.bookdao.BookDao;
import ca.purpose.edu.dao.genredao.GenreDao;
import ca.purpose.edu.domain.Author;
import ca.purpose.edu.domain.Book;
import ca.purpose.edu.domain.Genre;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(Main.class);
        BookDao bookDao = context.getBean(BookDao.class);
        AuthorDao authorDao = context.getBean(AuthorDao.class);
        GenreDao genreDao = context.getBean(GenreDao.class);

        System.out.println("Total books " + bookDao.count());
        bookDao.deleteById(2);
        System.out.println("Total books " + bookDao.count());
        bookDao.insert(new Book(4, "Interstellar", 2, 3));
        System.out.println(bookDao.getById(4));
        System.out.println(bookDao.getAll());
        bookDao.update(new Book(1, "Wizard", 3, 1));
        System.out.println(bookDao.getById(1));

        System.out.println("Total authors " + authorDao.count());
        authorDao.deleteById(2);
        System.out.println("Total authors " + authorDao.count());
        authorDao.insert(new Author(4, "Pushkin"));
        System.out.println(authorDao.getById(4));
        System.out.println(authorDao.getAll());
        authorDao.update(new Author(1, "Elizabeth"));
        System.out.println(authorDao.getById(1));


        System.out.println("Total genres " + genreDao.count());
        genreDao.deleteById(2);
        System.out.println("Total genres " + genreDao.count());
        genreDao.insert(new Genre(4, "Thriller"));
        System.out.println(genreDao.getById(4));
        System.out.println(genreDao.getAll());

    }
}
