package com.ceiba.listanegra.servicio;

import com.ceiba.listanegra.puerto.dao.DaoListaNegra;

public class ServicioValidarListaNegra {

    private final DaoListaNegra daoListaNegra;

    public ServicioValidarListaNegra(DaoListaNegra daoListaNegra) {
        this.daoListaNegra = daoListaNegra;
    }

    public boolean ejecutar(Long id) {
        return this.daoListaNegra.isVetado(id);
    }

}
