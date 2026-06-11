package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.core.security;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.Usuario;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Facade para facilitar o acesso ao usuário autenticado no contexto de segurança
 */
@Component
@RequiredArgsConstructor
public class AuthenticationFacade {

    private final UsuarioRepository usuarioRepository;

    /**
     * Retorna o email do usuário autenticado
     */
    public String getAuthenticatedUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return null;
        }
        return authentication.getName();
    }

    /**
     * Retorna o usuário autenticado completo
     */
    public Usuario getAuthenticatedUser() {
        String email = getAuthenticatedUserEmail();
        if (email == null) {
            throw new UsernameNotFoundException("Nenhum usuário autenticado no contexto");
        }
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário autenticado não encontrado: " + email));
    }

    /**
     * Retorna o usuário autenticado ou null se não houver
     */
    public Usuario getAuthenticatedUserOrNull() {
        String email = getAuthenticatedUserEmail();
        if (email == null) {
            return null;
        }
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    /**
     * Verifica se há um usuário autenticado
     */
    public boolean isAuthenticated() {
        return getAuthenticatedUserEmail() != null;
    }
}

