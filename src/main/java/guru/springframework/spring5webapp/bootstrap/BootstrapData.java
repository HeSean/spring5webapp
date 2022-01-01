package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.models.Author;
import guru.springframework.spring5webapp.models.Book;
import guru.springframework.spring5webapp.models.Publisher;
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

        System.out.println("Started in Bootstrap");

        Author sean = new Author("Sean", "Hed" );
        Book book = new Book("Java Spring", "1234");
        sean.getBooks().add(book);
        book.getAuthors().add(sean);

        authorRepository.save(sean);
        bookRepository.save(book);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE without EJB" ,"1234");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        Publisher porat = new Publisher("Porat","Bialik 1, Ramat Gan, Israel");
        porat.getBooks().add(noEJB);
        noEJB.setPublisher(porat);
        porat.getBooks().add(book);
        book.setPublisher(porat);
        publisherRepository.save(porat);
        bookRepository.save(noEJB);
        bookRepository.save(book);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Data: " + publisherRepository.findAll());
        System.out.println("Publisher number of books: " + porat.getBooks().size());


    }
}
