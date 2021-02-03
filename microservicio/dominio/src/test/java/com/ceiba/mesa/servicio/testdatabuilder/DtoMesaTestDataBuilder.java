package com.ceiba.mesa.servicio.testdatabuilder;


import com.ceiba.mesa.modelo.dto.DtoMesa;

public class DtoMesaTestDataBuilder {

    private Long id;
    private String nombre;
    private int cantidadMaximaComensales;

    public DtoMesaTestDataBuilder() {
        id = 1L;
        nombre = "Nombre Mesa";
        cantidadMaximaComensales = 12;
    }

    public DtoMesaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public DtoMesaTestDataBuilder conCantidadMaximaComensales(int cantidadMaximaComensales) {
        this.cantidadMaximaComensales = cantidadMaximaComensales;
        return this;
    }

    public DtoMesaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public DtoMesa build() {
        return new DtoMesa(id,nombre, cantidadMaximaComensales);
    }
}
