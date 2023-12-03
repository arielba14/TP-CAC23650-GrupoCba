package com.cac.tpcacfinal.controllers;

import com.cac.tpcacfinal.entities.dtos.DolarDto;
import com.cac.tpcacfinal.services.DolarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/dolar")
public class DolarController {
    private final DolarService dolarService;

    private DolarController(DolarService dolarService){
        this.dolarService = dolarService;
    }

    @GetMapping(value = "oficial")
    public DolarDto dolarOficial(){
        return dolarService.getDolarOficial();
    }

    @GetMapping (value = "solidario")
    public DolarDto dolarSolidario() {return dolarService.getDolarSolidario();}
}
