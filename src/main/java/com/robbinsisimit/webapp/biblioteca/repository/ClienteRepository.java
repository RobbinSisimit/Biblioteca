package com.robbinsisimit.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robbinsisimit.webapp.biblioteca.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
