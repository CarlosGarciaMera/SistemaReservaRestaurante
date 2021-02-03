package com.ceiba.reserva.servicio.testdatabuilder;


import com.ceiba.reserva.modelo.dto.DtoReserva;

import java.time.LocalDateTime;

public class DtoReservaTestDataBuilder {

    private Long id;
	private Long idCliente;
    private String nombreCliente;
    private int cantidadComensales;
	private Long idMesa;
	private LocalDateTime fecha;

    public DtoReservaTestDataBuilder() {
		id = 1L;
        idCliente = 5L;
        nombreCliente = "Nombre Cliente";
		cantidadComensales = 10;
		fecha = LocalDateTime.parse("2020-03-31T19:25");
		idMesa = 123L;
    }

	public DtoReservaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public DtoReservaTestDataBuilder conNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        return this;
    }

    public DtoReservaTestDataBuilder conIdCliente(Long idCliente) {
        this.idCliente = idCliente;
        return this;
    }
	
	public DtoReservaTestDataBuilder conCantidadComensales(int cantidadComensales) {
        this.cantidadComensales = cantidadComensales;
        return this;
    }
	
	public DtoReservaTestDataBuilder conFecha(LocalDateTime fecha) {
        this.fecha = fecha;
        return this;
    }
	
	public DtoReservaTestDataBuilder conIdMesa(Long idMesa) {
        this.idMesa = idMesa;
        return this;
    }

    public DtoReserva build() {
        return new DtoReserva(id, idCliente, nombreCliente, cantidadComensales, idMesa, fecha);
    }
}
