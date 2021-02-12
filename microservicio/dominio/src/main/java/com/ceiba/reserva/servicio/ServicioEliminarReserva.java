package com.ceiba.reserva.servicio;


import com.ceiba.dominio.excepcion.ReservaException;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.util.Optional;

public class ServicioEliminarReserva {

    private static final String NO_EXISTE_UNA_RESERVA_CON_ID = "No existe una reserva con ID: %s";

    private final RepositorioReserva repositorioReserva;

    private final DaoReserva daoReserva;

    private final ServicioValidacionCancelarReserva servicioValidacionCancelarReserva;

    public ServicioEliminarReserva(RepositorioReserva repositorioReserva, DaoReserva daoReserva, ServicioValidacionCancelarReserva servicioValidacionCancelarReserva) {
        this.repositorioReserva = repositorioReserva;
        this.daoReserva = daoReserva;
        this.servicioValidacionCancelarReserva = servicioValidacionCancelarReserva;
    }

    public void ejecutar(Long id) {
        Optional<DtoReserva> reserva = daoReserva.getById(id);
        if (reserva.isPresent()) {
            servicioValidacionCancelarReserva.validar(reserva.get());
            repositorioReserva.eliminar(id);
        } else {
            throw new ReservaException(String.format(NO_EXISTE_UNA_RESERVA_CON_ID, id));
        }
    }
}
