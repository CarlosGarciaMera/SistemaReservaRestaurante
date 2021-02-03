package com.ceiba.reserva.servicio;


import com.ceiba.excepcion.ReservaException;
import com.ceiba.listanegra.puerto.dao.DaoListaNegra;
import com.ceiba.mesa.modelo.dto.DtoMesa;
import com.ceiba.mesa.puerto.dao.DaoMesa;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServicioCrearReserva {

    private static final String CLIENTE_VETADO = "El Cliente se encuentra vetado, no es posible hacer reservas";
    private static final String CLIENTE_CUENTA_CON_RESERVA_EL_MISMO_DIA_MISMA_HORA = "El Cliente ya cuenta con una reserva para a el mismo dia y la misma hora";
    private static final String NO_HAY_MESAS_DISPONIBLES_PARA_LA_RESERVA = "No hay mesas disponibles para la reserva";

    private final RepositorioReserva repositorio;
    private final DaoReserva dao;
    private final DaoMesa daoMesa;
    private final DaoListaNegra daoListaNegra;
    private final ServicioValidacionFechaCrearReserva servicioValidacionesFechaCrear;

    public ServicioCrearReserva(RepositorioReserva repositorio, DaoReserva dao, DaoMesa daoMesa, DaoListaNegra daoListaNegra, ServicioValidacionFechaCrearReserva servicioValidacionesFechaCrear) {
        this.repositorio = repositorio;
        this.dao = dao;
        this.daoMesa = daoMesa;
        this.daoListaNegra = daoListaNegra;
        this.servicioValidacionesFechaCrear = servicioValidacionesFechaCrear;
    }

    public Long ejecutar(Reserva reserva) {
        validarClienteEnListaNegra(reserva);
        validarClienteDosReservasMismaHoraMismoDia(reserva);
        servicioValidacionesFechaCrear.validar(reserva.getFecha());
        validarAsignarMesaReserva(reserva);
        return this.repositorio.crear(reserva);
    }

    private void validarAsignarMesaReserva(Reserva reserva) {
        Optional<DtoMesa> mesaEncontrada = getMesaParaReserva (reserva);
        if (mesaEncontrada.isPresent()) {
            reserva.setIdMesa(mesaEncontrada.get().getId());
        } else {
            throw  new ReservaException(NO_HAY_MESAS_DISPONIBLES_PARA_LA_RESERVA);
        }
    }

    private Optional<DtoMesa> getMesaParaReserva(Reserva reservaInformacion) {
        Predicate<DtoMesa> filtrarPorCantidadComensales = mesa -> mesa.getCantidadMaximaComensales() > reservaInformacion.getCantidadComensales();
        List<DtoMesa> mesasDisponiblePorContidadComensales = daoMesa.listar().stream().filter(filtrarPorCantidadComensales).collect(Collectors.toList());
        List<DtoReserva> reservas = dao.listar();
        for (DtoMesa dtoMesa: mesasDisponiblePorContidadComensales) {
            boolean isMesaOcupada = reservas.stream().filter(reserva -> reserva.getIdMesa().equals(dtoMesa.getId()))
                    .anyMatch(getPredicateMismaFechaMismaHora(reservaInformacion.getFecha()));
            if (!isMesaOcupada) {
                return Optional.of(dtoMesa);
            }
        }
        return Optional.empty();
    }

    private void validarClienteDosReservasMismaHoraMismoDia(Reserva reservaAValidar) {
        List<DtoReserva> reservasMismoClienteMismoDiaMismaHora= dao.listar().stream()
                .filter(reserva -> reserva.getIdCliente().equals(reservaAValidar.getIdCliente()))
                .filter(getPredicateMismaFechaMismaHora(reservaAValidar.getFecha()))
                .collect(Collectors.toList());
        if(!reservasMismoClienteMismoDiaMismaHora.isEmpty()) {
            throw  new ReservaException(CLIENTE_CUENTA_CON_RESERVA_EL_MISMO_DIA_MISMA_HORA);
        }
    }

    private Predicate<DtoReserva> getPredicateMismaFechaMismaHora(LocalDateTime fechaAValidar) {
        return reserva -> {
            LocalDateTime fechaReserva = reserva.getFecha();
            return fechaReserva.getYear() == fechaAValidar.getYear()
                    && fechaReserva.getDayOfYear() == fechaAValidar.getDayOfYear()
                    && fechaReserva.getHour() == fechaAValidar.getHour();
        };
    }

    private void validarClienteEnListaNegra(Reserva reserva) {
        boolean esClienteVetado = daoListaNegra.isVetado(reserva.getIdCliente());
        if(esClienteVetado) {
            throw  new ReservaException(CLIENTE_VETADO);
        }
    }
}
