package com.alexandria.books.repository;

import com.alexandria.books.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID>, CrudRepository<Author, UUID> {
  
}
