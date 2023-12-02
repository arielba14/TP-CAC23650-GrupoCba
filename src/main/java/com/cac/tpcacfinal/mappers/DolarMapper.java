package com.cac.tpcacfinal.mappers;

import com.cac.tpcacfinal.entities.Dolar;
import com.cac.tpcacfinal.entities.dtos.DolarDto;

public class DolarMapper {
    public DolarDto dolarToDto(Dolar dolar){
        DolarDto dto = new DolarDto();
        dto.setCompra(dolar.getCompra());
        dto.setVenta(dolar.getVenta());
        dto.setFechaActualizacion(dolar.getFechaActualizacion());
        return dto;
    }
}
