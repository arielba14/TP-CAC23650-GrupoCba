package com.cac.tpcacfinal.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Dolar {
    private String moneda;
    private String casa;
    private String nombre;
    private Double compra;
    private Double venta;
    private LocalDateTime fechaActualizacion;
}
