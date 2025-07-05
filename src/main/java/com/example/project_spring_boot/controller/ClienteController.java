package com.example.project_spring_boot.controller;

import com.example.project_spring_boot.model.Cliente;
import com.example.project_spring_boot.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerClientes(){
        return ResponseEntity.ok(clienteService.obtenerClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.obtenerClientePorId(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente){
        clienteService.crearCliente(cliente);
        return ResponseEntity.created(URI.create("/clientes/"+cliente.getId())).body(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> eliminarCliente(@PathVariable Long id){
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public void actualizarCliente(@PathVariable Long id,
                                  @RequestBody Cliente cliente){
        clienteService.actualizarCliente(id, cliente);
    }
}
