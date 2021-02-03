package com.ceiba.reserva.comando.fabrica;

import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class FabricaReserva {

	@Autowired
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public Reserva crear(ComandoReserva comandoReserva) {
		return new Reserva(
                comandoReserva.getId(),
				comandoReserva.getIdCliente(),
                comandoReserva.getNombreCliente(),
                comandoReserva.getCantidadComensales(),
				LocalDateTime.parse(comandoReserva.getFecha(), formatter),
				comandoReserva.getIdMesa()
        );
    }
}
