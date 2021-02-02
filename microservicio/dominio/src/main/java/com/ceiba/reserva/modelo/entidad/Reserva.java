package com.ceiba.reserva.modelo.entidad;


import lombok.Getter;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Reserva {

	private static final String SE_DEBE_INGRESAR_EL_ID_DEL_CLIENTE = "Se debe ingresar El id del Cliente";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DEL_CLIENTE = "Se debe ingresar El nombre del Cliente";
    private static final String SE_DEBE_INGRESAR_EL_NUMERO_DE_COMENSALES = "Se debe ingresar el número de comensales";
	private static final String SE_DEBE_INGRESAR_LA_FECHA = "Se debe ingresar la fecha de la reserva";
	private static final String SE_DEBE_INGRESAR_LA_MESA_RESERVADA = "Se debe ingresar la el id de la mesa reserva";

    private Long id;
	private Long idCliente;
    private String nombreCliente;
    private int cantidadComensales;
	private Long idMesa;
	private LocalDateTime fecha;

    public Reserva(Long id, Long idCliente,String nombreCliente, int cantidadComensales, LocalDateTime fecha, Long idMesa) {
		validarObligatorio(idCliente, SE_DEBE_INGRESAR_EL_NOMBRE_DEL_CLIENTE);
        validarObligatorio(nombreCliente, SE_DEBE_INGRESAR_EL_NOMBRE_DEL_CLIENTE);
        validarObligatorio(cantidadComensales, SE_DEBE_INGRESAR_EL_NUMERO_DE_COMENSALES);
		validarObligatorio(fecha, SE_DEBE_INGRESAR_LA_FECHA);
        this.id = id;
		this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.cantidadComensales = cantidadComensales;
		this.fecha = fecha;
        this.idMesa = idMesa;
    }

    public void setIdMesa(Long idMesa) {
        this.idMesa = idMesa;
    }
}
