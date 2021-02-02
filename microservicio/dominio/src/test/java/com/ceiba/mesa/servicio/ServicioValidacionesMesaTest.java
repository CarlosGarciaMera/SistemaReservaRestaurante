package com.ceiba.mesa.servicio;


import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.mesa.modelo.dto.DtoMesa;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.puerto.dao.DaoMesa;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;
import com.ceiba.mesa.servicio.testdatabuilder.MesaTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ServicioValidacionesMesaTest {

    @Test
    public void noPasaValidacionNombreUnico_NombresIguales_IdsDiferentesTest() {
        // arrange
        final long idMesa1 = 2L;
        final String nombreMesa1 = "NombreMesa1";
        Mesa mesaAValidar = new MesaTestDataBuilder().conId(idMesa1).conNombre(nombreMesa1).build();
        DaoMesa daoMesa = Mockito.mock(DaoMesa.class);
        List<DtoMesa> listaMesas = new ArrayList<>();
        DtoMesa dto = new DtoMesa(11L,nombreMesa1, 8);
        listaMesas.add(dto);
        Mockito.when(daoMesa.listar()).thenReturn(listaMesas);
        ServicioValidacionMesa servicio = new ServicioValidacionMesa(daoMesa);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(mesaAValidar), ExcepcionDuplicidad.class,"Ya existe una mesa con el mismo nombre");
    }
}
