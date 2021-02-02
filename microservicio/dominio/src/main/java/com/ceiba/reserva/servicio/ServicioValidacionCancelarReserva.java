package com.ceiba.reserva.servicio;


import com.ceiba.Excepcion.ReservaException;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.function.Predicate;

@NoArgsConstructor
public class ServicioValidacionCancelarReserva {

    private static final String NO_ES_POSIBLE_CANCELAR_LA_RESERVA = "No es posible cancelar la reserva, recuerda que si no asisites a tu reserva, se te agregara a la lista negra y por lo tanto no podras hacer uso de este sistema";

    public void validar(DtoReserva reserva) {
        validarHoraFecha(reserva.getFecha());
    }

    private void validarHoraFecha(LocalDateTime fechaAValidar) {
        Predicate<LocalDateTime> isPosibleCancelarReserva = fecha -> {
            LocalDateTime fechaActualMenosDosHoras = LocalDateTime.now().minusHours(2);
            return fechaActualMenosDosHoras.isAfter(fechaAValidar);
        };

        if(isPosibleCancelarReserva.negate().test(fechaAValidar)) {
            throw new ReservaException(NO_ES_POSIBLE_CANCELAR_LA_RESERVA);
        }
    }
}
