/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BazarApi.service;

import com.example.BazarApi.exception.NotFoundException;
import com.example.BazarApi.model.Cliente;
import com.example.BazarApi.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author César
 */

@Service
public class ClienteService implements IClienteService{
    @Autowired
    private ClienteRepository cliRepo;
    
    //Métodos CRUD
    @Override
    public List<Cliente> getClientes() {
        return cliRepo.findAll();
    }
    
    @Override
    public Cliente findCliente(Long id_cliente) {
        Cliente cliente = cliRepo.findById(id_cliente).orElse(null);
        if(cliente != null){
            return cliente;
        } else{
            throw new NotFoundException("Por el momento, no existe ningun cliente"
                    + "con el id indicado");
        }
    }

    @Override
    public void saveCliente(Cliente cliente) {
        cliRepo.save(cliente);
    }

    @Override
    public void deleteCliente(Long id_cliente) {
        if(cliRepo.existsById(id_cliente)){
            cliRepo.deleteById(id_cliente);
        } else{
            throw new NotFoundException("Por el momento, no existe ningun cliente"
                    + "con el id indicado");
        }  
    }

    @Override
    public void editCliente(Cliente cliente, Long id_cliente) {
        Cliente clienteExistente = cliRepo.findById(id_cliente).orElseThrow(() -> 
            new NotFoundException("Por el momento, no existe ningun cliente con "
                    + "el id indicado"));
        String nombre = cliente.getNombre();
        if(nombre != null){
            clienteExistente.setNombre(nombre);
        }
        String apellido = cliente.getApellido();
        if(apellido != null){
            clienteExistente.setApellido(apellido);
        }
        String dni = cliente.getDni();
        if(dni != null){
            clienteExistente.setDni(dni);
        }
        saveCliente(clienteExistente);
    }
}
