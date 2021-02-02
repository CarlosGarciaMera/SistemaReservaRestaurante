package com.ceiba.listanegra.servicio;


import com.ceiba.listanegra.modelo.entidad.ListaNegra;
import com.ceiba.listanegra.puerto.repositorio.RepositorioListaNegra;

public class ServicioRegistrarListaNegra {

    private final RepositorioListaNegra repositorioListaNegra;

    public ServicioRegistrarListaNegra(RepositorioListaNegra repositorioListaNegra) {
        this.repositorioListaNegra = repositorioListaNegra;
    }

    public void ejecutar(ListaNegra listaNegra) {
        this.repositorioListaNegra.registrar(listaNegra);
    }

}
