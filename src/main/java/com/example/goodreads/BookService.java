package com.example.goodreads;

import java.util.*;
import com.example.goodreads.Book;
import com.example.goodreads.BookRepository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

public class BookService implements BookRepository {
    private HashMap<Integer, Book> hmap = new HashMap<>();

    public BookService() {
        Book b1 = new Book(1, "harry potter", "harry_potter.jpg");
        Book b2 = new Book(2, "Rise", "rise.jpeg");
        hmap.put(b1.getId(), b1);
        hmap.put(b2.getId(), b2);
    }

    @Override
    public ArrayList<Book> getBooks() {
        Collection<Book> bookCollection = hmap.values();
        ArrayList<Book> books = new ArrayList<>(bookCollection);
        return books;

    }

    @Override
    public Book getBookById(int bookId) {
        Book book = hmap.get(bookId);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return book;

    }

    int bookId = 3;

    @Override
    public Book addBook(Book book) {
        book.setId(bookId);
        hmap.put(bookId, book);
        bookId += 1;
        return book;
    }

    @Override
    public Book updateBook(int bookId, Book book) {
        Book existBook = hmap.get(bookId);
        if (existBook == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (book.getName() != null) {
            existBook.setName(book.getName());
        }
        if (book.getImageUrl() != null) {
            existBook.setImageUrl(book.getImageUrl());
        }
        return existBook;
    }

    @Override
    public void deleteBook(int bookId) {
        Book existBook = hmap.get(bookId);
        if (existBook == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            hmap.remove(bookId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);

        }
    }

}
