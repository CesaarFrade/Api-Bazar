/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BazarApi.controller;

import com.example.BazarApi.model.Producto;
import com.example.BazarApi.service.ProductoService;
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
public class ProductoController {
    @Autowired
    private ProductoService proServ;
    
    @GetMapping("/productos")
    public List<Producto> getProductos(){
        return proServ.getProductos();
    }
    
    @PostMapping("/productos/crear")
    public void saveProducto(@RequestBody Producto producto){
        proServ.saveProducto(producto);
    }
    
    @DeleteMapping("/productos/eliminar/{codigo_producto}")
    public void deleteProducto(@PathVariable Long codigo_producto){
        proServ.deleteProducto(codigo_producto);
    }
    
    @PutMapping("/productos/editar/{codigo_producto}")
    public void editProducto(@PathVariable Long codigo_producto, @RequestBody Producto producto){
        proServ.editProducto(producto, codigo_producto);
    }
}
