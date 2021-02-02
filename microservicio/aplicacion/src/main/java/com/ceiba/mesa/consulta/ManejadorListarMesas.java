package com.ceiba.mesa.consulta;

import com.ceiba.mesa.modelo.dto.DtoMesa;
import com.ceiba.mesa.puerto.dao.DaoMesa;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarMesas {

    private final DaoMesa daoMesa;

    public ManejadorListarMesas(DaoMesa daoMesa){
        this.daoMesa = daoMesa;
    }

    public List<DtoMesa> ejecutar(){ return this.daoMesa.listar(); }
}
