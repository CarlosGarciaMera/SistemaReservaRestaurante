package com.ceiba.reserva.puerto.dao;


import com.ceiba.reserva.modelo.dto.DtoReserva;

import java.util.List;
import java.util.Optional;

public interface DaoReserva {

    /**
     * Permite listar usuarios
     * @return los usuarios
     */
    List<DtoReserva> listar();
    Optional<DtoReserva> getById(Long id);
}
