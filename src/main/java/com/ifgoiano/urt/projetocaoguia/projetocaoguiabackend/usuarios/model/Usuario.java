package com.ifgoiano.urt.projetocaoguia.projetocaoguiabackend.usuarios.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "com/ifgoiano/urt/projetocaoguia/projetocaoguiabackend/usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;
}