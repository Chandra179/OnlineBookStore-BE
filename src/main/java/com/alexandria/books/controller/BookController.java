package com.alexandria.books.controller;

import com.alexandria.books.entity.Book;
import com.alexandria.books.repository.AuthorRepository;
import com.alexandria.books.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Tag(name = "Book")
@RestController
class BookController {

  @Autowired
  BookRepository bookRepository;

  @Autowired
  AuthorRepository authorRepository;

  @Operation(
    description = "Get all books",
    tags = {"Book"}
  )
  @ApiResponses(
    value = {
      @ApiResponse(
        responseCode = "200",
        description = "Success get books"
      ),
      @ApiResponse(
        responseCode = "404",
        description = "Book not found",
        content = @Content(schema = @Schema(implementation = ResponseStatusException.class))
      )
    }
  )
  @GetMapping(
    value = "/books",
    produces = {MediaType.APPLICATION_JSON_VALUE}
  )
  public List<Book> getAllBooks() throws ResponseStatusException {
    List<Book> bookList = bookRepository.findAll();
    if (bookList.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No book found");
    }
    return bookList;
  }

  @Operation(
    description = "Get book by title",
    tags = {"Book"}
  )
  @ApiResponses(
    value = {
      @ApiResponse(
        responseCode = "200",
        description = "Success get book"
      ),
      @ApiResponse(
        responseCode = "404",
        description = "No book found",
        content = @Content(schema = @Schema(implementation = ResponseStatusException.class))
      )
    }
  )
  @GetMapping(
    value = "/book",
    produces = {MediaType.APPLICATION_JSON_VALUE}
  )
  public List<Book> getBook(
    @RequestParam("book_title") String title
  ) throws ResponseStatusException {
    List<Book> bookList = bookRepository.findByBookTitleLike(title);
    if (bookList.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No book found");
    }
    return bookList;
  }

}
