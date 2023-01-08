package com.bitway.biblioteca.resources;

import com.bitway.biblioteca.DTO.EnderecoDTO;
import com.bitway.biblioteca.services.EnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api(tags = "Endereço Via Cep Resource", value = "ViaCepResource")
@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

    @Autowired
    private EnderecoService service;

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> findAll() {
        List<EnderecoDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "Cadastrando no banco de dados um cliente.")
    @PostMapping
    public ResponseEntity<EnderecoDTO> insert(@Valid @RequestBody EnderecoDTO dto) {
        EnderecoDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @ApiOperation(value = "Atualizando um cliente pelo seu ID")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EnderecoDTO> update(@PathVariable Long id, @RequestBody EnderecoDTO dto) {
        EnderecoDTO newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @ApiOperation(value = "Deleta um cliente pelo seu ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
