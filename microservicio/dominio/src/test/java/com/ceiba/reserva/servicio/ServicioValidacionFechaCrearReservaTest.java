package com.ceiba.reserva.servicio;

import com.ceiba.core.BasePrueba;
import com.ceiba.excepcion.ReservaException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;


public class ServicioValidacionFechaCrearReservaTest {

    private ServicioValidarFechaParaDiaLunesOMiercoles servicioValidarFechaParaDiaLunesOMiercoles;
    private ServicioValidarHoraParEntre8y20Horas servicioValidarHoraParEntre8y20Horas;
    private ServicioValidarDiaAnticipacionParaReserva servicioValidarDiaAnticipacionParaReserva;
    private LocalDateTime fechaPrueba = LocalDateTime.now();
    private ServicioValidacionFechaCrearReserva servicio;

    @Before
    public void Setup(){
        servicioValidarFechaParaDiaLunesOMiercoles = Mockito.mock(ServicioValidarFechaParaDiaLunesOMiercoles.class);
        servicioValidarHoraParEntre8y20Horas = Mockito.mock(ServicioValidarHoraParEntre8y20Horas.class);
        servicioValidarDiaAnticipacionParaReserva = Mockito.mock(ServicioValidarDiaAnticipacionParaReserva.class);
        servicio = new ServicioValidacionFechaCrearReserva(servicioValidarFechaParaDiaLunesOMiercoles,
                servicioValidarHoraParEntre8y20Horas, servicioValidarDiaAnticipacionParaReserva);
    }

    @Test
    public void validarServicioValidarFechaParaDiaLunesOMiercolesLanzaExceptionTest() {
        // arrange
        Mockito.doThrow(new ReservaException("cualquier Excepcion del servicio ServicioValidarFechaParaDiaLunesOMiercoles"))
                .when(servicioValidarFechaParaDiaLunesOMiercoles).validar(fechaPrueba);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(fechaPrueba), ReservaException.class,"cualquier Excepcion del servicio ServicioValidarFechaParaDiaLunesOMiercoles");
    }

    @Test
    public void validarServicioValidarHoraParEntre8y20HorasLanzaExceptionTest() {
        // arrange
        Mockito.doThrow(new ReservaException("cualquier Excepcion del servicio ServicioValidarHoraParEntre8y20Horas"))
                .when(servicioValidarHoraParEntre8y20Horas).validar(fechaPrueba);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(fechaPrueba), ReservaException.class,"cualquier Excepcion del servicio ServicioValidarHoraParEntre8y20Horas");
    }

    @Test
    public void validarServicioValidarDiaAnticipacionParaReservaLanzaExceptionTest() {
        //arrange
        Mockito.doThrow(new ReservaException("cualquier Excepcion del servicio servicioValidarDiaAnticipacionParaReserva"))
                .when(servicioValidarDiaAnticipacionParaReserva).validar(fechaPrueba.toLocalDate());
        //act - assert
        BasePrueba.assertThrows(() -> servicio.validar(fechaPrueba), ReservaException.class,"cualquier Excepcion del servicio servicioValidarDiaAnticipacionParaReserva");
    }

    @Test
    public void validarServicioOrquestadorTest() {
        //arrange
        servicio.validar(fechaPrueba);
        //act - assert
        Mockito.verify(servicioValidarDiaAnticipacionParaReserva).validar(fechaPrueba.toLocalDate());
        Mockito.verify(servicioValidarHoraParEntre8y20Horas).validar(fechaPrueba);
        Mockito.verify(servicioValidarFechaParaDiaLunesOMiercoles).validar(fechaPrueba);
    }
}
