package com.ceiba.mesa.servicio;

import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;

import com.ceiba.mesa.servicio.testdatabuilder.MesaTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;

public class ServicioActualizarMesaTest {

    @Test
    public void noPermiteActualizacionPorNombreRepetidoTest() {
        // arrange
        Mesa mesa = new MesaTestDataBuilder().build();
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        ServicioValidacionMesa servicioValidaciones = Mockito.mock(ServicioValidacionMesa.class);
        Mockito.doThrow(new ExcepcionDuplicidad("Excepcion Lanzada Por Nombre Repetido desde el Validador")).when(servicioValidaciones).validar(Mockito.any(Mesa.class));
        ServicioActualizarMesa servicio = new ServicioActualizarMesa(repositorioMesa, servicioValidaciones);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.ejecutar(mesa), ExcepcionDuplicidad.class,"Excepcion Lanzada Por Nombre Repetido desde el Validador");
    }

    @Test
    public void permiteActualizacionTest() {
        // arrange
        Mesa mesa = new MesaTestDataBuilder().build();
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        ServicioValidacionMesa servicioValidaciones = Mockito.mock(ServicioValidacionMesa.class);
        ServicioActualizarMesa servicio = new ServicioActualizarMesa(repositorioMesa, servicioValidaciones);
        // act
        servicio.ejecutar(mesa);
        // assert
        Mockito.verify(repositorioMesa).actualizar(mesa);
    }
}
