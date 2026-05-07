package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.noticias.service;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.core.NoticiaNotFoundException;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.noticias.model.*;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.noticias.repository.NoticiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NoticiaService {

    private final NoticiaRepository repository;

    @Transactional
    public NoticiaResponseDTO criar(NoticiaRequestDTO dto) {
        var noticia = Noticia.builder()
                .titulo(dto.getTitulo())
                .conteudo(dto.getConteudo())
                .resumo(dto.getResumo())
                .autor(dto.getAutor())
                .categoria(dto.getCategoria())
                .status(dto.getStatus() != null ? dto.getStatus() : StatusNoticia.RASCUNHO)
                .tags(dto.getTags())
                .imagemUrl(dto.getImagemUrl())
                .build();
        return NoticiaResponseDTO.from(repository.save(noticia));
    }

    public NoticiaResponseDTO buscarPorId(Long id) {
        return NoticiaResponseDTO.from(findOrThrow(id));
    }

    @Transactional
    public NoticiaResponseDTO atualizar(Long id, NoticiaRequestDTO dto) {
        var noticia = findOrThrow(id);
        noticia.setTitulo(dto.getTitulo());
        noticia.setConteudo(dto.getConteudo());
        noticia.setResumo(dto.getResumo());
        noticia.setAutor(dto.getAutor());
        noticia.setCategoria(dto.getCategoria());
        if (dto.getStatus() != null) noticia.setStatus(dto.getStatus());
        noticia.setTags(dto.getTags());
        noticia.setImagemUrl(dto.getImagemUrl());
        return NoticiaResponseDTO.from(repository.save(noticia));
    }

    @Transactional
    public NoticiaResponseDTO atualizarStatus(Long id, StatusNoticia status) {
        var noticia = findOrThrow(id);
        noticia.setStatus(status);
        return NoticiaResponseDTO.from(repository.save(noticia));
    }

    @Transactional
    public void deletar(Long id) {
        findOrThrow(id);
        repository.deleteById(id);
    }

    public Page<NoticiaResponseDTO> listar(
            String titulo, String autor, CategoriaNoticia categoria,
            StatusNoticia status, String tags,
            LocalDateTime dataInicio, LocalDateTime dataFim,
            Pageable pageable) {
        return repository
                .buscarComFiltros(titulo, autor, categoria, status, tags, dataInicio, dataFim, pageable)
                .map(NoticiaResponseDTO::from);
    }


    private Noticia findOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoticiaNotFoundException(id));
    }
}

