package com.robbinsisimit.webapp.biblioteca.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.robbinsisimit.webapp.biblioteca.model.Categoria;
import com.robbinsisimit.webapp.biblioteca.model.Prestamo;
import com.robbinsisimit.webapp.biblioteca.service.PrestamoService;
import com.robbinsisimit.webapp.biblioteca.util.MethodType;

@Controller
@RestController
@RequestMapping("")
public class PrestamoController {
    @Autowired
    PrestamoService prestamoService;

    @GetMapping("/prestamos")
    public ResponseEntity<List<Prestamo>> listarPrestamos(){
        try{
            return ResponseEntity.ok(prestamoService.listarPrestamos());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PostMapping("/prestamo")
    public ResponseEntity<Map<String, String>>agregarPrestamo(@RequestBody Prestamo prestamo){
        Map<String, String> response = new HashMap<>();
        try{
            prestamoService.guardarPrestamo(prestamo, MethodType.POST);
            response.put("message", "Prestamo creado con exito :D");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("Message", "error");
            response.put("err", "Hubo un error al crear el prestamo");
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PutMapping("/prestamo")
    public ResponseEntity <Map<String, String>> editarPretamo(@RequestParam Long id, @RequestBody Prestamo prestamoNuevo){
        Map<String, String> response = new HashMap<>();
        try{
            Prestamo prestamo = prestamoService.buscarPrestamoPorId(id);
            prestamo.setCliente(prestamoNuevo.getCliente());
            prestamo.setEmpleado(prestamoNuevo.getEmpleado());
            prestamo.setFechaDeDevolucion(prestamoNuevo.getFechaDeDevolucion());
            prestamo.setFechaDePrestamo(prestamoNuevo.getFechaDePrestamo());
            prestamo.setLibros(prestamoNuevo.getLibros());
            prestamo.setVigencia(prestamoNuevo.getVigencia());
            prestamoService.guardarPrestamo(prestamo, MethodType.PUT);
            response.put("message", "se a actuliazo con exito:D");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("message", "error");
            response.put("err", "no se pudo actualizar prestamos");
            return ResponseEntity.badRequest().body(response);
        }
    }
    @GetMapping("/prestamo")
    public ResponseEntity<Prestamo> buscarPrestamoPorId(@RequestParam Long Id){
        try{
            return ResponseEntity.ok(prestamoService.buscarPrestamoPorId(Id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    @DeleteMapping("/prestamo")
    public ResponseEntity<Map<String, String>> eliminarPrestamo(@RequestParam Long id){
        Map<String, String > response = new HashMap<>();
        try{
            Prestamo prestamo = prestamoService.buscarPrestamoPorId(id);
            prestamoService.eliminarPrestamo(prestamo);
            response.put("message", "Prestamo Eliminada");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("message", "el prestamo no se puede eliminar");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
