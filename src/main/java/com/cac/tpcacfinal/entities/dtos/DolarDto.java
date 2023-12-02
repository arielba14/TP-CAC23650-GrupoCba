package com.cac.tpcacfinal.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DolarDto {
    private Double compra;
    private Double venta;
    private LocalDateTime fechaActualizacion;
}
