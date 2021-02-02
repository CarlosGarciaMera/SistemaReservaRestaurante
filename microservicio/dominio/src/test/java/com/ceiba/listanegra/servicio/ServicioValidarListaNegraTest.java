package com.ceiba.listanegra.servicio;

import com.ceiba.listanegra.puerto.dao.DaoListaNegra;
import org.junit.Test;
import org.mockito.Mockito;



public class ServicioValidarListaNegraTest {

    @Test
    public void validarVetadoTest() {
        // arrange
        final long idCliente= 2L;
        DaoListaNegra dao = Mockito.mock(DaoListaNegra.class);
        ServicioValidarListaNegra servicio = new ServicioValidarListaNegra(dao);
        // act
        servicio.ejecutar(idCliente);
        // assert
        Mockito.verify(dao).isVetado(idCliente);
    }
}
