package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.core;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.PerfilUsuario;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.Usuario;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        criarAdminSomenteNoPrimeiroBootstrap();
    }

    private void criarAdminSomenteNoPrimeiroBootstrap() {
        if (usuarioRepository.count() > 0) return;

        usuarioRepository.save(Usuario.builder()
                .nome("Administrador")
                .email("admin@caoguia.com")
                .senha(passwordEncoder.encode("admin123"))
                .perfil(PerfilUsuario.ADMIN)
                .build());

        System.out.println("==============================================");
        System.out.println("  ADMIN criado: admin@caoguia.com / admin123");
        System.out.println("==============================================");
    }
}
