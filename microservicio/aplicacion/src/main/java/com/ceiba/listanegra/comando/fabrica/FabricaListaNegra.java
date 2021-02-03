package com.ceiba.listanegra.comando.fabrica;

import com.ceiba.listanegra.comando.ComandoListaNegra;
import com.ceiba.listanegra.modelo.entidad.ListaNegra;
import org.springframework.stereotype.Component;



@Component
public class FabricaListaNegra {

    public ListaNegra crear(ComandoListaNegra comandoListaNegra) {
        return new ListaNegra(
                comandoListaNegra.getId(),
                comandoListaNegra.getIdCliente(),
                comandoListaNegra.getNombreCliente()
        );
    }
}
