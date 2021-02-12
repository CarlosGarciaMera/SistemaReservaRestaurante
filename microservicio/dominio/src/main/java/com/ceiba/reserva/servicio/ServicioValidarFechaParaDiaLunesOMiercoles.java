package com.ceiba.reserva.servicio;


import com.ceiba.dominio.excepcion.ReservaException;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.function.Predicate;

@NoArgsConstructor
public class ServicioValidarFechaParaDiaLunesOMiercoles {

    private static final String NO_HAY_RESERVAS_PARA_LOS_DIAS_LUNES_Y_MIERCOLES = "No es posible reservar para unn dia Lunes o Miercoles";

    private final Predicate<LocalDateTime> esLunes = fecha -> DayOfWeek.MONDAY == fecha.getDayOfWeek();
    private final Predicate<LocalDateTime> esMiercoles = fecha -> DayOfWeek.WEDNESDAY == fecha.getDayOfWeek();

    public void validar(LocalDateTime fecha) {
        if (esLunes.or(esMiercoles).test(fecha)) {
            throw new ReservaException(NO_HAY_RESERVAS_PARA_LOS_DIAS_LUNES_Y_MIERCOLES);
        }
    }
}
