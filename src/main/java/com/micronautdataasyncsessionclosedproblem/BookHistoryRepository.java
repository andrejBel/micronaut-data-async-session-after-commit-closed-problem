package com.micronautdataasyncsessionclosedproblem;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface BookHistoryRepository extends CrudRepository<BookHistory, Long> {
}