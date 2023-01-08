package com.bitway.biblioteca.DTO;

import com.bitway.biblioteca.entities.Cliente;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO implements Serializable {

    private Long id;
    @NotBlank
    @Size(min = 3, max = 100)
    private String nome;
    private String email;
    private String cpf;

    public List<EnderecoDTO> enderecos = new ArrayList<>();

    public ClienteDTO(Cliente entity) {
        id = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();
        cpf = entity.getCpf();
        entity.getEnderecos().forEach(endereco -> this.enderecos.add(new EnderecoDTO(endereco)));
    }
}
