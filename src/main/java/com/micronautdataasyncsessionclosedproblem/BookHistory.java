package com.micronautdataasyncsessionclosedproblem;

import static jakarta.persistence.GenerationType.AUTO;

import io.micronaut.core.annotation.Introspected;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Introspected
@Entity
@Table(name = "book_history")
public class BookHistory {

    @Id
    @GeneratedValue(strategy = AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;

    private Long bookId;

    public BookHistory() {}

    public BookHistory(Long bookId) {
        this.bookId = bookId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  @Override
  public String toString() {
    return "BookHistory{" +
        "id=" + id +
        ", bookId='" + bookId + '\'' +
        '}';
  }
}