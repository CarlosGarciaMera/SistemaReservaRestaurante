package com.ceiba.mesa.modelo.entidad;


import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Mesa {

    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DE_MESA = "Se debe ingresar El nombre de la mesa";
    private static final String SE_DEBE_INGRESAR_EL_NUMERO_DE_COMENSALES = "Se debe ingresar el número de comensales";

    private Long id;
    private String nombre;
    private int cantidadMaximaComensales;

    public Mesa(Long id, String nombre, int cantidadMaximaComensales) {
        validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE_DE_MESA);
        validarObligatorio(cantidadMaximaComensales, SE_DEBE_INGRESAR_EL_NUMERO_DE_COMENSALES);
        this.id = id;
        this.nombre = nombre;
        this.cantidadMaximaComensales = cantidadMaximaComensales;
    }

}
