package com.ceiba.reserva.servicio;


import com.ceiba.Excepcion.ReservaException;
import com.ceiba.reserva.modelo.entidad.Reserva;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.function.Predicate;

@NoArgsConstructor
public class ServicioValidacionFechaCrearReserva {

    private static final String NO_HAY_RESERVAS_PARA_LOS_DIAS_LUNES_Y_MIERCOLES = "No es posible reservar para unn dia Lunes o Miercoles";

    private static final String SOLO_ES_POSIBLE_RESERVAR_HORAS_PARES_ENTRE_LAS_8_Y_LAS_20_HORAS = "Solo es posible reservar en horas pares comprendidas entre las 8 y las 20 horas";

    private static final String LAS_RESERVA_DEBE_SER_MINIMO_CON_UN_DIA_DE_ANTICIPACION = "La reserva debe ser con minimo un dia de anticipación";

    public void validar(Reserva reserva) {
        validarReservaParaDiaLunesOMiercoles(reserva.getFecha());
        validarHoraParEntre8y20Horas(reserva.getFecha());
        validarDiaAnticipacionParaReserva(reserva.getFecha());

    }

    private void validarDiaAnticipacionParaReserva(LocalDateTime fecha) {
        Predicate<LocalDateTime> esElMismoDiaOSuperiorAlActual = dia -> {
            LocalDateTime fechaActual = LocalDateTime.now();
            return fechaActual.isAfter(dia) && dia.getDayOfYear() < fechaActual.getDayOfYear();
        };
        if (esElMismoDiaOSuperiorAlActual.test(fecha)) {
            throw new ReservaException(LAS_RESERVA_DEBE_SER_MINIMO_CON_UN_DIA_DE_ANTICIPACION);
        }
    }

    private void validarReservaParaDiaLunesOMiercoles(LocalDateTime fecha) {
        Predicate<LocalDateTime> esLunesOMiercoles= dia -> DayOfWeek.MONDAY == dia.getDayOfWeek() || DayOfWeek.WEDNESDAY == dia.getDayOfWeek();
        if (esLunesOMiercoles.test(fecha)) {
            throw new ReservaException(NO_HAY_RESERVAS_PARA_LOS_DIAS_LUNES_Y_MIERCOLES);
        }
    }

    private void validarHoraParEntre8y20Horas(LocalDateTime fecha) {
        Predicate<LocalDateTime> esHoraPar= hora -> hora.getHour() % 2 > 0 ;
        Predicate<LocalDateTime> esHoraFueraDeRango8y20= hora -> hora.getHour() > 20 || hora.getHour() < 8 ;
        if(esHoraFueraDeRango8y20.or(esHoraPar).test(fecha)) {
            throw new ReservaException(SOLO_ES_POSIBLE_RESERVAR_HORAS_PARES_ENTRE_LAS_8_Y_LAS_20_HORAS);
        }
    }
}
