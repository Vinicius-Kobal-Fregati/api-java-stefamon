package com.stefanini.repository;

import com.stefanini.dao.GenericDAO;
import com.stefanini.dto.JogadorCadastroDTO;
import com.stefanini.entity.Jogador;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class JogadorRepository extends GenericDAO<Jogador, Long> {
    public void salvar(Jogador jogador) {
        this.save(jogador);
    }

    public Jogador pegarPorId(Long id) {
        return findById(id);
    }

    public void alterar(Jogador jogador) {
        this.update(jogador);
    }

    public void deletar(Long id) {
        delete(id);
    }

    public List<Jogador> listarTodos() {
        return listAll();
    }
}
