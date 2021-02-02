package com.ceiba.mesa.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.mesa.servicio.ServicioEliminarMesa;
import org.springframework.stereotype.Component;


@Component
public class ManejadorEliminarMesa implements ManejadorComando<Long> {

    private final ServicioEliminarMesa servicioEliminarMesa;

    public ManejadorEliminarMesa(ServicioEliminarMesa servicioEliminarMesa) {
        this.servicioEliminarMesa = servicioEliminarMesa;
    }

    public void ejecutar(Long idCliente) {
        this.servicioEliminarMesa.ejecutar(idCliente);
    }
}
