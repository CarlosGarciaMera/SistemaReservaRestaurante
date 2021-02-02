package com.ceiba.mesa.servicio.testdatabuilder;


import com.ceiba.mesa.modelo.entidad.Mesa;

public class MesaTestDataBuilder {

    private Long id;
    private String nombre;
    private int cantidadMaximaComensales;

    public MesaTestDataBuilder() {
        id = 1L;
        nombre = "Nombre Mesa";
        cantidadMaximaComensales = 12;
    }

    public MesaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public MesaTestDataBuilder conCantidadMaximaComensales(int cantidadMaximaComensales) {
        this.cantidadMaximaComensales = cantidadMaximaComensales;
        return this;
    }

    public MesaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public Mesa build() {
        return new Mesa(id,nombre, cantidadMaximaComensales);
    }
}
