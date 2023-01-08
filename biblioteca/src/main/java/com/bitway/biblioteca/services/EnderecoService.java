package com.bitway.biblioteca.services;

import com.bitway.biblioteca.DTO.ClienteDTO;
import com.bitway.biblioteca.DTO.EnderecoDTO;
import com.bitway.biblioteca.entities.Cliente;
import com.bitway.biblioteca.entities.Endereco;
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
import java.util.stream.Collectors;

@Service
public class EnderecoService {

        @Autowired
        private EnderecoRepository enderecoRepository;


        @Transactional(readOnly = true)
        public List<EnderecoDTO> findAll() {
            List<Endereco> list = enderecoRepository.findAll();
            return list.stream().map(x -> new EnderecoDTO(x)).collect(Collectors.toList());
        }

        //BUSCA PAGINADA
        @Transactional(readOnly = true)
        public Page<EnderecoDTO> findAllPaged(Pageable pageable) {
            Page<Endereco> list = enderecoRepository.findAll(pageable);
            return list.map(x -> new EnderecoDTO(x));
        }

        @Transactional
        public EnderecoDTO insert(EnderecoDTO dto) {
            Endereco entity = new Endereco();

            entity.setCep(dto.getCep());
            entity.setBairro(dto.getBairro());
            entity.setComplemento(dto.getComplemento());
            entity.setLogradouro(dto.getLogradouro());
            entity.setLocalidade(dto.getLocalidade());

            Cliente cliente = new Cliente();
            cliente.setId(dto.getClientId());
            entity.setCliente(cliente);

            entity = enderecoRepository.save(entity);
            return new EnderecoDTO(entity);
        }

        @Transactional
        public EnderecoDTO update(Long id, EnderecoDTO dto) {
            try {
                Endereco entity = enderecoRepository.getReferenceById(id);
                entity.setCep(dto.getCep());
                entity.setBairro(dto.getBairro());
                entity.setComplemento(dto.getComplemento());
                entity.setLogradouro(dto.getLogradouro());
                entity.setLocalidade(dto.getLocalidade());
                entity = enderecoRepository.save(entity);
                return new EnderecoDTO(entity);
            } catch (EntityNotFoundException e) {
                throw new ResourceNotFoundException("Id not found " + id);
            }
        }

        public void delete(Long id) {
            try {
                enderecoRepository.deleteById(id);
            } catch (EmptyResultDataAccessException e) {
                throw new ResourceNotFoundException("Id not found " + id);
            } catch (DataIntegrityViolationException e) {
                throw new DatabaseException("Integrity violation");
            }
        }

    }