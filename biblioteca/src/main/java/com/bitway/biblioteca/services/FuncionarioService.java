package com.bitway.biblioteca.services;

import com.bitway.biblioteca.DTO.FuncionarioDTO;
import com.bitway.biblioteca.entities.Funcionario;
import com.bitway.biblioteca.repositories.EnderecoRepository;
import com.bitway.biblioteca.repositories.FuncionarioRepository;
import com.bitway.biblioteca.services.exceptions.DatabaseException;
import com.bitway.biblioteca.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional(readOnly = true)
    public List<FuncionarioDTO> findAll() {
        List<Funcionario> list = funcionarioRepository.findAll();
        return list.stream().map(x -> new FuncionarioDTO(x)).collect(Collectors.toList());
    }

    //BUSCA PAGINADA
    @Transactional(readOnly = true)
    public Page<FuncionarioDTO> findAllPaged(Pageable pageable) {
        Page<Funcionario> list = funcionarioRepository.findAll(pageable);
        return list.map(x -> new FuncionarioDTO(x));
    }

    @Transactional(readOnly = true)
    public FuncionarioDTO findById(Long id) {
        Optional<Funcionario> obj = funcionarioRepository.findById(id);
        Funcionario entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new FuncionarioDTO(entity);
    }

    @Transactional
    public FuncionarioDTO insert(FuncionarioDTO dto) {
        Funcionario entity = new Funcionario();
        copyDtoToEntity(dto, entity);

        entity = funcionarioRepository.save(entity);
        return new FuncionarioDTO(entity);
    }

    @Transactional
    public FuncionarioDTO update(Long id, FuncionarioDTO dto) {
        try {
            Funcionario entity = funcionarioRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = funcionarioRepository.save(entity);
            return new FuncionarioDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            funcionarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(FuncionarioDTO dto, Funcionario entity) {
        entity.setNome(dto.getNome());
        entity.setCargo(dto.getCargo());
        entity.setCep(dto.getCep());
    }
}