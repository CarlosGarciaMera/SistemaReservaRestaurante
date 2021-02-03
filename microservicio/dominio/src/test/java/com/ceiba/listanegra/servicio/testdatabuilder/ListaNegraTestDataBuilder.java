package com.ceiba.listanegra.servicio.testdatabuilder;


import com.ceiba.listanegra.modelo.entidad.ListaNegra;

public class ListaNegraTestDataBuilder {

    private Long id;
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

    public ListaNegraTestDataBuilder conId(Long idC) {
        this.id = id;
        return this;
    }

    public ListaNegra build() {
        return new ListaNegra(id, idCliente,nombreCliente);
    }
}
