package com.ceiba.reserva.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoReserva {
    private Long id;
	private Long idCliente;
    private String nombreCliente;
    private int cantidadMaximaComensales;
	private Long idMesa;
	private LocalDateTime fecha;
}
