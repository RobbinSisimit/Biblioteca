package com.robbinsisimit.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robbinsisimit.webapp.biblioteca.model.Categoria;
import com.robbinsisimit.webapp.biblioteca.repository.CategoriaRepository;
@Service
public class CategoriaService implements ICategoriaService{
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Override
    public List<Categoria> listarCategoria(){
        return categoriaRepository.findAll();
    }
    @Override
    public Categoria busCategoriaPorId(Long id) {
        // TODO Auto-generated method stub
        return categoriaRepository.findById(id).orElse(null);
    }
    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        // TODO Auto-generated method stub
        return categoriaRepository.save(categoria);
    }
    @Override
    public void eliminarCategoria(Categoria categoria) {
        // TODO Auto-generated method stub
        categoriaRepository.delete(categoria);
    }
}
