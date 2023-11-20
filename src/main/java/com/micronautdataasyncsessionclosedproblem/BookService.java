package com.micronautdataasyncsessionclosedproblem;

import io.micronaut.context.event.ApplicationEventPublisher;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.concurrent.Future;

@Singleton
@Transactional
public class BookService {

  private final BookRepository bookRepository;
  private final ApplicationEventPublisher<NewBookCreatedEvent>
      currentAppEventApplicationEventPublisher;

  public BookService(
      BookRepository bookRepository,
      ApplicationEventPublisher<NewBookCreatedEvent> currentAppEventApplicationEventPublisher) {
    this.bookRepository = bookRepository;
    this.currentAppEventApplicationEventPublisher = currentAppEventApplicationEventPublisher;
  }

  @Transactional
  public Book create(Book newBook) {
    Book savedBook = bookRepository.save(newBook);
    this.currentAppEventApplicationEventPublisher.publishEvent(new NewBookCreatedEvent(savedBook.getId()));
    return savedBook;
  }

  public List<Book> getAll() {
    return bookRepository.findAll();
  }
}
