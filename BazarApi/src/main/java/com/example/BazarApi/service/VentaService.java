/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BazarApi.service;

import com.example.BazarApi.exception.NotFoundException;
import com.example.BazarApi.model.Cliente;
import com.example.BazarApi.model.Producto;
import com.example.BazarApi.model.Venta;
import com.example.BazarApi.repository.ClienteRepository;
import com.example.BazarApi.repository.VentaRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author César
 */

@Service
public class VentaService implements IVentaService {
    @Autowired
    private VentaRepository venRepo;
    @Autowired
    private ClienteRepository cliRepo;
    @Autowired
    private ProductoService proServ;
    
    //Métodos CRUD
    @Override
    public List<Venta> getVentas() {
        return venRepo.findAll();
    }

    @Override
    public Venta findVenta(Long id_venta) {
        Venta venta = venRepo.findById(id_venta).orElse(null);
        if(venta != null){
            return venta; 
        } else{
            throw new NotFoundException("Por el momento, no existe ninguna venta con "
                    + "el id indicado");
        }
    }

    @Override
    @Transactional // Si algo falla, no se guarda ni la venta ni se descuenta stock
    public void saveVenta(Venta venta) {
        List<Producto> listaProductosVenta = venta.getListaProductos();
    
        if (listaProductosVenta == null || listaProductosVenta.isEmpty()) {
            throw new NotFoundException("La venta debe contener al menos un producto");
        }

        for(Producto p : listaProductosVenta) {
            // BUSCAMOS el producto real en la DB usando el ID que viene en el JSON
            Producto prodDb = proServ.findProducto(p.getCodigo_producto());

            if(prodDb.getCantidad_disponible() > 0) {
                // Restamos sobre el valor REAL de la base de datos
                prodDb.setCantidad_disponible(prodDb.getCantidad_disponible() - 1);

                // Guardamos el cambio de stock
                proServ.editProducto(prodDb, prodDb.getCodigo_producto());
            } else {
                throw new NotFoundException("No hay stock para: " + prodDb.getNombre());
            }
        }

        // Al final, guardamos la venta
        venRepo.save(venta);
    }

    @Override
    public void deleteVenta(Long id_venta) {
        Venta venta = venRepo.findById(id_venta).orElse(null);
        if(venta != null){
            venRepo.deleteById(id_venta); 
        } else{
            throw new NotFoundException("Por el momento, no existe ninguna venta con "
                    + "el id indicado");
        }
    }

    @Override
    public void editVenta(Venta venta, Long id_venta) {
        Venta ventaExistente = venRepo.findById(id_venta).orElseThrow(() -> 
            new NotFoundException("Por el momento, no existe ninguna venta con "
                    + "el id indicado"));

        // Seteo manual de los campos no nulos
        LocalDate nuevaFecha = venta.getFecha_venta();
        if(nuevaFecha != null){
            ventaExistente.setFecha_venta(nuevaFecha);
        }
        Double total = venta.getTotal();
        if(total != null){
            ventaExistente.setTotal(total);
        }
        List<Producto> listaProductos = venta.getListaProductos();
        if(listaProductos != null){
            ventaExistente.setListaProductos(listaProductos);
        }
        if (venta.getUnCliente() != null) {
            Cliente cliente = cliRepo.findById(venta.getUnCliente().getId_cliente()).orElse(null);
            if (cliente != null) {
                ventaExistente.setUnCliente(cliente);
            }
        }
        saveVenta(ventaExistente);
    }

    @Override
    public List<Producto> getProductosVenta(Long id_venta) {
        Venta venta = venRepo.findById(id_venta).orElse(null);
        if(venta == null){
            throw new NotFoundException("Por el momento, no existe ninguna venta con el id indicado");
        }
        return venta.getListaProductos();
    }
}
