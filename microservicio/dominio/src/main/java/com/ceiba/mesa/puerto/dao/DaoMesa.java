package com.ceiba.mesa.puerto.dao;


import com.ceiba.mesa.modelo.dto.DtoMesa;

import java.util.List;
import java.util.Optional;

public interface DaoMesa {

    /**
     * Permite listar usuarios
     * @return los usuarios
     */
    List<DtoMesa> listar();
    Optional<DtoMesa> getById(Long id);
}
