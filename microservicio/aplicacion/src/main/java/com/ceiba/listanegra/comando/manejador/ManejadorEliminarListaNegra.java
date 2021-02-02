package com.ceiba.listanegra.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.listanegra.servicio.ServicioEliminarListaNegra;
import org.springframework.stereotype.Component;


@Component
public class ManejadorEliminarListaNegra implements ManejadorComando<Long> {

    private final ServicioEliminarListaNegra servicioEliminar;

    public ManejadorEliminarListaNegra(ServicioEliminarListaNegra servicioEliminar) {
        this.servicioEliminar = servicioEliminar;
    }

    public void ejecutar(Long idUsuario) {
        this.servicioEliminar.ejecutar(idUsuario);
    }
}
