package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.controller;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.UsuarioRequestDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.UsuarioResponseDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(
        name = "Usuários",
        description = "Gerenciamento dos usuários do Projeto Cão-Guia Digital"
)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastro")
    @Operation(summary = "Cadastrar usuário")
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(
            @RequestBody UsuarioRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.cadastrar(dto));
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuário")
    public ResponseEntity<UsuarioResponseDTO> login(
            @RequestBody UsuarioRequestDTO dto) {

        return ResponseEntity.ok(usuarioService.autenticar(dto.email(), dto.senha()));
    }

    @GetMapping
    @Operation(
            summary = "Listar usuários",
            description = "Retorna a lista de todos os usuários cadastrados"
    )
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioRequestDTO dto) {

        return ResponseEntity.ok(usuarioService.atualizarDados(id, dto));
    }
}