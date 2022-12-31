package com.alexandria.books.repository;

import com.alexandria.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>, CrudRepository<Book, UUID> {

  @Query(
    value = "SELECT * FROM book WHERE title LIKE %:title%",
    nativeQuery = true)
  List<Book> findByBookTitleLike(String title);
}
