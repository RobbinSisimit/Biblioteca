package com.robbinsisimit.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.robbinsisimit.webapp.biblioteca.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {


}