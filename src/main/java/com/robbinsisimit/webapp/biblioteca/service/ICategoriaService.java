package com.robbinsisimit.webapp.biblioteca.service;
import java.util.List;
import com.robbinsisimit.webapp.biblioteca.model.Categoria;

public interface ICategoriaService {
    public List<Categoria> listarCategoria();

    public Categoria busCategoriaPorId(Long id);

    public Categoria guardarCategoria(Categoria categoria);

    public void eliminarCategoria(Categoria categoria);
}
