package com.stefanini.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "tb_jogador")
public class Jogador {

    @Id
    @Column(name = "id_jogador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "Nickname não pode ser nulo")
    @NotEmpty(message = "Nickname não pode ser vazio")
    private String nickname;

    @Column
    @NotNull(message = "Senha não pode ser nulo")
    @NotEmpty(message = "Senha não pode ser vazio")
    @Size(min = 4, max = 10, message = "O tamanho da senha deve ser entre 4 e 10 caracteres")
    private String password;

    @Column
    @NotNull(message = "Saldo não pode ser nulo")
    @NotEmpty(message = "Saldo não pode ser vazio")
    private BigDecimal saldo = BigDecimal.valueOf(5000.00);


    @ManyToMany
    @JoinTable(name = "Jogador_Stefamon",
            joinColumns = {@JoinColumn(name = "IdJogador")},
            inverseJoinColumns = {@JoinColumn(name = "IdStefamon")})
    private List<Stefamon> stefamons = new ArrayList<>();

    public Jogador() {
    }
}
