package com.ceiba.listanegra.consulta;

import com.ceiba.listanegra.puerto.dao.DaoListaNegra;

import org.springframework.stereotype.Component;


@Component
public class ManejadorFindByIdListaNegra {

    private final DaoListaNegra dao;

    public ManejadorFindByIdListaNegra(DaoListaNegra dao){
        this.dao = dao;
    }

    public boolean ejecutar(Long id){ return this.dao.isVetado(id); }
}
