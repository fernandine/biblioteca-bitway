package com.bitway.biblioteca.repositories;

import com.bitway.biblioteca.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query(nativeQuery = true, value = "SELECT obj.cep, "
            + "COUNT(obj.cep) "
            + "FROM tb_funcionario obj "
            + "GROUP BY obj.cep "
            + "ORDER BY COUNT(obj.cep) DESC LIMIT 1" )
    List<Object> consultaCepIncidente();
}
