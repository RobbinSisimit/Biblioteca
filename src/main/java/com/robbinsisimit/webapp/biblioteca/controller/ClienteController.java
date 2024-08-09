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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.robbinsisimit.webapp.biblioteca.model.Cliente;
import com.robbinsisimit.webapp.biblioteca.service.ClienteService;

@Controller
@RestController
@RequestMapping(value= "")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> listarCliente(){
        return clienteService.listarCliente();
    }

    @GetMapping("/cliente")
    public ResponseEntity<Cliente>buscarClientePorDPI(@PathVariable Long DPI){
        try{
            return ResponseEntity.ok(clienteService.buscarClientePorId(DPI));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/cliente")
    public ResponseEntity<Map<String, Boolean>> agregarCliente(@RequestBody Cliente clinte){
        Map<String, Boolean> response = new HashMap<>();
        try{
            clienteService.guardarCliente(clinte);
            response.put("se agrego :D un cliente", Boolean.TRUE);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("se agrego ;D ", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @PutMapping("/cliente")
    public ResponseEntity<Map<String, String>> editarCLiente(@PathVariable Long DPI, @RequestBody Cliente clienteNuevo){
        Map<String, String> response = new HashMap<>();
        try{
            Cliente cliente = clienteService.buscarClientePorId(DPI);
            cliente.setNombreCliente(clienteNuevo.getNombreCliente());
            cliente.setApellidoCliente(cliente.getApellidoCliente());
            cliente.setTelefonoCliente(cliente.getTelefonoCliente());
            clienteService.guardarCliente(cliente);
            response.put("message", "la Cliente se a actulizado exito");
            return ResponseEntity.ok(response);

        }catch(Exception e){
            response.put("message", "la Cliente se peude editar");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/cliente")
    public ResponseEntity<Map<String, String>> eliminarCliente(@PathVariable Long DPI){
        Map<String, String> response = new HashMap<>();
        try{
            Cliente cliente = clienteService.buscarClientePorId(DPI);
            clienteService.eliminarCliente(cliente);
            response.put("message", "el cliente se a eliminado exito");
            return ResponseEntity.ok(response);

        }catch(Exception e){
            response.put("message", "el cleinte no se peude eliminar");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
