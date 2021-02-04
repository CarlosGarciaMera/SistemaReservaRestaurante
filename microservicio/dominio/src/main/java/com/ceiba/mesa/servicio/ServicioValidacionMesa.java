package com.ceiba.mesa.servicio;


import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.mesa.modelo.dto.DtoMesa;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.puerto.dao.DaoMesa;

import java.util.function.Function;
import java.util.function.Predicate;

public class ServicioValidacionMesa {

    private static final String YA_EXISTE_MESA_MISMO_NOMBRE = "Ya existe una mesa con el mismo nombre";

    private final DaoMesa daoMesa;

    public ServicioValidacionMesa(DaoMesa daoMesa) {
        this.daoMesa = daoMesa;
    }

    private final Function<Mesa, Predicate<DtoMesa>> esMesaDiferenteIgualNombre = mesa ->
            dtoMesa -> dtoMesa.getNombre().equals(mesa.getNombre()) && !dtoMesa.getId().equals(mesa.getId());

    public void validar(Mesa mesa) {
        boolean existeMesaIgualNombre = daoMesa.listar().stream().anyMatch(esMesaDiferenteIgualNombre.apply(mesa));
        if(existeMesaIgualNombre) {
            throw new ExcepcionDuplicidad(YA_EXISTE_MESA_MISMO_NOMBRE);
        }
    }


}
