package com.ceiba.listanegra.servicio.testdatabuilder;

import com.ceiba.listanegra.comando.ComandoListaNegra;

public class ComandoListaNegraTestDataBuilder {

    private Long id;
    private Long idCliente;
    private String nombreCliente;

    public ComandoListaNegraTestDataBuilder() {
        this.id=1L;
        this.idCliente = 2L;
        this.nombreCliente = "Segundo Cliente Lista Negra";
    }

    public ComandoListaNegraTestDataBuilder conNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        return this;
    }

    public ComandoListaNegraTestDataBuilder conIdCliente(Long idCliente) {
        this.idCliente = idCliente;
        return this;
    }

    public ComandoListaNegraTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ComandoListaNegra build() {
        return new ComandoListaNegra(this.id, this.idCliente, this.nombreCliente);
    }
}
