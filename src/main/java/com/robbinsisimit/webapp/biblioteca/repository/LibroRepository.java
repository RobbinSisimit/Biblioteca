package com.robbinsisimit.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robbinsisimit.webapp.biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository <Libro, Long> {

}
