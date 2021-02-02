package com.ceiba.listanegra.puerto.dao;

public interface DaoListaNegra {

	/**
     * Permite saber si dado in id de cliente, este se encuentra VETADO.
     * @return los usuarios vetados
     */
    boolean isVetado(Long id);
}
