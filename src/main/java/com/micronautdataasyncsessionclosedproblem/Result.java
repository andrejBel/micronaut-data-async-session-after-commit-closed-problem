package com.micronautdataasyncsessionclosedproblem;

public record Result(Book book, java.util.concurrent.Future<Void> voidFuture) {}
