package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.controller;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.model.FormularioRequestDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.model.FormularioResponseDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.service.FormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formularios")
public class FormularioController {

    @Autowired
    private FormularioService formularioService;

    @PostMapping
    public ResponseEntity<FormularioResponseDTO> enviarFormulario(@RequestBody FormularioRequestDTO dto) {
        FormularioResponseDTO resposta = formularioService.salvarFormulario(dto);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping
    public ResponseEntity<List<FormularioResponseDTO>> listarFormularios() {
        List<FormularioResponseDTO> lista = formularioService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormularioResponseDTO> buscarFormulario(@PathVariable Long id) {
        FormularioResponseDTO dto = formularioService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormularioResponseDTO> atualizarFormulario(@PathVariable Long id, @RequestBody FormularioRequestDTO dto) {
        FormularioResponseDTO atualizado = formularioService.atualizarResposta(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFormulario(@PathVariable Long id) {
        formularioService.deletarFormulario(id);
        return ResponseEntity.noContent().build();
    }
}