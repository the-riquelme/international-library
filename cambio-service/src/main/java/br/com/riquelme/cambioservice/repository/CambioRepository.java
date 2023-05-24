package br.com.riquelme.cambioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.riquelme.cambioservice.model.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long> {

	Cambio findByFromAndTo(String from, String to);

}