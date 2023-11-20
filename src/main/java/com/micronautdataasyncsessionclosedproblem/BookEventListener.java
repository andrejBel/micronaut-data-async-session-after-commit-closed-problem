package com.micronautdataasyncsessionclosedproblem;


import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.scheduling.annotation.Async;
import io.micronaut.transaction.TransactionDefinition;
import io.micronaut.transaction.annotation.Transactional;
import io.micronaut.transaction.annotation.TransactionalEventListener;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

@Singleton
public class BookEventListener {

  private static final Logger LOG = LoggerFactory.getLogger(BookEventListener.class);

  private final BookHistoryRepository bookHistoryRepository;

  public BookEventListener(BookHistoryRepository bookHistoryRepository) {
    this.bookHistoryRepository = bookHistoryRepository;
  }

  @TransactionalEventListener(TransactionalEventListener.TransactionPhase.AFTER_COMMIT)
  public void exceptionOnEvent(NewBookCreatedEvent newBookCreatedEvent) {
      exceptionOnTransaction(newBookCreatedEvent);
  }

  @Async
  @Transactional(propagation = TransactionDefinition.Propagation.REQUIRES_NEW)
  protected void exceptionOnTransaction(NewBookCreatedEvent newBookCreatedEvent) {
    try {
      LOG.info("Saving into history: " + newBookCreatedEvent.bookId());
      Thread.sleep(TimeUnit.SECONDS.toMillis(5));
      bookHistoryRepository.save(new BookHistory(newBookCreatedEvent.bookId()));
      LOG.info("Saved into history: " + newBookCreatedEvent.bookId());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
  }

}
