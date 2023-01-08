package com.bitway.biblioteca.services;

import com.bitway.biblioteca.DTO.ClienteDTO;
import com.bitway.biblioteca.DTO.EnderecoDTO;
import com.bitway.biblioteca.entities.Cliente;
import com.bitway.biblioteca.entities.Endereco;
import com.bitway.biblioteca.repositories.ClienteRepository;
import com.bitway.biblioteca.repositories.EnderecoRepository;
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
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {
        List<Cliente> list = clienteRepository.findAll();
        return list.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
    }

    //BUSCA PAGINADA
    @Transactional(readOnly = true)
    public Page<ClienteDTO> findAllPaged(Pageable pageable) {
        Page<Cliente> list = clienteRepository.findAll(pageable);
        return list.map(x -> new ClienteDTO(x));
    }

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        Cliente entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ClienteDTO(entity);
    }

    @Transactional
    public ClienteDTO insert(ClienteDTO dto) {
        Cliente entity = new Cliente();
        copyDtoToEntity(dto, entity);
        entity = clienteRepository.save(entity);
        return new ClienteDTO(entity);
    }

    @Transactional
    public ClienteDTO update(Long id, ClienteDTO dto) {
        try {
            Cliente entity = clienteRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = clienteRepository.save(entity);
            return new ClienteDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            clienteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(ClienteDTO dto, Cliente entity) {

        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setCpf(dto.getCpf());

        for (EnderecoDTO end: dto.getEnderecos()) {
            Endereco endereco = new Endereco();
            endereco.setId(end.getId());
            entity.getEnderecos().add(endereco);
        }
    }
}



