package com.ceiba.reserva.servicio;


import com.ceiba.excepcion.ReservaException;
import com.ceiba.core.BasePrueba;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.servicio.testdatabuilder.DtoReservaTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;


import java.time.LocalDateTime;



public class ServicioValidacionCancelarReservaTest {

    @Test
    public void validarFechaCancelarLanzaExceptionPorCancelarConMenosDeDosHorasTest() {
        // arrange
        LocalDateTime fechaMenosDeDosHorasAntes = LocalDateTime.now().minusHours(1);
        DtoReserva reserva = new DtoReservaTestDataBuilder().conFecha(fechaMenosDeDosHorasAntes).build();

        ServicioValidacionCancelarReserva servicio = new ServicioValidacionCancelarReserva();
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(reserva), ReservaException.class,"No es posible cancelar la reserva, recuerda que si no asisites a tu reserva, se te agregara a la lista negra y por lo tanto no podras hacer uso de este sistema");
    }

    @Test
    public void validarFechaCancelarLanzaExceptionPorCancelarConMasDeDosHosTest() {
        // arrange
        LocalDateTime fechaMenosDeDosHorasAntes = LocalDateTime.now().minusHours(3);
        DtoReserva reserva = new DtoReservaTestDataBuilder().conFecha(fechaMenosDeDosHorasAntes).build();

        ServicioValidacionCancelarReserva servicioMokc = Mockito.mock(ServicioValidacionCancelarReserva.class);
        servicioMokc.validar(reserva);
        Mockito.verify(servicioMokc,Mockito.times(1)).validar(reserva);
    }
}
