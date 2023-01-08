package com.bitway.biblioteca.resources;

import com.bitway.biblioteca.DTO.ClienteDTO;
import com.bitway.biblioteca.repositories.ClienteRepository;
import com.bitway.biblioteca.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api(tags = "Cliente Resource", value = "ClienteResource")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @ApiOperation(value = "Obtendo a lista de todos os clientes e seus respectivos endereços.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista criada com sucesso"),
            @ApiResponse(code = 400, message = "Houve um erro!")
    })
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> list = clienteService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "Obtendo as informações do cliente pelo seu ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
        ClienteDTO dto = clienteService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @ApiOperation(value = "Cadastrando no banco de dados um cliente.")
    @PostMapping
    public ResponseEntity<ClienteDTO> insert(@Valid @RequestBody ClienteDTO dto) {
        ClienteDTO newDto = clienteService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @ApiOperation(value = "Atualizando um cliente pelo seu ID")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update( @PathVariable Long id, @RequestBody ClienteDTO dto) {
        ClienteDTO newDto = clienteService.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @ApiOperation(value = "Deleta um cliente pelo seu ID")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
