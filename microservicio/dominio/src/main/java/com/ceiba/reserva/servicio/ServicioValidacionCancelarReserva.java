package com.ceiba.reserva.servicio;


import com.ceiba.excepcion.ReservaException;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.function.Predicate;

@NoArgsConstructor
public class ServicioValidacionCancelarReserva {

    private static final int HOURS_PREVIAS_CAMCELACION = 2;
    private static final String NO_ES_POSIBLE_CANCELAR_LA_RESERVA = "No es posible cancelar la reserva, recuerda que si no asisites a tu reserva, se te agregara a la lista negra y por lo tanto no podras hacer uso de este sistema";

    public void validar(DtoReserva reserva) {
        validarFechaHoraCancelacion(reserva.getFecha());
    }

    private void validarFechaHoraCancelacion(LocalDateTime fechaAValidar) {
        Predicate<LocalDateTime> isPosibleCancelarReserva = fecha -> {
            LocalDateTime fechaHoraMaximaParaCancelarReserva = fecha.minusHours(HOURS_PREVIAS_CAMCELACION);
            return fechaHoraMaximaParaCancelarReserva.isAfter(LocalDateTime.now());
        };

        if(isPosibleCancelarReserva.negate().test(fechaAValidar)) {
            throw new ReservaException(NO_ES_POSIBLE_CANCELAR_LA_RESERVA);
        }
    }
}
