/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.BazarApi.service;

import com.example.BazarApi.model.Producto;
import com.example.BazarApi.model.Venta;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author César
 */
public interface IVentaService {
    public List<Venta> getVentas();
    public Venta findVenta(Long id_venta);
    public void saveVenta(Venta venta);
    public void deleteVenta(Long id_venta);
    public void editVenta(Venta venta, Long id_venta);
    public List<Producto> getProductosVenta(Long id_venta);
    public String getMontoYCantidadVentaDeUnDia(LocalDate dia);
}
