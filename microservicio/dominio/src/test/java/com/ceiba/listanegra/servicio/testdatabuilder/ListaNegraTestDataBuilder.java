package com.ceiba.listanegra.servicio.testdatabuilder;


import com.ceiba.listanegra.modelo.entidad.ListaNegra;

public class ListaNegraTestDataBuilder {

    private Long idCliente;
    private String nombreCliente;

    public ListaNegraTestDataBuilder() {
        idCliente = 1L;
        nombreCliente = "Nombre Cliente";
    }

    public ListaNegraTestDataBuilder conNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        return this;
    }

    public ListaNegraTestDataBuilder conIdCliente(Long idCliente) {
        this.idCliente = idCliente;
        return this;
    }

    public ListaNegra build() {
        return new ListaNegra(idCliente,nombreCliente);
    }
}
