package com.stefanini.repository;

import com.stefanini.dao.GenericDAO;
import com.stefanini.entity.Jogador;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
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

    public TypedQuery<Jogador> loginDoJogador(String nickname, String password) {
        return createQuery("FROM Jogador WHERE nickname = :nickname AND " +
                "password = :password")
                .setParameter("nickname", nickname)
                .setParameter("password", password);
    }
}
