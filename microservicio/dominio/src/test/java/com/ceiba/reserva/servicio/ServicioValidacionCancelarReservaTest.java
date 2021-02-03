package com.ceiba.reserva.servicio;


import com.ceiba.excepcion.ReservaException;
import com.ceiba.core.BasePrueba;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import org.junit.Test;


import java.time.LocalDateTime;



public class ServicioValidacionCancelarReservaTest {

    @Test
    public void validarFechaCancelarLanzaExceptionPorCancelarConMenosDeDosHorasTest() {
        // arrange
        LocalDateTime fechaActualReserva = LocalDateTime.now();
        LocalDateTime fechaMenosDeDosHorasAntes = fechaActualReserva.minusHours(1);
        DtoReserva reserva = new DtoReserva(12L,123L,"nombre cliente", 8, 321L, fechaActualReserva);

        ServicioValidacionCancelarReserva servicio = new ServicioValidacionCancelarReserva();
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(reserva), ReservaException.class,"No es posible cancelar la reserva, recuerda que si no asisites a tu reserva, se te agregara a la lista negra y por lo tanto no podras hacer uso de este sistema");
    }
}
