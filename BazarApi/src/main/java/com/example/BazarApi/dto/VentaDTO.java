/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BazarApi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author César
 */

@NoArgsConstructor
@Getter @Setter
public class VentaDTO {
    Long codigo_venta;
    int cantidad_productos;
    String nombre_cliente;
    String apellido_cliente;
}
