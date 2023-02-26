package com.stefanini.services;

import com.stefanini.dto.CompraStefamonDTO;
import com.stefanini.dto.JogadorCadastroDTO;
import com.stefanini.dto.JogadorVisualizacaoDTO;
import com.stefanini.dto.StefamonDTO;
import com.stefanini.entities.Jogador;
import com.stefanini.entities.Stefamon;
import com.stefanini.exceptions.RegraDeNegocioException;
import com.stefanini.parsers.JogadorParser;
import com.stefanini.parsers.StefamonParser;
import com.stefanini.repositories.JogadorRepository;
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
    @Inject
    StefamonService serviceStefamon;

    @Transactional
    public void salvar(JogadorCadastroDTO jogador) {
        Jogador jogadorCriado = JogadorParser
                .cadastroDTOParaEntidade(jogador);
        jogador.setPassword(EncriptadorSenhaUtil
                .encripta(jogador.getPassword()));
        repository.salvar(jogadorCriado);
    }

    public JogadorVisualizacaoDTO pegarPorId(Long id) {
        var jogador = repository.pegarPorId(id);
        if(Objects.isNull(jogador)) {
            throw new RegraDeNegocioException("Ocorreu um erro ao buscar o Jogador de id " + id, Response.Status.NOT_FOUND);
        }
        return JogadorParser.entidadeParaVisualizacaoDTO(jogador);
    }

    public Jogador pegarJogadorEntityPorId(Long id) {
        Jogador jogador = repository.pegarPorId(id);

        if(Objects.isNull(jogador)) {
            throw new RegraDeNegocioException("Ocorreu um erro ao buscar o Jogador de id " + id, Response.Status.NOT_FOUND);
        }
        return jogador;
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

    public void compraStefamon(CompraStefamonDTO compra) {
        Jogador jogador = pegarJogadorEntityPorId(compra.getIdJogador());
        List<Stefamon> stefamonsDoJogador = jogador.getStefamons();
        StefamonDTO stefamon = serviceStefamon.pegarPorId(compra.getIdStefamon());

        if (jogador.getSaldo().compareTo(stefamon.getPreco()) >= 0
                && stefamonsDoJogador.size() < 6) {
            Stefamon stefamonEntity = StefamonParser.dtoToEntity(stefamon);
            stefamonsDoJogador.add(stefamonEntity);
            jogador.setSaldo(jogador.getSaldo().subtract(stefamon.getPreco()));
            repository.alterar(jogador);
        } else {
            throw new RegraDeNegocioException("Jogador não tem saldo suficiente ou " +
                    "possuí mais de 6 stefamons",
                    Response.Status.BAD_REQUEST);
        }
    }
}
