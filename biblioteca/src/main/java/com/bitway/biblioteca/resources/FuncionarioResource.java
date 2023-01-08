package com.bitway.biblioteca.resources;

import com.bitway.biblioteca.DTO.FuncionarioDTO;
import com.bitway.biblioteca.repositories.FuncionarioRepository;
import com.bitway.biblioteca.services.FuncionarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "Funcionario Resource", value = "FuncionarioResource")
@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioResource {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @ApiOperation(value = "Obtendo a lista de todos os funcionários.")
    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> findAll() {
        List<FuncionarioDTO> list = funcionarioService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "Recebendo a informação de um funcionário pelo seu ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long id) {
        FuncionarioDTO dto = funcionarioService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @ApiOperation(value = "Cadastrando informações de um funcionário")
    @PostMapping
    public ResponseEntity<FuncionarioDTO> insert(@RequestBody FuncionarioDTO obj) {
        obj = funcionarioService.insert(obj);
        return ResponseEntity.ok(obj);
    }

    @ApiOperation(value = "Atualizando os dados do funcionário")
    @PutMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> update( @PathVariable Long id, @RequestBody FuncionarioDTO dto) {
        FuncionarioDTO newDto = funcionarioService.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @ApiOperation(value = "Deleta os dados de um funcionário pelo seu ID")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        funcionarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Consulta CEP com maior incidência dos funcionários e mostra a qtde.")
    @GetMapping("/consultaIncidencia")
    public List<Object> consultaCepIncidente(){
        return funcionarioRepository.consultaCepIncidente();
    }

}

