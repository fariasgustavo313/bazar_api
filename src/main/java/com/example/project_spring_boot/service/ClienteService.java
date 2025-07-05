package com.example.project_spring_boot.service;

import com.example.project_spring_boot.model.Cliente;
import com.example.project_spring_boot.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerClientes(){
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Long id){
        return clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));
    }

    public void crearCliente(Cliente cliente){
        validarCliente(cliente);

        if (clienteRepository.findByDni(cliente.getDni()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un cliente registrado con el DNI: " + cliente.getDni());
        }
        clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id){
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("No se puede eliminar. Cliente no encontrado con ID: " + id);
        }
        clienteRepository.deleteById(id);
    }

    public void actualizarCliente(Long id, Cliente cliente){
        validarCliente(cliente);

        Cliente clienteExistente = clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));
        Optional<Cliente> clienteMismoDni = clienteRepository.findByDni(cliente.getDni());
        if (clienteMismoDni.isPresent() && !clienteMismoDni.get().getDni().equals(cliente.getDni())) {
            throw new IllegalArgumentException("Ya existe otro cliente con el DNI: " + cliente.getDni());
        }

        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setApellido(cliente.getApellido());
        clienteExistente.setDni(cliente.getDni());

        clienteRepository.save(clienteExistente);
    }

    private void validarCliente(Cliente cliente){
        if (cliente.getNombre() == null || cliente.getNombre().isBlank()
                || cliente.getApellido() == null || cliente.getApellido().isBlank()
                || cliente.getDni() == null || cliente.getDni().isBlank()) {
            throw new IllegalArgumentException("Debe ingresar nombre, apellido y DNI del cliente");
        }
    }
}
