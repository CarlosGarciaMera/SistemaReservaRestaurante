package com.ceiba.reserva.servicio.testdatabuilder;


import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDateTime;

public class ReservaTestDataBuilder {

    private Long id;
	private Long idCliente;
    private String nombreCliente;
    private int cantidadComensales;
	private Long idMesa;
	private LocalDateTime fecha;

    public ReservaTestDataBuilder() {
		id = 1L;
        idCliente = 1L;
        nombreCliente = "Nombre Cliente";
		cantidadComensales = 10;
		fecha = LocalDateTime.parse("2020-03-31T19:25");
    }

	public ReservaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ReservaTestDataBuilder conNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        return this;
    }

    public ReservaTestDataBuilder conIdCliente(Long idCliente) {
        this.idCliente = idCliente;
        return this;
    }
	
	public ReservaTestDataBuilder conCantidadComensales(int cantidadComensales) {
        this.cantidadComensales = cantidadComensales;
        return this;
    }
	
	public ReservaTestDataBuilder conFecha(LocalDateTime fecha) {
        this.fecha = fecha;
        return this;
    }
	
	public ReservaTestDataBuilder conIdMesa(Long idMesa) {
        this.idMesa = idMesa;
        return this;
    }

    public Reserva build() {
        return new Reserva(id, idCliente, nombreCliente, cantidadComensales, fecha, idMesa);
    }
}
