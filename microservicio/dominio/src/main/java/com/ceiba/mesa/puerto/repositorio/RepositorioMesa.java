package com.ceiba.mesa.puerto.repositorio;


import com.ceiba.mesa.modelo.entidad.Mesa;

public interface RepositorioMesa {
    /**
     * Permite crear un usuario
     * @param mesa
     * @return el id generado
     */
    Long crear(Mesa mesa);

    /**
     * Permite actualizar un usuario
     * @param mesa
     */
    void actualizar(Mesa mesa);

    /**
     * Permite eliminar un usuario
     * @param id
     */
    void eliminar(Long id);


}
