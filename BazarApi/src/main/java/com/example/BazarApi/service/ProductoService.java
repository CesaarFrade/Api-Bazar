/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BazarApi.service;

import com.example.BazarApi.exception.NotFoundException;
import com.example.BazarApi.model.Producto;
import com.example.BazarApi.repository.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author César
 */

@Service
public class ProductoService implements IProductoService{
    @Autowired
    private ProductoRepository proRepo;
    
    @Override
    public List<Producto> getProductos() {
        return proRepo.findAll();
    }

    @Override
    public void saveProducto(Producto producto) {
        proRepo.save(producto);
    }

    @Override
    public void deleteProducto(Long id_producto) {
        if(proRepo.existsById(id_producto)){
            proRepo.deleteById(id_producto);
        } else{
            throw new NotFoundException("Por el momento, no existe ningun producto con "
                    + "el id indicado");
        }
    }

    @Override
    public void editProducto(Producto producto, Long id_producto) {
        if(proRepo.existsById(id_producto)){
            saveProducto(producto);
        } else{
            throw new NotFoundException("Por el momento, no existe ningun producto con "
                    + "el id indicado");
        }
    }   
}
