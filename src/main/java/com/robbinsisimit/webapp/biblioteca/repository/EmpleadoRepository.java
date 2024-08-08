package com.robbinsisimit.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robbinsisimit.webapp.biblioteca.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

}
