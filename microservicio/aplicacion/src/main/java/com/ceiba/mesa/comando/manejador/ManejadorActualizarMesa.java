package com.ceiba.mesa.comando.manejador;


import com.ceiba.manejador.ManejadorComando;
import com.ceiba.mesa.comando.ComandoMesa;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.servicio.ServicioActualizarMesa;
import com.ceiba.mesa.comando.fabrica.FabricaMesa;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarMesa implements ManejadorComando<ComandoMesa> {

    private final FabricaMesa fabricaMesa;
    private final ServicioActualizarMesa servicioActualizarMesa;

    public ManejadorActualizarMesa(FabricaMesa fabricaMesa, ServicioActualizarMesa servicioActualizarMesa) {
        this.fabricaMesa = fabricaMesa;
        this.servicioActualizarMesa = servicioActualizarMesa;
    }

    public void ejecutar(ComandoMesa comandoUsuario) {
        Mesa mesa = this.fabricaMesa.crear(comandoUsuario);
        this.servicioActualizarMesa.ejecutar(mesa);
    }
}
