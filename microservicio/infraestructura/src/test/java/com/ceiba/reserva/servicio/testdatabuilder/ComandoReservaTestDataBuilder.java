package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.comando.ComandoReserva;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ComandoReservaTestDataBuilder {

    private Long id;
	private Long idCliente;
    private String nombreCliente;
    private int cantidadComensales;
	private Long idMesa;
	private LocalDateTime fecha;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ComandoReservaTestDataBuilder() {
		id = 1L;
        idCliente = 1L;
        nombreCliente = "Nombre Cliente";
		cantidadComensales = 1;
		LocalDateTime fechaAxiliar = LocalDateTime.now().withHour(10).withMinute(0).withSecond(0).plusDays(1);
		if (DayOfWeek.MONDAY == fechaAxiliar.getDayOfWeek() || DayOfWeek.WEDNESDAY == fechaAxiliar.getDayOfWeek()) {
            fecha = fechaAxiliar.plusDays(1);
        } else {
            fecha = fechaAxiliar ;
        }
    }

	public ComandoReservaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ComandoReservaTestDataBuilder conNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        return this;
    }

    public ComandoReservaTestDataBuilder conIdCliente(Long idCliente) {
        this.idCliente = idCliente;
        return this;
    }
	
	public ComandoReservaTestDataBuilder conCantidadComensales(int cantidadComensales) {
        this.cantidadComensales = cantidadComensales;
        return this;
    }
	
	public ComandoReservaTestDataBuilder conFecha(LocalDateTime fecha) {
        this.fecha = fecha;
        return this;
    }
	
	public ComandoReservaTestDataBuilder conIdMesa(Long idMesa) {
        this.idMesa = idMesa;
        return this;
    }

    public ComandoReserva build() {
        return new ComandoReserva(id, idCliente, nombreCliente, cantidadComensales, idMesa, fecha.format(formatter));
    }
}
