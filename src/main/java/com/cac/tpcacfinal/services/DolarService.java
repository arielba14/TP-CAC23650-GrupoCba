package com.cac.tpcacfinal.services;

import com.cac.tpcacfinal.entities.dtos.DolarDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DolarService {
    private final String apiUrl = "https://dolarapi.com/v1/dolares/oficial";

    private final RestTemplate restTemplate;

    private DolarService(RestTemplateBuilder restTemplate){
        this.restTemplate = restTemplate.build();
    }

    public DolarDto getDolar(){
        return restTemplate.getForObject(apiUrl, DolarDto.class);
    }
}
