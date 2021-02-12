package com.ceiba.reserva.servicio;


import com.ceiba.core.BasePrueba;
import com.ceiba.dominio.excepcion.ReservaException;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.servicio.testdatabuilder.DtoReservaTestDataBuilder;
import org.junit.Test;

import java.time.LocalDateTime;



public class ServicioValidacionCancelarReservaTest {

    @Test
    public void validarFechaCancelarLanzaExceptionPorCancelarConMenosDeDosHorasTest() {
        // arrange
        LocalDateTime fechaMenosDeDosHorasAntes = LocalDateTime.now().plusHours(1);
        DtoReserva reserva = new DtoReservaTestDataBuilder().conFecha(fechaMenosDeDosHorasAntes).build();

        ServicioValidacionCancelarReserva servicio = new ServicioValidacionCancelarReserva();
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(reserva), ReservaException.class,"No es posible cancelar la reserva, recuerda que si no asisites a tu reserva, se te agregara a la lista negra y por lo tanto no podras hacer uso de este sistema");
    }

    @Test
    public void validarFechaCancelarPermitePorFechaConMasDeDosHorasTest() {
        // arrange
        LocalDateTime fechaMasDeDosHorasAnticipacion = LocalDateTime.now().plusHours(3);
        DtoReserva reserva = new DtoReservaTestDataBuilder().conFecha(fechaMasDeDosHorasAnticipacion).build();
        ServicioValidacionCancelarReserva servicio = new ServicioValidacionCancelarReserva();
        servicio.validar(reserva);
    }
}
