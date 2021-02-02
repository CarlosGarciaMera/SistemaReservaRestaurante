package com.ceiba.reserva.servicio;

import com.ceiba.Excepcion.ReservaException;
import com.ceiba.core.BasePrueba;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.Month;


public class ServicioValidacionFechaCrearReservaTest {

    @Test
    public void validarDiaReservaLunesTest() {
        // arrange
        LocalDateTime fechaPruebaLunes = LocalDateTime.of(2021, Month.JANUARY, 25, 11, 55, 24);
        Reserva reserva = new ReservaTestDataBuilder().conFecha(fechaPruebaLunes).build();
        ServicioValidacionFechaCrearReserva servicio = new ServicioValidacionFechaCrearReserva();
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(reserva), ReservaException.class,"No es posible reservar para unn dia Lunes o Miercoles");
    }

    @Test
    public void validarDiaReservaMiercolesTest() {
        // arrange
        LocalDateTime fechaPruebaMiercoles = LocalDateTime.of(2021, Month.JANUARY, 27, 11, 55, 24);
        Reserva reserva = new ReservaTestDataBuilder().conFecha(fechaPruebaMiercoles).build();
        ServicioValidacionFechaCrearReserva servicio = new ServicioValidacionFechaCrearReserva();
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(reserva), ReservaException.class,"No es posible reservar para unn dia Lunes o Miercoles");
    }

    @Test
    public void validarHoraParTest() {
        // arrange
        LocalDateTime horaImpar = LocalDateTime.of(2021, Month.JANUARY, 26, 11, 00, 00);
        Reserva reserva = new ReservaTestDataBuilder().conFecha(horaImpar).build();
        ServicioValidacionFechaCrearReserva servicio = new ServicioValidacionFechaCrearReserva();
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(reserva), ReservaException.class,"Solo es posible reservar en horas pares comprendidas entre las 8 y las 20 horas");
    }

    @Test
    public void validarHoraFueraRango8a20Test() {
        // arrange
        LocalDateTime fechaFueraDeRango8a20 = LocalDateTime.of(2021, Month.JANUARY, 26, 22, 00, 00);
        Reserva reserva = new ReservaTestDataBuilder().conFecha(fechaFueraDeRango8a20).build();
        ServicioValidacionFechaCrearReserva servicio = new ServicioValidacionFechaCrearReserva();
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(reserva), ReservaException.class,"Solo es posible reservar en horas pares comprendidas entre las 8 y las 20 horas");
    }
}
