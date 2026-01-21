/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.BazarApi.service;

import com.example.BazarApi.model.Producto;
import java.util.List;

/**
 *
 * @author César
 */
public interface IProductoService {
    public List<Producto> getProductos();
    public void saveProducto(Producto producto);
    public void deleteProducto(Long id_producto);
    public void editProducto(Producto producto, Long id_producto);
}
