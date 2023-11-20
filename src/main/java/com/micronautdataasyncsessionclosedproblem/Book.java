package com.micronautdataasyncsessionclosedproblem;

import static jakarta.persistence.GenerationType.AUTO;

import io.micronaut.core.annotation.Introspected;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Introspected
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    public Book() {}

    public Book(String isbn,
                String name) {
        this.isbn = isbn;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }



  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", isbn='" + isbn + '\'' +
        '}';
  }
}