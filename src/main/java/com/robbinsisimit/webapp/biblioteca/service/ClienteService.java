package com.robbinsisimit.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robbinsisimit.webapp.biblioteca.model.Cliente;
import com.robbinsisimit.webapp.biblioteca.repository.ClienteRepository;

@Service
public class ClienteService implements IClienteService{
    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    public List<Cliente> listarCliente(){
        return clienteRepository.findAll();
    }
    @Override
    public Cliente guardarCliente(Cliente cliente) {
        // TODO Auto-generated method stub
        return clienteRepository.save(cliente);
    }
    @Override
    public void eliminarCliente(Cliente cliente) {
        // TODO Auto-generated method stub
        clienteRepository.delete(cliente);
    }
    @Override
    public Cliente buscarClientePorId(Long DPI) {
        // TODO Auto-generated method stub
        return clienteRepository.findById(DPI).orElse(null);
    }
}
