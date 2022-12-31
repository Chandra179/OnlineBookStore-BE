package com.alexandria.books.service;

import com.alexandria.books.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

  Page<Book> findAllBooks(int page, int size);

  List<Book> findBookByTitle(String title);
}
