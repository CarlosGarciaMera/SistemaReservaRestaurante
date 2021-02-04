package com.ceiba.mesa.servicio;

import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;
import org.junit.Test;
import org.mockito.Mockito;

public class ServicioEliminarMesaTest {

    @Test
    public void eliminarOKTest() {
        // arrange
        Long idMesaEliminar = 12L;
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        ServicioEliminarMesa servicio = new ServicioEliminarMesa(repositorioMesa);

        // act
        servicio.ejecutar(idMesaEliminar);
        // assert
        Mockito.verify(repositorioMesa).eliminar(idMesaEliminar);

    }
}
