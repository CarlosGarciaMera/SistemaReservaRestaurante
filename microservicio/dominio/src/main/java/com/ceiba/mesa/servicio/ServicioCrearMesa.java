package com.ceiba.mesa.servicio;


import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;

public class ServicioCrearMesa {

    private static final String EL_IDENTIFICADOR_DE_MESA_YA_FUE_ASIGNADO = "El identificador demesa ya fue asignado";

    private final RepositorioMesa repositorioMesa;
    private final ServicioValidacionMesa servicioValidaciones;

    public ServicioCrearMesa(RepositorioMesa repositorioMesa, ServicioValidacionMesa servicioValidaciones) {
        this.repositorioMesa = repositorioMesa;
        this.servicioValidaciones = servicioValidaciones;
    }

    public Long ejecutar(Mesa mesa) {
        servicioValidaciones.validar(mesa);
        return this.repositorioMesa.crear(mesa);
    }
}
