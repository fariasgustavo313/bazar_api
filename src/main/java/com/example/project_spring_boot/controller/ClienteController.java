package com.example.project_spring_boot.controller;

import com.example.project_spring_boot.model.Cliente;
import com.example.project_spring_boot.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> obtenerClientes(){
        return clienteService.obtenerClientes();
    }

    @GetMapping("/{id}")
    public Cliente obtenerClientePorId(@PathVariable Long id){
        return clienteService.obtenerClientePorId(id);
    }

    @PostMapping
    public void crearCliente(@RequestBody Cliente cliente){
        clienteService.crearCliente(cliente);
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Long id){
        clienteService.eliminarCliente(id);
    }

    @PutMapping("/{id}")
    public void actualizarCliente(@PathVariable Long id,
                                  @RequestBody Cliente cliente){
        clienteService.actualizarCliente(id, cliente);
    }
}
