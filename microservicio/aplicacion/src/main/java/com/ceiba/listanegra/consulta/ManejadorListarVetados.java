package com.ceiba.listanegra.consulta;

import com.ceiba.listanegra.modelo.dto.DtoListaNegra;
import com.ceiba.listanegra.puerto.dao.DaoListaNegra;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarVetados {

    private final DaoListaNegra dao;

    public ManejadorListarVetados(DaoListaNegra dao){
        this.dao = dao;
    }

    public List<DtoListaNegra> ejecutar(){ return this.dao.listar(); }
}
