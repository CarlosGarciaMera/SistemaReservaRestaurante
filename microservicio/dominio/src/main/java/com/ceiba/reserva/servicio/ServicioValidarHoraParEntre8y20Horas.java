package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ReservaException;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.function.Predicate;

@NoArgsConstructor
public class ServicioValidarHoraParEntre8y20Horas {

    private static final String SOLO_ES_POSIBLE_RESERVAR_HORAS_PARES_ENTRE_LAS_8_Y_LAS_20_HORAS = "Solo es posible reservar en horas pares comprendidas entre las 8 y las 20 horas";
    public static final int HORA_MAXIMA_RESERVA = 20;
    public static final int HORA_MINIMA_RESERVA = 8;

    private final Predicate<LocalDateTime> esHoraParCeroMinutosCeroSegundos = hora ->
            hora.getMinute() == 0 && hora.getSecond() == 0 && hora.getHour() % 2 == 0 ;
    private final Predicate<LocalDateTime> esHoraFueraDeRango8y20 = hora ->
            hora.getHour() > HORA_MAXIMA_RESERVA || hora.getHour() < HORA_MINIMA_RESERVA;

    public void validar(LocalDateTime fecha) {
        if(esHoraParCeroMinutosCeroSegundos.negate().or(esHoraFueraDeRango8y20).test(fecha)) {
            throw new ReservaException(SOLO_ES_POSIBLE_RESERVAR_HORAS_PARES_ENTRE_LAS_8_Y_LAS_20_HORAS);
        }
    }
}
