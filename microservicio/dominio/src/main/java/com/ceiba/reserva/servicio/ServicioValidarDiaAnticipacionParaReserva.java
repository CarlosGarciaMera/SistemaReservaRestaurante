package com.ceiba.reserva.servicio;

import com.ceiba.excepcion.ReservaException;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.function.Predicate;

@NoArgsConstructor
public class ServicioValidarDiaAnticipacionParaReserva {

    private static final String LAS_RESERVA_DEBE_SER_MINIMO_CON_UN_DIA_DE_ANTICIPACION = "No se aceptan reserva para el mismo dia, esta debe ser minimo el dia anterior";
    private static final int NUMERO_DIAS_ANTICIPACION_PARA_RESERVAR = 1;

    private final Predicate<LocalDate> noCumpleDiasAnticipo = fecha ->
            fecha.minusDays(NUMERO_DIAS_ANTICIPACION_PARA_RESERVAR).isBefore(LocalDate.now());

    public void validar(LocalDate fechaReserva) {
        if (noCumpleDiasAnticipo.test(fechaReserva)) {
            throw new ReservaException(LAS_RESERVA_DEBE_SER_MINIMO_CON_UN_DIA_DE_ANTICIPACION);
        }
    }
}
