package com.ceiba.mesa.servicio;


import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.mesa.modelo.dto.DtoMesa;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.puerto.dao.DaoMesa;
import com.ceiba.mesa.servicio.testdatabuilder.MesaTestDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ServicioValidacionesMesaTest {

    private static final Long ID_MESA_PERSISTIDA = 123L;
    private static final String NOMBRE_MESA_PERSISTIDA = "NombrePersistida";
    private static final int NUMERO_MAXIMO_COMENSALES_MESA_PERSISTIDA = 5;
    private ServicioValidacionMesa servicio;
    private DaoMesa daoMesa ;

    @Before
    public void SetUp(){
        daoMesa = Mockito.mock(DaoMesa.class);
        List<DtoMesa> listaMesas = new ArrayList<>();
        DtoMesa dto = new DtoMesa(ID_MESA_PERSISTIDA, NOMBRE_MESA_PERSISTIDA, NUMERO_MAXIMO_COMENSALES_MESA_PERSISTIDA);
        listaMesas.add(dto);
        Mockito.when(daoMesa.listar()).thenReturn(listaMesas);
        servicio = new ServicioValidacionMesa(daoMesa);
    }

    @Test
    public void noPasaValidacionNombreUnico_NombresIguales_IdsDiferentesTest() {
        // arrange
        long idMesaAValidar = 465L;
        Mesa mesaAValidar = new MesaTestDataBuilder().conId(idMesaAValidar).conNombre(NOMBRE_MESA_PERSISTIDA).build();
        // act - assert
        BasePrueba.assertThrows(() -> servicio.validar(mesaAValidar), ExcepcionDuplicidad.class,"Ya existe una mesa con el mismo nombre");
    }

    @Test
    public void pasaValidacionNombreUnico_NombresIguales_IdsIgualesTest() {
        // arrange
        Mesa mesaAValidar = new MesaTestDataBuilder().conId(ID_MESA_PERSISTIDA).conNombre(NOMBRE_MESA_PERSISTIDA).build();
        // act - assert
        servicio.validar(mesaAValidar);
    }

}
