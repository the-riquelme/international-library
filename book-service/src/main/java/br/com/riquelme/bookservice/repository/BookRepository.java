package br.com.riquelme.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.riquelme.bookservice.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}
