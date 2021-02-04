package com.ceiba.listanegra.consulta;

import com.ceiba.ComandoRespuesta;
import com.ceiba.listanegra.puerto.dao.DaoListaNegra;

import org.springframework.stereotype.Component;


@Component
public class ManejadorFindByIdListaNegra {

    private final DaoListaNegra dao;

    public ManejadorFindByIdListaNegra(DaoListaNegra dao){
        this.dao = dao;
    }

    public ComandoRespuesta<Boolean> ejecutar(Long id){ return new ComandoRespuesta<>(this.dao.isVetado(id)); }
}
