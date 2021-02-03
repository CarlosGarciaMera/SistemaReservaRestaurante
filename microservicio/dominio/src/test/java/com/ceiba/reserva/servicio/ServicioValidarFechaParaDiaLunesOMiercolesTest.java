package com.ceiba.reserva.servicio;

import com.ceiba.core.BasePrueba;
import com.ceiba.excepcion.ReservaException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

public class ServicioValidarFechaParaDiaLunesOMiercolesTest {

    private ServicioValidarFechaParaDiaLunesOMiercoles servicio;

    @Before
    public void  setUp(){
        servicio = new ServicioValidarFechaParaDiaLunesOMiercoles();
    }

    @Test
    public void validarDiaReservaLunesTest() {
        // arrange  25/01/2021 es Lunes
        LocalDateTime fechaPruebaLunes = LocalDateTime.of(2021, Month.JANUARY, 25, 0, 0, 0);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(fechaPruebaLunes), ReservaException.class,"No es posible reservar para unn dia Lunes o Miercoles");
    }

    @Test
    public void validarDiaReservaMiercolesTest() {
        // arrange  27/01/2021 es Miercoles
        LocalDateTime fechaPruebaMiercoles = LocalDateTime.of(2021, Month.JANUARY, 27, 0, 0, 0);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(fechaPruebaMiercoles), ReservaException.class,"No es posible reservar para unn dia Lunes o Miercoles");
    }

    @Test
    public void validarDiaReservaMartesTest() {
        // arrange  27/01/2021 es Martes
        LocalDateTime fechaPruebaMiercoles = LocalDateTime.of(2021, Month.JANUARY, 26, 0, 0, 0);
        // act - assert
        servicio.validar(fechaPruebaMiercoles);
    }
}
