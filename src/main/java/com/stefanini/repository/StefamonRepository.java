package com.stefanini.repository;


import com.stefanini.dao.GenericDAO;
import com.stefanini.entity.Stefamon;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class StefamonRepository extends GenericDAO<Stefamon, Long> {

    public List<Stefamon> listarTodos() {
        return listAll();
    }

    public Stefamon pegarPorId(Long id) {
        return findById(id);
    }
}
