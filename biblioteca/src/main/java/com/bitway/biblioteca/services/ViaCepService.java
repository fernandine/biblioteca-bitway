package com.bitway.biblioteca.services;

import com.bitway.biblioteca.DTO.EnderecoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(url="viacep.com.br/ws/", name="viacep")
public interface ViaCepService {

    @GetMapping("/{cep}/json/")
    EnderecoDTO findByCep(@PathVariable("cep") String cep);
}
