package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.estatisticas.model;

import com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "estatistica_eventos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstatisticaEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long entidadeId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoEntidade tipoEntidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoEventoEstatistica tipoEvento;

    @Column(length = 80)
    private String origem;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
    }
}
