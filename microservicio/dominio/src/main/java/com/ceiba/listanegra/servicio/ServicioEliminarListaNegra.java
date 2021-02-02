package com.ceiba.listanegra.servicio;


import com.ceiba.listanegra.puerto.repositorio.RepositorioListaNegra;

public class ServicioEliminarListaNegra {

    private final RepositorioListaNegra repositorioListaNegra;

    public ServicioEliminarListaNegra(RepositorioListaNegra repositorioListaNegra) {
        this.repositorioListaNegra = repositorioListaNegra;
    }

    public void ejecutar(Long id) {
        this.repositorioListaNegra.eliminar(id);
    }
}
