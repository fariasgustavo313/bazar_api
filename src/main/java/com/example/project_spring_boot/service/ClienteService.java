package com.example.project_spring_boot.service;

import com.example.project_spring_boot.model.Cliente;
import com.example.project_spring_boot.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerClientes(){
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Long id){
        return clienteRepository.findById(id).orElse(null);
    }

    public void crearCliente(Cliente cliente){
        clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id){
        clienteRepository.deleteById(id);
    }

    public void actualizarCliente(Long id, Cliente cliente){
        Cliente clienteAux = clienteRepository.findById(id).orElse(null);
        clienteAux.setNombre(cliente.getNombre());
        clienteAux.setApellido(cliente.getApellido());
        clienteAux.setDni(cliente.getDni());
        clienteRepository.save(clienteAux);
    }
}
