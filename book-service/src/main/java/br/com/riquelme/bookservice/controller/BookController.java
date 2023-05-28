package br.com.riquelme.bookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riquelme.bookservice.model.Book;
import br.com.riquelme.bookservice.proxy.CambioProxy;
import br.com.riquelme.bookservice.repository.BookRepository;

@RestController
@RequestMapping("book-service")
public class BookController {

	@Autowired
	private Environment environment;

	@Autowired
	private BookRepository repository;

  @Autowired
	private CambioProxy proxy;

  @GetMapping(value = "/{id}/{currency}")	
	public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
    var book = repository.getReferenceById(id);
		if (book == null) throw new RuntimeException("Book not Found");
				
		var cambio = proxy.getCambio(book.getPrice(), "USD", currency);
		
		var port = environment.getProperty("local.server.port");
		book.setEnvironment(
      "Book port: " + port + 
      " Cambio Port " + cambio.getEnvironment()
    );
		book.setPrice(cambio.getConvertedValue());

		return book;
	}

}