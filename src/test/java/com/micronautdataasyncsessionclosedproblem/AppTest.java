package com.micronautdataasyncsessionclosedproblem;

import io.micronaut.http.MediaType;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.specification.RequestSpecification;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

@MicronautTest
class AppTest {

  @Inject
  EntityManager em;

  @Test
  void testItWorks(RequestSpecification spec) throws InterruptedException {
    spec.log().all(true)
        .when()
        .contentType(MediaType.APPLICATION_JSON)
        .body(new Book("isbn", "name"))
        .post("book")
        .then()
        .log().all(true)
        .statusCode(200);

    Thread.sleep(TimeUnit.SECONDS.toMillis(9)); // sleep for async to finish

    Long bookResult = (Long) em.createNativeQuery("select count(1) from book", Long.class)
        .getSingleResult();

    Long historyResult = (Long) em.createNativeQuery("select count(1) from book_history", Long.class)
        .getSingleResult();

    Assertions.assertTrue(bookResult == 1,"Book is not Saved");
    Assertions.assertTrue(historyResult == 1, "Book History is not Saved");

  }

}
