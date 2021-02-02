package com.ceiba.mesa.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoMesa {
    private Long id;
    private String nombre;
    private int cantidadMaximaComensales;

}
