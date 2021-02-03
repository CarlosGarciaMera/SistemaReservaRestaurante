package com.ceiba.listanegra.modelo.entidad;


import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class ListaNegra {

    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DEL_CLIENTE = "Se debe ingresar El nombre del cliente";
    private static final String SE_DEBE_INGRESAR_EL_NUMERO_ID_CLIENTE = "Se debe ingresar el id del cliente";

    private Long id;
    private Long idCliente;
    private String nombreCliente;

    public ListaNegra(Long id, Long idCliente, String nombreCliente) {
        validarObligatorio(idCliente, SE_DEBE_INGRESAR_EL_NUMERO_ID_CLIENTE);
        validarObligatorio(nombreCliente, SE_DEBE_INGRESAR_EL_NOMBRE_DEL_CLIENTE);
        this.id = id;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
    }

}
