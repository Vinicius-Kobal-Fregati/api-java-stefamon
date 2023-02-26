package com.stefanini.service;

import com.stefanini.dto.JogadorCadastroDTO;
import com.stefanini.dto.JogadorVisualizacaoDTO;
import com.stefanini.entity.Jogador;
import com.stefanini.exceptions.RegraDeNegocioException;
import com.stefanini.parser.JogadorParser;
import com.stefanini.repository.JogadorRepository;
import com.stefanini.utils.EncriptadorSenhaUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class JogadorService {

    @Inject
    JogadorRepository repository;

    @Transactional
    public void salvar(Jogador jogador) {
        jogador.setPassword(EncriptadorSenhaUtil
                .encripta(jogador.getPassword()));
        repository.salvar(jogador);
    }

    public JogadorVisualizacaoDTO pegarPorId(Long id) {
        var jogador = repository.pegarPorId(id);
        if(Objects.isNull(jogador)) {
            throw new RegraDeNegocioException("Ocorreu um erro ao buscar o Jogador de id " + id, Response.Status.NOT_FOUND);
        }
        return JogadorParser.entidadeParaVisualizacaoDTO(jogador);
    }

    @Transactional
    public void alterar(Jogador jogador) {
        jogador.setPassword(EncriptadorSenhaUtil
                .encripta(jogador.getPassword()));
        repository.alterar(jogador);
    }

    @Transactional
    public void deletar(Long id) {
        repository.deletar(id);
    }

    public List<JogadorVisualizacaoDTO> listarTodos() {
        return repository.listarTodos().stream()
                .map(JogadorParser::entidadeParaVisualizacaoDTO)
                .collect(Collectors.toList());
    }

    public void loginDoJogador(JogadorCadastroDTO jogador) {
        Jogador jogadorEncontrado = repository
                .loginDoJogador(jogador.getNickname(), jogador.getPassword())
                .getSingleResult();

        if(Objects.isNull(jogadorEncontrado)) {
            throw new RegraDeNegocioException("Nenhum jogador encontrado com esse usuário e senha",
                    Response.Status.NOT_FOUND);
        }
    }
}
