package com.ceiba.mesa.servicio;


import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.puerto.dao.DaoMesa;

public class ServicioValidacionMesa {

    private static final String YA_EXISTE_MESA_MISMO_NOMBRE = "Ya existe una mesa con el mismo nombre";


    private final DaoMesa daoMesa;

    public ServicioValidacionMesa(DaoMesa daoMesa) {
        this.daoMesa = daoMesa;
    }

    public void validar(Mesa mesa) {
        validarExistenciaMesaIgualNombre(mesa);
    }

    private void validarExistenciaMesaIgualNombre(Mesa mesa) {
        boolean existeMesaIgualNombre = this.daoMesa.listar().stream().anyMatch(dto -> dto.getNombre().equals(mesa.getNombre())
                && !dto.getId().equals(mesa.getId()));
        if(existeMesaIgualNombre) {
            throw new ExcepcionDuplicidad(YA_EXISTE_MESA_MISMO_NOMBRE);
        }
    }
}
