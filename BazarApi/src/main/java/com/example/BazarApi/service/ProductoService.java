/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BazarApi.service;

import com.example.BazarApi.exception.NotFoundException;
import com.example.BazarApi.model.Producto;
import com.example.BazarApi.repository.ProductoRepository;
import java.util.ArrayList;
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
    
    //Métodos CRUD
    @Override
    public List<Producto> getProductos() {
        return proRepo.findAll();
    }
    
    @Override
    public Producto findProducto(Long id_producto) {
        Producto producto = proRepo.findById(id_producto).orElse(null);
        if(producto != null){
            return producto;
        } else{
            throw new NotFoundException("Por el momento, no existe ningun producto con "
                    + "el id indicado");
        }
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
        Producto productoExistente = proRepo.findById(id_producto).orElseThrow(() -> 
            new NotFoundException("Por el momento, no existe ningun producto con "
                    + "el id indicado"));
        String nombre = producto.getNombre();
        if(nombre != null){
            productoExistente.setNombre(nombre);
        }
        String marca = producto.getMarca();
        if(marca != null){
            productoExistente.setMarca(marca);
        }
        Double coste = producto.getCoste();
        if(coste != null){
            productoExistente.setCoste(coste);
        }
        Double cantidadDisponible = producto.getCantidad_disponible();
        if(cantidadDisponible != null){
            productoExistente.setCantidad_disponible(cantidadDisponible);
        }
        saveProducto(productoExistente);
    }   
    
    //Otros Métodos
    public List<Producto> getProductosStockMenorACinco(){
        List<Producto> productosTotales = getProductos();
        ArrayList<Producto> productosPocoStock = new ArrayList<>();
        for(Producto producto : productosTotales){
            if(producto.getCantidad_disponible() < 5){
                productosPocoStock.add(producto);
            }
        }
        return productosPocoStock;
    }
}
