package com.stefanini.dto;

import com.stefanini.entity.Jogador;
import com.stefanini.entity.Stefamon;

import java.math.BigDecimal;
import java.util.List;

public class JogadorVisualizacaoDTO {
    private Long id;
    private String nickname;
    private BigDecimal saldo;
    List<Stefamon> stefamons;

    public JogadorVisualizacaoDTO(Jogador jogador) {
        this.id = jogador.getIdJogador();
        this.nickname = jogador.getNickname();
        this.saldo = jogador.getSaldo();
        this.stefamons = jogador.getStefamons();
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
