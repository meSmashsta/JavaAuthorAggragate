/* Online Java Compiler and Editor */
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodingExercise {
    public static void main(String []args) {
        System.out.println("Hello, World!");

        var testDataStream = Arrays.stream(TestDataset1);

        var keyword = "software";
        var booksWithKeyword = BooksWithWordInTitle(testDataStream, keyword).collect(Collectors.toList());

        System.out.println("Books with %s in the title:".formatted(keyword));

        for(var book : booksWithKeyword) {
            System.out.println("\t%s".formatted(book.name));
        }
        System.out.println("\n");

        var testDataList = Arrays.asList(TestDataset1);
        var extractedAuthors = ExtractAuthors(testDataList);
        for(var author : extractedAuthors) {
            System.out.println(author.authorName);
            for(var book : author.bookNames) {
                System.out.println("\t".formatted(book));
            }
            System.out.println("\n");
        }
    }

    //**********************************************************************************************
    // QUESTIONs
    //**********************************************************************************************
    // Implement the following function to find books with a particular word in the title
    //**********************************************************************************************
    public static Stream<Book> BooksWithWordInTitle(Stream<Book> books, String wordToFind) {
        return books.filter(b -> b.name.toLowerCase().contains(wordToFind.toLowerCase()));
    }

    //**********************************************************************************************
    // QUESTIONs
    //**********************************************************************************************
    // Implement the following function to return a collection of authors with the books they have
    // written
    //**********************************************************************************************
    public static List<AuthorWithBooks> ExtractAuthors(List<Book> books) {
        Map<String, List<Book>> authorAndBooksDict = new HashMap<>();
        for (Book book : books) {
            for (Author author : book.authors) {
                List<Book> booksOfAuthor = authorAndBooksDict.get(author.name);
                if (booksOfAuthor == null) {
                    booksOfAuthor = new ArrayList<>();
                    authorAndBooksDict.put(author.name, booksOfAuthor);
                }
                booksOfAuthor.add(book);
            }
        }

        List<AuthorWithBooks> authorWithBooksList = new ArrayList<>();
        for (Map.Entry<String, List<Book>> entry: authorAndBooksDict.entrySet()) {
            AuthorWithBooks authorWithBooks = new AuthorWithBooks(
                    entry.getKey(),
                    entry.getValue().stream().map(s -> s.name).toArray(String[]::new)
            );
            authorWithBooksList.add(authorWithBooks);
        }
        return authorWithBooksList;
    }

    public static Book[] TestDataset1 = new Book[] {
        new Book(
            "Design Patterns: Elements of Reusable Object-Oriented Software",
            new Author[] {
                new Author("Erich Gamma"),
                new Author("Richard Helm"),
                new Author("Ralph Johnson"),
                new Author("John Vlissides"),
                new Author("Grady Booch")
            }
        ),
        new Book(
            "Unified Modeling Language User Guide",
            new Author[] { new Author("Grady Booch") }
                ),
        new Book(
            "The Annotated Turing",
            new Author[] { new Author("Charles Petzold") }
        ),
        new Book(
            "Code: The Hidden Language of Computer Hardware and Software",
            new Author[] { new Author("Charles Petzold") }
        ),
        new Book(
            "Balancing Agility and Discipline: A Guide for the Perplexed",
            new Author[] {
                new Author("Barry Boehm"),
                new Author("Grady Booch")
            }
        )
    };
    
    /* Book.java */
    public static class Book {
        public String name;
        public Author[] authors;

        public Book(String name, Author[] authors) {
            this.name = name;
            this.authors = authors;
        }
    }


    /* Author.java */
    public static class Author {
        public String name;

        public Author(String name) {
            this.name = name;
        }
    }

    
    /* AuthorWithBooks.java */
    public static class AuthorWithBooks {
        public String authorName;
        public String[] bookNames;

        public AuthorWithBooks(String author, String[] books) {
            this.authorName = author;
            this.bookNames = books;
        }
    }
}