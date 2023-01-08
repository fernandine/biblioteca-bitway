package com.bitway.biblioteca.DTO;

import com.bitway.biblioteca.entities.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO implements Serializable {

    private Long id;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    private Long clientId;

    public EnderecoDTO(Endereco entity) {
     id = entity.getId();
     cep = entity.getCep();
     logradouro = entity.getLogradouro();
     bairro = entity.getBairro();
     localidade = entity.getLocalidade();
     uf = entity.getUf();
     clientId = entity.getCliente().getId();
    }
}
