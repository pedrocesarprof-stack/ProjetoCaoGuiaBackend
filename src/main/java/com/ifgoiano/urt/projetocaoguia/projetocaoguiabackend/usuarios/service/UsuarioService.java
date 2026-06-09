package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.service;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.Usuario;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.UsuarioRequestDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.UsuarioResponseDTO;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        if (dto.nome() == null || dto.nome().isBlank() ||
                dto.email() == null || dto.email().isBlank() ||
                dto.senha() == null || dto.senha().isBlank()) {
            throw new RuntimeException("Todos os campos (nome, email e senha) são obrigatórios!");
        }

        String emailTratado = dto.email().trim().toLowerCase();

        if (usuarioRepository.findByEmail(emailTratado).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado no sistema!");
        }

        Usuario usuario = Usuario.builder()
                .nome(dto.nome().trim())
                .email(emailTratado)
                .senha(dto.senha().trim())
                .build();

        Usuario salvo = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(salvo);
    }

    public UsuarioResponseDTO autenticar(String email, String senha) {
        if (email == null || email.isBlank() || senha == null || senha.isBlank()) {
            throw new RuntimeException("E-mail e senha são obrigatórios para o login!");
        }

        String emailTratado = email.trim().toLowerCase();

        Usuario usuario = usuarioRepository.findByEmail(emailTratado)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (!usuario.getSenha().equals(senha.trim())) {
            throw new RuntimeException("Senha incorreta!");
        }

        return new UsuarioResponseDTO(usuario);
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioResponseDTO::new)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO atualizarDados(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (dto.nome() != null && !dto.nome().isBlank()) {
            usuario.setNome(dto.nome().trim());
        }

        if (dto.email() != null && !dto.email().isBlank()) {
            String emailTratado = dto.email().trim().toLowerCase();

            var usuarioExistente = usuarioRepository.findByEmail(emailTratado);
            if (usuarioExistente.isPresent() && !usuarioExistente.get().getId().equals(id)) {
                throw new RuntimeException("Este e-mail já está sendo usado por outro usuário!");
            }
            usuario.setEmail(emailTratado);
        }

        if (dto.senha() != null && !dto.senha().isBlank()) {
            usuario.setSenha(dto.senha().trim());
        }

        Usuario updated = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(updated);
    }
}