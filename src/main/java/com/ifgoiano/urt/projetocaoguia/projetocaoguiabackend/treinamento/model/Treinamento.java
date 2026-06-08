package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.treinamento.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "treinamentos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Treinamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, length = 2000)
    private String descricao;

    @Column(nullable = false, length = 100)
    private String etapa;
}