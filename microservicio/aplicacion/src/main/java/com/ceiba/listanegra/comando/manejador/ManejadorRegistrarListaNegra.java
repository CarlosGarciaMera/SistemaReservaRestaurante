package com.ceiba.listanegra.comando.manejador;


import com.ceiba.listanegra.comando.ComandoListaNegra;
import com.ceiba.listanegra.comando.fabrica.FabricaListaNegra;
import com.ceiba.listanegra.modelo.entidad.ListaNegra;
import com.ceiba.listanegra.servicio.ServicioRegistrarListaNegra;
import com.ceiba.manejador.ManejadorComando;

import org.springframework.stereotype.Component;

@Component
public class ManejadorRegistrarListaNegra implements ManejadorComando<ComandoListaNegra> {

    private final FabricaListaNegra fabrica;
    private final ServicioRegistrarListaNegra servicio;

    public ManejadorRegistrarListaNegra(FabricaListaNegra fabricaListanegra, ServicioRegistrarListaNegra servicioRegistrarListaNegra) {
        this.fabrica = fabricaListanegra;
        this.servicio = servicioRegistrarListaNegra;
    }

    public void ejecutar(ComandoListaNegra comando) {
        ListaNegra listaNegra = this.fabrica.crear(comando);
        this.servicio.ejecutar(listaNegra);
    }
}
