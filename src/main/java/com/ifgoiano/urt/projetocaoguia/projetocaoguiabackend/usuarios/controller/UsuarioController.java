package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.controller;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.UsuarioRequestDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.UsuarioResponseDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO resposta = usuarioService.cadastrar(dto);
        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> login(@RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO resposta = usuarioService.autenticar(dto.email(), dto.senha());
        return ResponseEntity.ok(resposta);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        List<UsuarioResponseDTO> lista = usuarioService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO atualizado = usuarioService.atualizarDados(id, dto);
        return ResponseEntity.ok(atualizado);
    }
}