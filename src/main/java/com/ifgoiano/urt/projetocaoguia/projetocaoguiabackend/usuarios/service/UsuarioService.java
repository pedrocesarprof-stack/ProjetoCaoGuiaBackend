package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.service;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.core.security.AuthenticationFacade;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.core.security.JwtUtil;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.TipoEntidade;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model.TipoEventoEstatistica;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.service.EstatisticaService;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.*;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EstatisticaService estatisticaService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationFacade authenticationFacade;

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
                .telefone(dto.telefone() != null ? dto.telefone().trim() : null)
                .senha(passwordEncoder.encode(dto.senha().trim()))
                .perfil(PerfilUsuario.USER)
                .build();

        Usuario salvo = usuarioRepository.save(usuario);
        estatisticaService.registrarEventoInterno(salvo.getId(), TipoEntidade.USUARIO, TipoEventoEstatistica.CRIACAO);
        return new UsuarioResponseDTO(salvo);
    }

    public UsuarioResponseDTO cadastrarAdmin(AdminRequestDTO dto) {
        // Validar que apenas admins autenticados podem criar novos admins
        Usuario usuarioAutenticado = authenticationFacade.getAuthenticatedUser();
        if (usuarioAutenticado.getPerfil() != PerfilUsuario.ADMIN) {
            throw new RuntimeException("Apenas administradores podem criar novos administradores!");
        }

        // Validações dos campos
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
                .telefone(dto.telefone() != null ? dto.telefone().trim() : null)
                .senha(passwordEncoder.encode(dto.senha().trim()))
                .perfil(PerfilUsuario.ADMIN)
                .build();

        Usuario salvo = usuarioRepository.save(usuario);
        estatisticaService.registrarEventoInterno(salvo.getId(), TipoEntidade.USUARIO, TipoEventoEstatistica.CRIACAO);
        return new UsuarioResponseDTO(salvo);
    }

    public LoginResponseDTO autenticar(String email, String senha) {
        if (email == null || email.isBlank() || senha == null || senha.isBlank()) {
            throw new RuntimeException("E-mail e senha são obrigatórios para o login!");
        }

        String emailTratado = email.trim().toLowerCase();

        Usuario usuario = usuarioRepository.findByEmail(emailTratado)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (!passwordEncoder.matches(senha.trim(), usuario.getSenha())) {
            throw new RuntimeException("Senha incorreta!");
        }

        String token = jwtUtil.gerarToken(usuario.getEmail(), usuario.getPerfil().name());
        estatisticaService.registrarEventoInterno(usuario.getId(), TipoEntidade.USUARIO, TipoEventoEstatistica.VISUALIZACAO);

        return new LoginResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil().name(),
                token
        );
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

        if (dto.telefone() != null) {
            usuario.setTelefone(dto.telefone().trim());
        }

        if (dto.senha() != null && !dto.senha().isBlank()) {
            usuario.setSenha(passwordEncoder.encode(dto.senha().trim()));
        }

        Usuario updated = usuarioRepository.save(usuario);
        estatisticaService.registrarEventoInterno(id, TipoEntidade.USUARIO, TipoEventoEstatistica.ATUALIZACAO);
        return new UsuarioResponseDTO(updated);
    }
}