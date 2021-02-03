package com.ceiba.reserva.servicio;

import com.ceiba.excepcion.ReservaException;
import com.ceiba.core.BasePrueba;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;


public class ServicioValidacionFechaCrearReservaTest {

    // Se crea de esta forma, porque de lo contrario si las pruebas corren en horario que por negocio no se aceptan reservas (8 a 20 se aceptan y en horas pares) fallaría.
    private static final int HORA_POR_DEFECTO_PRUEBA_DEBIDO_A_LOGIC_DE_NEGOCIO = 10;
    private final ServicioValidacionFechaCrearReserva servicio = new ServicioValidacionFechaCrearReserva();
    private final LocalDateTime fechaPrueba = LocalDateTime.now().withHour(HORA_POR_DEFECTO_PRUEBA_DEBIDO_A_LOGIC_DE_NEGOCIO);

    @Test
    public void validarDiaReservaLunesTest() {
        // arrange  25/01/2021 es Lunes
        LocalDateTime fechaPruebaLunes = LocalDateTime.of(2021, Month.JANUARY, 25, 11, 55, 24);
        Reserva reserva = new ReservaTestDataBuilder().conFecha(fechaPruebaLunes).build();
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(reserva), ReservaException.class,"No es posible reservar para unn dia Lunes o Miercoles");
    }

    @Test
    public void validarDiaReservaMiercolesTest() {
        // arrange  27/01/2021 es Miercoles
        LocalDateTime fechaPruebaMiercoles = LocalDateTime.of(2021, Month.JANUARY, 27, 11, 55, 24);
        Reserva reserva = new ReservaTestDataBuilder().conFecha(fechaPruebaMiercoles).build();
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(reserva), ReservaException.class,"No es posible reservar para unn dia Lunes o Miercoles");
    }

    @Test
    public void validarHoraReservaImparParTest() {
        // arrange
        LocalDateTime horaImpar = fechaPrueba.withHour(11);
        Reserva reserva = new ReservaTestDataBuilder().conFecha(horaImpar).build();
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(reserva), ReservaException.class,"Solo es posible reservar en horas pares comprendidas entre las 8 y las 20 horas");
    }

    @Test
    public void validarHora22FueraRango8a20Test() {
        // arrange
        LocalDateTime horaFueraDeRango8a20 = fechaPrueba.withHour(22);
        Reserva reserva = new ReservaTestDataBuilder().conFecha(horaFueraDeRango8a20).build();
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(reserva), ReservaException.class,"Solo es posible reservar en horas pares comprendidas entre las 8 y las 20 horas");
    }

    @Test
    public void validarHora7FueraRango8a20Test() {
        // arrange
        LocalDateTime horaFueraDeRango8a20 = fechaPrueba.withHour(7);
        Reserva reserva = new ReservaTestDataBuilder().conFecha(horaFueraDeRango8a20).build();
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(reserva), ReservaException.class,"Solo es posible reservar en horas pares comprendidas entre las 8 y las 20 horas");
    }

    @Test
    public void validarReservaMismoDiaTest() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().conFecha(fechaPrueba).build();
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(reserva), ReservaException.class,"No se aceptan reserva para el mismo dia, esta debe ser minimo el dia anterior");
    }

    @Test
    public void validarReservaDiasPasadoTest() {
        // arrange
        LocalDateTime diaPasado = fechaPrueba.minusDays(5);
        Reserva reserva = new ReservaTestDataBuilder().conFecha(diaPasado).build();
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(reserva), ReservaException.class,"No se aceptan reserva para el mismo dia, esta debe ser minimo el dia anterior");
    }

    @Test
    public void validarReservaAnioPasadoTest() {
        // arrange
        LocalDateTime anioPasado = fechaPrueba.minusYears(1);
        Reserva reserva = new ReservaTestDataBuilder().conFecha(anioPasado).build();
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(reserva), ReservaException.class,"No se aceptan reserva para el mismo dia, esta debe ser minimo el dia anterior");
    }
}
