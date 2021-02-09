package com.ceiba.listanegra.puerto.dao;

import com.ceiba.listanegra.modelo.dto.DtoListaNegra;

import java.util.List;

public interface DaoListaNegra {

	/**
     * Permite saber si dado in id de cliente, este se encuentra VETADO.
     * @return los usuarios vetados
     */
    boolean isVetado(Long id);

    /**
     * Lista los usuario vetados.
     * @return los usuarios vetados
     */
    List<DtoListaNegra> listar();
}
