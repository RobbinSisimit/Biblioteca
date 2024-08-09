package com.robbinsisimit.webapp.biblioteca.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.robbinsisimit.webapp.biblioteca.model.Prestamo;
public interface PrestamoRepository extends JpaRepository <Prestamo, Long>{

}
