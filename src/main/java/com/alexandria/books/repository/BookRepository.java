package com.alexandria.books.repository;

import com.alexandria.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>, CrudRepository<Book, UUID> {

}
