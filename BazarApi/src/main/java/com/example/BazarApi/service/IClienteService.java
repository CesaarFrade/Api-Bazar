/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.BazarApi.service;

import com.example.BazarApi.model.Cliente;
import java.util.List;

/**
 *
 * @author César
 */
public interface IClienteService {
    public List<Cliente> getClientes();
    public Cliente findCliente(Long id_cliente);
    public void saveCliente(Cliente cliente);
    public void deleteCliente(Long id_cliente);
    public void editCliente(Cliente cliente, Long id_cliente);
}
