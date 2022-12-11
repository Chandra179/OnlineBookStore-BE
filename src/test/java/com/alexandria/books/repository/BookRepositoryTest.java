package com.alexandria.books.repository;

import com.alexandria.books.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class BookRepositoryTest {

  @Autowired
  BookRepository bookRepository;

  @Test
  void insertBook() {
    var savedBook = bookRepository.save(Book.builder().title("title1").build());
    var getBook = bookRepository.findById(savedBook.getId()).orElse(null);
    assertNotNull(getBook);
    assertEquals(savedBook.getTitle(), getBook.getTitle());
  }
}
