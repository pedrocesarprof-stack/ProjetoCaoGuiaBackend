package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.service;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.dto.DepoimentoRequestDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.dto.DepoimentoResponseDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.model.Depoimento;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.repository.DepoimentoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DepoimentoService {

    private final DepoimentoRepository repository;

    @Transactional
    public DepoimentoResponseDTO criar(DepoimentoRequestDTO dto) {

        Depoimento depoimento = Depoimento.builder()
                .descricao(dto.getDescricao())
                .build();

        return DepoimentoResponseDTO.from(repository.save(depoimento));
    }

    public Page<DepoimentoResponseDTO> listar(Pageable pageable) {
        return repository.findAll(pageable)
                .map(DepoimentoResponseDTO::from);
    }

    public DepoimentoResponseDTO buscarPorId(Long id) {

        Depoimento depoimento = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Depoimento não encontrado"));

        return DepoimentoResponseDTO.from(depoimento);
    }

    @Transactional
    public DepoimentoResponseDTO atualizar(Long id, DepoimentoRequestDTO dto) {

        Depoimento depoimento = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Depoimento não encontrado"));

        depoimento.setDescricao(dto.getDescricao());

        return DepoimentoResponseDTO.from(repository.save(depoimento));
    }

    @Transactional
    public void remover(Long id) {

        Depoimento depoimento = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Depoimento não encontrado"));

        repository.delete(depoimento);
    }
}