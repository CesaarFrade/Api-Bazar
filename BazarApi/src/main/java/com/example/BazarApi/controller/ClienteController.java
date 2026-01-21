/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BazarApi.controller;

import com.example.BazarApi.model.Cliente;
import com.example.BazarApi.service.ClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author César
 */

@RestController
public class ClienteController {
    @Autowired
    private ClienteService cliServ;
    
    @GetMapping("/clientes")
    public List<Cliente> getClientes(){
        return cliServ.getClientes();
    }
    
    @GetMapping("/clientes/{id_cliente}")
    public Cliente getClienteById(@PathVariable Long id_cliente){
        return cliServ.findCliente(id_cliente);
    }
    
    @PostMapping("/clientes/crear")
    public void saveCliente(@RequestBody Cliente cliente){
        cliServ.saveCliente(cliente);
    }
    
    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    public void deleteCliente(@PathVariable Long id_cliente){
        cliServ.deleteCliente(id_cliente);
    }
    
    @PutMapping("/clientes/editar/{id_cliente}")
    public void editCliente(@PathVariable Long id_cliente, @RequestBody Cliente cliente){
        cliServ.editCliente(cliente, id_cliente);
    }
}
