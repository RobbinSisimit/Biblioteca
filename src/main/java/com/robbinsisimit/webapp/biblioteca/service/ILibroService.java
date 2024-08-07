package com.robbinsisimit.webapp.biblioteca.service;

import java.util.List;

import com.robbinsisimit.webapp.biblioteca.model.Libro;

public interface ILibroService {
    public List<Libro> listarLibros();
    public Libro guardarLibro(Libro libro);
    public Libro buscarLibroPorId(Long id);
    public void eliminarLibro(Libro libro);
}
