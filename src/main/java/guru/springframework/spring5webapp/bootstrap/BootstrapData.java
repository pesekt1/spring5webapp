package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher1 = new Publisher(
                "publisher1", "address1", "Austin", "Texas", "zip111");
        publisherRepository.save(publisher1);

        Book book1 = new Book("book1 title", "123123", publisher1);
        Author eric = new Author("Eric", "Evans");
        eric.getBooks().add(book1);
        book1.getAuthors().add(eric);
        publisher1.getBooks().add(book1);

        authorRepository.save(eric);
        bookRepository.save(book1);

        Book book2 = new Book("book2 title", "66666666", publisher1);
        Author rod = new Author("Rod", "Johnson");
        book2.getAuthors().add(rod);
        rod.getBooks().add(book2);
        publisher1.getBooks().add(book2);

        authorRepository.save(rod);
        bookRepository.save(book2);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("publisher1 - number of books: " + publisher1.getBooks().size());

    }
}
