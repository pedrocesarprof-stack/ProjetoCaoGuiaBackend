package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.service;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.dto.TreinamentoRequestDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.dto.TreinamentoResponseDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.model.Treinamento;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.repository.TreinamentoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TreinamentoService {

    private final TreinamentoRepository repository;

    @Transactional
    public TreinamentoResponseDTO criar(TreinamentoRequestDTO dto) {

        Treinamento treinamento = Treinamento.builder()
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .etapa(dto.getEtapa())
                .build();

        return TreinamentoResponseDTO.from(repository.save(treinamento));
    }

    public Page<TreinamentoResponseDTO> listar(Pageable pageable) {
        return repository.findAll(pageable)
                .map(TreinamentoResponseDTO::from);
    }

    public TreinamentoResponseDTO buscarPorId(Long id) {

        Treinamento treinamento = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Treinamento não encontrado"));

        return TreinamentoResponseDTO.from(treinamento);
    }

    @Transactional
    public TreinamentoResponseDTO atualizar(Long id, TreinamentoRequestDTO dto) {

        Treinamento treinamento = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Treinamento não encontrado"));

        treinamento.setTitulo(dto.getTitulo());
        treinamento.setDescricao(dto.getDescricao());
        treinamento.setEtapa(dto.getEtapa());

        return TreinamentoResponseDTO.from(repository.save(treinamento));
    }

    @Transactional
    public void remover(Long id) {

        Treinamento treinamento = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Treinamento não encontrado"));

        repository.delete(treinamento);
    }
}