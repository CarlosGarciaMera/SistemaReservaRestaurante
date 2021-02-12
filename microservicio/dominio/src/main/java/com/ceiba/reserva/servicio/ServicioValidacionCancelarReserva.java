package com.ceiba.reserva.servicio;


import com.ceiba.dominio.excepcion.ReservaException;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.function.Predicate;

@NoArgsConstructor
public class ServicioValidacionCancelarReserva {

    private static final int HOURS_PREVIAS_CAMCELACION = 2;
    private static final String NO_ES_POSIBLE_CANCELAR_LA_RESERVA = "No es posible cancelar la reserva, recuerda que si no asisites a tu reserva, se te agregara a la lista negra y por lo tanto no podras hacer uso de este sistema";

    private final Predicate<LocalDateTime> isPosibleCancelarPorFechaHora = fecha ->
         fecha.minusHours(HOURS_PREVIAS_CAMCELACION).isAfter(LocalDateTime.now());

    public void validar(DtoReserva reserva) {
        if(isPosibleCancelarPorFechaHora.negate().test(reserva.getFecha())) {
            throw new ReservaException(NO_ES_POSIBLE_CANCELAR_LA_RESERVA);
        }
    }
}
