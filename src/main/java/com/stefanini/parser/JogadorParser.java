package com.stefanini.parser;

import com.stefanini.dto.JogadorCadastroDTO;
import com.stefanini.dto.JogadorVisualizacaoDTO;
import com.stefanini.entity.Jogador;

public abstract class JogadorParser {

    public static JogadorVisualizacaoDTO entidadeParaVisualizacaoDTO(Jogador jogador) {
        return new JogadorVisualizacaoDTO(jogador);
    }

    public static Jogador cadastroDTOParaEntidade(JogadorCadastroDTO jogador) {
        return new Jogador(jogador);
    }
}
