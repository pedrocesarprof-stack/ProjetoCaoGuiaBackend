package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.formularios.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "formularios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Formulario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_envio", nullable = false)
    private LocalDateTime dataEnvio;

    @Column(columnDefinition = "TEXT")
    private String resposta;
}