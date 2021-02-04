package com.ceiba.mesa.servicio.testdatabuilder;

import com.ceiba.mesa.comando.ComandoMesa;

public class ComandoMesaTestDataBuilder {

    private Long id;
    private String nombre;
    private int cantidadMaximaComensales;

    public ComandoMesaTestDataBuilder() {
        this.id = 1L;
        this.nombre = "Mesa Unica";
        this.cantidadMaximaComensales = 12;
    }

    public ComandoMesaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ComandoMesaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ComandoMesaTestDataBuilder conCantidadMaximaComensales(int  cantidadMaximaComensales) {
        this.cantidadMaximaComensales = cantidadMaximaComensales;
        return this;
    }

    public ComandoMesa build() {
        return new ComandoMesa(this.id, this.nombre, this.cantidadMaximaComensales);
    }
}
