package com.ceiba.listanegra.servicio;

import com.ceiba.listanegra.modelo.entidad.ListaNegra;
import com.ceiba.listanegra.puerto.repositorio.RepositorioListaNegra;

import com.ceiba.listanegra.servicio.testdatabuilder.ListaNegraTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

public class ServicioEliminarListaNegraTest {
   
    @Test
    public void eliminarTest() {
        // arrange
        Long idCliente = 1L;
        RepositorioListaNegra repositorio = Mockito.mock(RepositorioListaNegra.class);
        ServicioEliminarListaNegra servicio = new ServicioEliminarListaNegra(repositorio);
        // act
        servicio.ejecutar(idCliente);
        // assert
        Mockito.verify(repositorio).eliminar(idCliente);
    }
}
