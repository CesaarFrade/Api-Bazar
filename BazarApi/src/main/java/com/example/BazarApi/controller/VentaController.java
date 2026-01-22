/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BazarApi.controller;

import com.example.BazarApi.dto.VentaDTO;
import com.example.BazarApi.model.Venta;
import com.example.BazarApi.service.VentaService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author César
 */
public class VentaController {
    @Autowired
    private VentaService venServ;
    
    @GetMapping("/ventas")
    public List<Venta> getVentas(){
        return venServ.getVentas();
    }
    
    @GetMapping("/ventas/{id_venta}")
    public Venta getVentaById(@PathVariable Long id_venta){
        return venServ.findVenta(id_venta);
    }
    
    @PostMapping("/ventas/crear")
    public void saveVenta(@RequestBody Venta venta){
        venServ.saveVenta(venta);
    }
    
    @DeleteMapping("/ventas/eliminar/{id_venta}")
    public void deleteVenta(@PathVariable Long id_venta){
        venServ.deleteVenta(id_venta);
    }
    
    @PutMapping("/ventas/editar/{id_venta}")
    public void editVenta(@PathVariable Long id_venta, @RequestBody Venta venta){
        venServ.editVenta(venta, id_venta);
    }
    
    @GetMapping("/ventas/productos/{codigo_venta}")
    public void getProductosVenta(@PathVariable Long codigo_venta){
        venServ.getProductosVenta(codigo_venta);
    }
    
    @GetMapping("/ventas/{fecha_venta}")
    public String getMontoYVentasDia(@PathVariable LocalDate fecha_venta){
        return venServ.getMontoYCantidadVentaDeUnDia(fecha_venta);
    }
    
    @GetMapping("/ventas/mayor_venta")
    public VentaDTO getInfoVentaMasCara(){
        return venServ.getVentaMasCara();
    }
}
