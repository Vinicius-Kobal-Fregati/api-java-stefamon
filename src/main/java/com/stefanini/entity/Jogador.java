package com.stefanini.entity;

import com.stefanini.dto.JogadorCadastroDTO;

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
    private BigDecimal saldo = BigDecimal.valueOf(5000.00);


    @ManyToMany
    @JoinTable(name = "Jogador_Stefamon",
            joinColumns = {@JoinColumn(name = "IdJogador")},
            inverseJoinColumns = {@JoinColumn(name = "IdStefamon")})
    private List<Stefamon> stefamons = new ArrayList<>();

    public Jogador() {
    }

    public Jogador(JogadorCadastroDTO jogador) {
        this.nickname = jogador.getNickname();
        this.password = jogador.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<Stefamon> getStefamons() {
        return stefamons;
    }

    public void setStefamons(List<Stefamon> stefamons) {
        this.stefamons = stefamons;
    }
}
