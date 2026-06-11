package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.core;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.noticias.model.CategoriaNoticia;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.noticias.model.Noticia;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.noticias.model.StatusNoticia;
import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.noticias.repository.NoticiaRepository;
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

    private final NoticiaRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        criarAdminPadrao();
        criarNoticiasPadrao();
    }

    private void criarAdminPadrao() {
        if (usuarioRepository.findByEmail("admin@caoguia.com").isPresent()) return;

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

    private void criarNoticiasPadrao() {
        if (repository.count() > 0) return;

        repository.save(Noticia.builder()
                .titulo("Cão-Guia: direitos garantidos por lei")
                .conteudo("A lei brasileira garante o acesso de cães-guia em todos os locais públicos e privados de uso coletivo.")
                .resumo("Saiba quais são os direitos de quem possui cão-guia no Brasil.")
                .autor("Equipe CãoGuia")
                .categoria(CategoriaNoticia.ACESSIBILIDADE)
                .status(StatusNoticia.PUBLICADO)
                .tags("cão-guia, lei, direitos")
                .build());

        repository.save(Noticia.builder()
                .titulo("Tecnologia assistiva para deficientes visuais")
                .conteudo("Novas tecnologias estão sendo desenvolvidas para auxiliar pessoas com deficiência visual.")
                .resumo("Conheça as últimas inovações em tecnologia assistiva.")
                .autor("João Silva")
                .categoria(CategoriaNoticia.TECNOLOGIA)
                .status(StatusNoticia.PUBLICADO)
                .tags("tecnologia, deficiência visual, inovação")
                .build());

        repository.save(Noticia.builder()
                .titulo("Programa de treinamento de cães-guia no Brasil")
                .conteudo("O Brasil possui centros especializados no treinamento de cães-guia com padrões internacionais.")
                .resumo("Como funciona o treinamento de cães-guia no Brasil.")
                .autor("Maria Oliveira")
                .categoria(CategoriaNoticia.EDUCACAO)
                .status(StatusNoticia.RASCUNHO)
                .tags("treinamento, cão-guia, educação")
                .build());
    }
}
