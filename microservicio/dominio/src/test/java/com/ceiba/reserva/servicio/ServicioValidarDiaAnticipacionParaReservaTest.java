package com.ceiba.reserva.servicio;

import com.ceiba.core.BasePrueba;
import com.ceiba.dominio.excepcion.ReservaException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class ServicioValidarDiaAnticipacionParaReservaTest {

    private ServicioValidarDiaAnticipacionParaReserva servicio;

    @Before
    public void  setUp(){
        servicio = new ServicioValidarDiaAnticipacionParaReserva();
    }

    @Test
    public void validarDiasAnticipacionLanzaExcepcionPorSerMismoDia() {
        // arrange
        LocalDate fechaPruebaMismoDia = LocalDate.now();
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(fechaPruebaMismoDia), ReservaException.class,"No se aceptan reserva para el mismo dia, esta debe ser minimo el dia anterior");
    }

    @Test
    public void validarDiasAnticipacionLanzaExcepcionPorSerDiaPasado() {
        // arrange
        LocalDate fechaReservaDiaYaPasado = LocalDate.now().minusDays(1);;
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(fechaReservaDiaYaPasado), ReservaException.class,"No se aceptan reserva para el mismo dia, esta debe ser minimo el dia anterior");
    }

    @Test
    public void validarDiasAnticipacionPasaPorFechaReservaDiaSiguienteOsuperior() {
        // arrange
        LocalDate fechaReservaDiaFuturo = LocalDate.now().plusDays(2);;
        // act - assert
        servicio.validar(fechaReservaDiaFuturo);
    }
}
