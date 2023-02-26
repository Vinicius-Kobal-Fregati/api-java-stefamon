package com.stefanini.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_stefamon")
public class Stefamon {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer vida;

    @Column(nullable = false)
    private Integer ataque;

    @Column(nullable = false)
    private Integer defesa;

    @Column(nullable = false)
    private Integer inteligencia;

    @Column(nullable = false)
    private Integer poder;

    @Column(nullable = false)
    private Integer velocidade;

    @Column(nullable = false)
    private String urlFoto;
}
