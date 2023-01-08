package com.bitway.biblioteca.resources;

import com.bitway.biblioteca.DTO.EnderecoDTO;
import com.bitway.biblioteca.entities.Endereco;
import com.bitway.biblioteca.services.ViaCepService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @Api(tags = "Endereço Via Cep Resource", value = "ViaCepResource")
    @RestController
    @RequestMapping(value = "/enderecos")
    public class ViaCepResource {

    @Autowired
    private ViaCepService service;

    @ApiOperation(value = "Obtendo as informações de endereço pelo CEP")
    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoDTO> consultaCep(@PathVariable String cep) {
        EnderecoDTO dto = service.findByCep(cep);
        return ResponseEntity.ok().body(dto);
    }
}
