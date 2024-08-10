package com.robbinsisimit.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.robbinsisimit.webapp.biblioteca.model.Categoria;
import com.robbinsisimit.webapp.biblioteca.service.CategoriaService;

@Controller
@RestController
@RequestMapping(value = "")
public class CategoriaController {
    
    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/categorias")
    public List<Categoria> listarCategoria(){
        return categoriaService.listarCategoria();
    }

    @GetMapping("/categoria")
    public ResponseEntity<Categoria> bucarCategoriaPorId(@RequestParam Long id, @RequestParam String nombre){
        try{
            return ResponseEntity.ok(categoriaService.busCategoriaPorId(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PostMapping("/categoria")
    public ResponseEntity<Map<String, String>> agregarCategoria(@RequestBody Categoria categoria){
        Map<String, String> response = new HashMap<>();
        try{
            if(categoriaService.guardarCategoria(categoria)){
                response.put("message", "se agrego con exito");
                return ResponseEntity.ok(response);
            }else{
                response.put("error", "no se creo por nombre de categoria dupliacado");
                return ResponseEntity.badRequest().body(response);
            }
        }catch(Exception e){
            response.put("messae","Se agrego con exito");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/categoria")
    public ResponseEntity<Map<String, String>> editarCategoria(@RequestParam Long id, @RequestBody Categoria categoriaNueva){
        Map<String, String> response = new HashMap<>();
        try{
            Categoria categoria = categoriaService.busCategoriaPorId(id);
            categoria.setNombreCategoria(categoriaNueva.getNombreCategoria());
            if(categoriaService.guardarCategoria(categoria)){
                response.put("message", "la categoria se a actulizado exito");
                return ResponseEntity.ok(response);
            }else{
                response.put("err", "categoria duplicada");
                return ResponseEntity.badRequest().body(response);
            }
            
        }catch(Exception e){
            response.put("err", "la categoria se peude editar");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/categoria")
    public ResponseEntity<Map<String, String>> eliminarCategoria(@RequestParam Long id){
        Map<String, String > response = new HashMap<>();
        try{
            Categoria categoria = categoriaService.busCategoriaPorId(id);
            categoriaService.eliminarCategoria(categoria);
            response.put("message", "Categoria Eliminada");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("message", "la categoria no se puede eliminar");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
