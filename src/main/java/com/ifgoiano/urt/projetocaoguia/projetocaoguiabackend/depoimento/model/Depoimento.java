package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.depoimento.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "depoimentos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Depoimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 3000)
    private String descricao;
}