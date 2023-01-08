package com.bitway.biblioteca.DTO;

import com.bitway.biblioteca.entities.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO implements Serializable {

    private Long id;
    @NotBlank
    @Size(min = 3, max = 100)
    private String nome;
    @NotBlank
    @Size(min = 3, max = 100)
    private String cargo;
    private String cep;

    public FuncionarioDTO(Funcionario entity) {
        id = entity.getId();
        nome = entity.getNome();
        cargo = entity.getCargo();
        cep = entity.getCep();
    }

}
