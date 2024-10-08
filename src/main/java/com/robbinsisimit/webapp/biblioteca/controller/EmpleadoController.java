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

import com.robbinsisimit.webapp.biblioteca.model.Empleado;
import com.robbinsisimit.webapp.biblioteca.service.EmpleadoService;

@Controller
@RestController
@RequestMapping(value = "")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;
    @GetMapping("/empleados")
    public List<Empleado> listarEmpelado(){
        return empleadoService.listarEmpleado();
    }
    @GetMapping("/empleado")
    public ResponseEntity<Empleado> buscarEmpeladoPorId(@RequestParam Long Id, @RequestParam String nombre){
        try{
            return ResponseEntity.ok(empleadoService.buscarEmpleadoPorId(Id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PostMapping("/empleado")
    public ResponseEntity<Map<String , String>> agregarEmpleado(@RequestBody Empleado empleado){
        Map<String, String> response = new HashMap<>();
        try{
            if(empleadoService.guardarEmpleado(empleado)){
                response.put("message","Se agrego con exito");
                return ResponseEntity.ok(response);
            }else{
                response.put("message", "DPI duplicado");
                return ResponseEntity.badRequest().body(response);
            }
            
        }catch(Exception e){
            response.put("messafe", "error");
            response.put("err", "no se agrego");
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PutMapping("/empleado")
    public ResponseEntity<Map<String, String>> editarEmpleado(@RequestParam Long id, @RequestBody Empleado empleadoNuevo){
        Map<String, String> response = new HashMap<>();
        try{
            Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
            empleado.setNombre(empleadoNuevo.getNombre());
            empleado.setApellido(empleadoNuevo.getApellido());
            empleado.setTelefono(empleadoNuevo.getTelefono());
            empleado.setDireccion(empleadoNuevo.getDireccion());
            empleado.setDPI(empleadoNuevo.getDPI());
            if(empleadoService.guardarEmpleado(empleado)){
                response.put("message", "Empleado modificado con exito");
                return ResponseEntity.ok(response);
            }else{
                response.put("message", "el empelado no se a editado");
                response.put("err", "error");
                return ResponseEntity.badRequest().body(response);
            }

        }catch(Exception e){
            response.put("message", "el empelado no se a editado");
            response.put("err", "error");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @DeleteMapping("/empleado")
    public ResponseEntity<Map<String , String>> eliminarEmpleado(@RequestParam Long Id){
        Map<String, String> response = new HashMap<>();
        try{
            Empleado empleado = empleadoService.buscarEmpleadoPorId(Id);
            empleadoService.eliminarEmpleado(empleado);
            response.put("message", "Empleado eliminado");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("message", "el empleaod no se elimino");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
