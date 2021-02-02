package com.ceiba.mesa.servicio;

import static org.mockito.BDDMockito.*;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;
import com.ceiba.mesa.servicio.testdatabuilder.MesaTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;

public class ServicioCrearMesaTest {

    @Test
    public void noPermiteCreacionPorNombreRepetidoTest() {
        // arrange
        Mesa mesa = new MesaTestDataBuilder().build();
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        ServicioValidacionMesa servicioValidaciones = Mockito.mock(ServicioValidacionMesa.class);
        Mockito.when(repositorioMesa.crear(Mockito.any(Mesa.class))).thenReturn(mesa.getId());
        Mockito.doThrow(new ExcepcionDuplicidad("Excepcion Lanzada Por Nombre Repetido desde el Validador")).when(servicioValidaciones).validar(Mockito.any(Mesa.class));
        ServicioCrearMesa servicio = new ServicioCrearMesa(repositorioMesa, servicioValidaciones);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.ejecutar(mesa), ExcepcionDuplicidad.class,"Excepcion Lanzada Por Nombre Repetido desde el Validador");
    }

    @Test
    public void permiteCreacionTest() {
        // arrange
        Mesa mesa = new MesaTestDataBuilder().build();
        RepositorioMesa repositorioMesa = Mockito.mock(RepositorioMesa.class);
        ServicioValidacionMesa servicioValidaciones = Mockito.mock(ServicioValidacionMesa.class);
        ServicioCrearMesa servicio = new ServicioCrearMesa(repositorioMesa, servicioValidaciones);

        // act
        servicio.ejecutar(mesa);
        // assert
        Mockito.verify(repositorioMesa).crear(mesa);

    }
}
