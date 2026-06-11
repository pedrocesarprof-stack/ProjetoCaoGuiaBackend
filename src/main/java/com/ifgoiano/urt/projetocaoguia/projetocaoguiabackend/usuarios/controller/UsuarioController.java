package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.controller;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.LoginResponseDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.UsuarioRequestDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.UsuarioResponseDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Operation(summary = "Autenticar usuário", description = "Retorna o token JWT para uso nas demais requisições")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody UsuarioRequestDTO dto) {

        return ResponseEntity.ok(usuarioService.autenticar(dto.email(), dto.senha()));
    }

    @GetMapping
    @Operation(
            summary = "Listar usuários",
            description = "Retorna a lista de todos os usuários cadastrados. Apenas ADMIN."
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário")
    @PreAuthorize("hasRole('ADMIN') or authentication.name == @usuarioRepository.findById(#id).orElseThrow().email")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioRequestDTO dto) {

        return ResponseEntity.ok(usuarioService.atualizarDados(id, dto));
    }
}