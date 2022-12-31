package com.alexandria.books.service;

import com.alexandria.books.entity.Book;
import com.alexandria.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  @Autowired
  public BookServiceImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public Page<Book> findAllBooks(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Book> bookList = bookRepository.findAll(pageable);
    if (bookList.isEmpty()) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND,
        String.format("Book not found, last page was %s, maximum size: %s",
                      bookList.getTotalPages()-1, bookList.getTotalElements())
      );
    }
    return bookList;
  }

  @Override
  public List<Book> findBookByTitle(String title) {
    List<Book> bookList = bookRepository.findByBookTitleLike(title);
    if (bookList.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No book found");
    }
    return bookList;
  }
}
