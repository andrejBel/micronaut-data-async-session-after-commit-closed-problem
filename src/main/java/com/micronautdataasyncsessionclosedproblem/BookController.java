package com.micronautdataasyncsessionclosedproblem;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller("/book")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @Post
  public Book create(@Body Book newBook) throws InterruptedException, ExecutionException {
    Book book = bookService.create(newBook);

    return book;
  }

  @Get
  public List<Book> getAll() {
    return bookService.getAll();
  }
}
