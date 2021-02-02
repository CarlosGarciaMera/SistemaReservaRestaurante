package com.ceiba.listanegra.servicio;

import com.ceiba.listanegra.modelo.entidad.ListaNegra;
import com.ceiba.listanegra.puerto.repositorio.RepositorioListaNegra;
import com.ceiba.listanegra.servicio.testdatabuilder.ListaNegraTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;


public class ServicioRegistrarListaNegraTest {

    @Test
    public void registrarEnListaNegra() {
        // arrange
        ListaNegra listaNegra = new ListaNegraTestDataBuilder().build();
        RepositorioListaNegra repositorio = Mockito.mock(RepositorioListaNegra.class);
        ServicioRegistrarListaNegra servicio = new ServicioRegistrarListaNegra(repositorio);
        // act
        servicio.ejecutar(listaNegra);
        // assert
        Mockito.verify(repositorio).registrar(listaNegra);
    }
}
