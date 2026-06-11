package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.service;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.core.EntityNotFoundException;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.core.security.AuthenticationFacade;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.TipoEntidade;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.TipoEventoEstatistica;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.service.EstatisticaService;
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
    private final EstatisticaService estatisticaService;
    private final AuthenticationFacade authenticationFacade;

    @Transactional
    public TreinamentoResponseDTO criar(TreinamentoRequestDTO dto) {
        var usuarioLogado = authenticationFacade.getAuthenticatedUser();

        Treinamento treinamento = Treinamento.builder()
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .etapa(dto.getEtapa())
                .criadoPor(usuarioLogado)
                .atualizadoPor(usuarioLogado)
                .build();

        var salvo = repository.save(treinamento);
        estatisticaService.registrarEventoInterno(salvo.getId(), TipoEntidade.TREINAMENTO, TipoEventoEstatistica.CRIACAO);
        return TreinamentoResponseDTO.from(salvo);
    }

    public Page<TreinamentoResponseDTO> listar(Pageable pageable) {
        return repository.findAll(pageable)
                .map(TreinamentoResponseDTO::from);
    }

    public TreinamentoResponseDTO buscarPorId(Long id) {
        Treinamento treinamento = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Treinamento", id));
        estatisticaService.registrarEventoInterno(id, TipoEntidade.TREINAMENTO, TipoEventoEstatistica.VISUALIZACAO);
        return TreinamentoResponseDTO.from(treinamento);
    }

    @Transactional
    public TreinamentoResponseDTO atualizar(Long id, TreinamentoRequestDTO dto) {
        var usuarioLogado = authenticationFacade.getAuthenticatedUser();

        Treinamento treinamento = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Treinamento", id));

        treinamento.setTitulo(dto.getTitulo());
        treinamento.setDescricao(dto.getDescricao());
        treinamento.setEtapa(dto.getEtapa());
        treinamento.setAtualizadoPor(usuarioLogado);

        var salvo = repository.save(treinamento);
        estatisticaService.registrarEventoInterno(id, TipoEntidade.TREINAMENTO, TipoEventoEstatistica.ATUALIZACAO);
        return TreinamentoResponseDTO.from(salvo);
    }

    @Transactional
    public void remover(Long id) {
        Treinamento treinamento = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Treinamento", id));
        repository.delete(treinamento);
        estatisticaService.registrarEventoInterno(id, TipoEntidade.TREINAMENTO, TipoEventoEstatistica.EXCLUSAO);
    }
}