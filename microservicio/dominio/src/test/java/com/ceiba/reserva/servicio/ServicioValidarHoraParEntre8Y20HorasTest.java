package com.ceiba.reserva.servicio;

import com.ceiba.core.BasePrueba;
import com.ceiba.excepcion.ReservaException;
import org.junit.Test;

import java.time.LocalDateTime;


public class ServicioValidarHoraParEntre8Y20HorasTest {

    private final LocalDateTime fechaPrueba = LocalDateTime.now().withMinute(0).withSecond(0);
    private final ServicioValidarHoraParEntre8y20Horas servicio = new ServicioValidarHoraParEntre8y20Horas();

    @Test
    public void validarHoraReservaImparTest() {
        // arrange
        LocalDateTime horaImpar = fechaPrueba.withHour(11);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(horaImpar), ReservaException.class,"Solo es posible reservar en horas pares comprendidas entre las 8 y las 20 horas");
    }

    @Test
    public void validarHora22FueraRango8a20Test() {
        // arrange
        LocalDateTime horaFueraDeRango8a20 = fechaPrueba.withHour(22);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(horaFueraDeRango8a20), ReservaException.class,"Solo es posible reservar en horas pares comprendidas entre las 8 y las 20 horas");
    }

    @Test
    public void validarHora7FueraRango8a20Test() {
        // arrange
        LocalDateTime horaFueraDeRango8a20 = fechaPrueba.withHour(6);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(horaFueraDeRango8a20), ReservaException.class,"Solo es posible reservar en horas pares comprendidas entre las 8 y las 20 horas");
    }

    @Test
    public void validarLanzaExeptionPorHoraNoPuntualEnMinutosTest() {
        // arrange
        LocalDateTime horaEnRangoPeroNoPuntualEnMinutos = fechaPrueba.withHour(10).withMinute(15);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(horaEnRangoPeroNoPuntualEnMinutos), ReservaException.class,"Solo es posible reservar en horas pares comprendidas entre las 8 y las 20 horas");
    }

    @Test
    public void validarLanzaExeptionPorHoraNoPuntualEnSegundosTest() {
        // arrange
        LocalDateTime horaEnRangoPeroNoPuntualEnSegundos = fechaPrueba.withHour(10).withSecond(15);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(horaEnRangoPeroNoPuntualEnSegundos), ReservaException.class,"Solo es posible reservar en horas pares comprendidas entre las 8 y las 20 horas");
    }

    @Test
    public void happyPathTest() {
        // arrange
        LocalDateTime horaValida = fechaPrueba.withHour(10);
        // act - assert
        servicio.validar(horaValida);
    }
}
