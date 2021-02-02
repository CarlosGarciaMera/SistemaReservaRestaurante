package com.ceiba.listanegra.puerto.repositorio;


import com.ceiba.listanegra.modelo.entidad.ListaNegra;

public interface RepositorioListaNegra {
    /**
     * Permite registrar un cliente en lista negra
     * @param listaNegra
     */
    void registrar(ListaNegra listaNegra);

    /**
     * Permite eliminar un cliente de la lista negra
     * @param id
     */
    void eliminar(Long id);

}
