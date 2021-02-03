package com.ceiba.reserva.servicio;

import com.ceiba.excepcion.ReservaException;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.function.Predicate;

@NoArgsConstructor
public class ServicioValidarFechaParaDiaLunesOMiercoles {

    private static final String NO_HAY_RESERVAS_PARA_LOS_DIAS_LUNES_Y_MIERCOLES = "No es posible reservar para unn dia Lunes o Miercoles";

    private final Predicate<LocalDateTime> esLunesOMiercoles= fecha ->
            DayOfWeek.MONDAY == fecha.getDayOfWeek() || DayOfWeek.WEDNESDAY == fecha.getDayOfWeek();

    public void validar(LocalDateTime fecha) {
        if (esLunesOMiercoles.test(fecha)) {
            throw new ReservaException(NO_HAY_RESERVAS_PARA_LOS_DIAS_LUNES_Y_MIERCOLES);
        }
    }
}
