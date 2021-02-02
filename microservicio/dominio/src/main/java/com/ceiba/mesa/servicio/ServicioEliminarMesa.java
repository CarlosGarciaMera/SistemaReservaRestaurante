package com.ceiba.mesa.servicio;


import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;

public class ServicioEliminarMesa {

    private final RepositorioMesa repositorioMesa;

    public ServicioEliminarMesa(RepositorioMesa repositorioMesa) {
        this.repositorioMesa = repositorioMesa;
    }

    public void ejecutar(Long id) {
        this.repositorioMesa.eliminar(id);
    }
}
