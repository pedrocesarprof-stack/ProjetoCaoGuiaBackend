package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.service;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.core.EntityNotFoundException;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.core.security.AuthenticationFacade;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.dto.DepoimentoRequestDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.dto.DepoimentoResponseDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.model.Depoimento;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.repository.DepoimentoRepository;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.TipoEntidade;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.TipoEventoEstatistica;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.service.EstatisticaService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DepoimentoService {

    private final DepoimentoRepository repository;
    private final EstatisticaService estatisticaService;
    private final AuthenticationFacade authenticationFacade;

    @Transactional
    public DepoimentoResponseDTO criar(DepoimentoRequestDTO dto) {
        var usuarioLogado = authenticationFacade.getAuthenticatedUser();

        Depoimento depoimento = Depoimento.builder()
                .descricao(dto.getDescricao())
                .criadoPor(usuarioLogado)
                .atualizadoPor(usuarioLogado)
                .build();

        var salvo = repository.save(depoimento);
        estatisticaService.registrarEventoInterno(salvo.getId(), TipoEntidade.DEPOIMENTO, TipoEventoEstatistica.CRIACAO);
        return DepoimentoResponseDTO.from(salvo);
    }

    public Page<DepoimentoResponseDTO> listar(Pageable pageable) {
        return repository.findAll(pageable)
                .map(DepoimentoResponseDTO::from);
    }

    public DepoimentoResponseDTO buscarPorId(Long id) {
        Depoimento depoimento = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Depoimento", id));
        estatisticaService.registrarEventoInterno(id, TipoEntidade.DEPOIMENTO, TipoEventoEstatistica.VISUALIZACAO);
        return DepoimentoResponseDTO.from(depoimento);
    }

    @Transactional
    public DepoimentoResponseDTO atualizar(Long id, DepoimentoRequestDTO dto) {
        var usuarioLogado = authenticationFacade.getAuthenticatedUser();

        Depoimento depoimento = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Depoimento", id));

        depoimento.setDescricao(dto.getDescricao());
        depoimento.setAtualizadoPor(usuarioLogado);

        var salvo = repository.save(depoimento);
        estatisticaService.registrarEventoInterno(id, TipoEntidade.DEPOIMENTO, TipoEventoEstatistica.ATUALIZACAO);
        return DepoimentoResponseDTO.from(salvo);
    }

    @Transactional
    public void remover(Long id) {
        Depoimento depoimento = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Depoimento", id));
        repository.delete(depoimento);
        estatisticaService.registrarEventoInterno(id, TipoEntidade.DEPOIMENTO, TipoEventoEstatistica.EXCLUSAO);
    }
}