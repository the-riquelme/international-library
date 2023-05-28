package br.com.riquelme.bookservice.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riquelme.bookservice.model.Book;

@RestController
@RequestMapping("book-service")
public class BookController {

	@Autowired
	private Environment environment;

  @GetMapping(value = "/{id}/{currency}")	
	public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {

		return new Book(1L, "Oda", "One Piece", new Date(), 100D, currency, environment.getProperty("local.server.port"));
	}

}