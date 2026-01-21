/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BazarApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author César
 */

@Getter @Setter
@Entity
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long codigo_producto;
    private String nombre;
    private String marca;
    private Double coste;
    private Double cantidad_disponible;

    public Producto(Long codigo_producto, String nombre, String marca, Double coste, Double cantidad_disponible) {
        this.codigo_producto = codigo_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.coste = coste;
        this.cantidad_disponible = cantidad_disponible;
    }
}
