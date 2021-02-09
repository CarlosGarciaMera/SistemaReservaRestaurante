package com.ceiba.listanegra.controlador;


import com.ceiba.ComandoRespuesta;
import com.ceiba.listanegra.consulta.ManejadorFindByIdListaNegra;
import com.ceiba.listanegra.consulta.ManejadorListarVetados;
import com.ceiba.listanegra.modelo.dto.DtoListaNegra;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/listanegra")
@Api(tags={"Controlador consulta lista negra"})
public class ConsultaControladorListaNegra {

    private final ManejadorFindByIdListaNegra manejadorFindById;
    private final ManejadorListarVetados manejadorListarVetados;

    @Autowired
    public ConsultaControladorListaNegra(ManejadorFindByIdListaNegra manejadorFindById,
                                         ManejadorListarVetados manejadorListarVetados) {
        this.manejadorFindById = manejadorFindById;
        this.manejadorListarVetados = manejadorListarVetados;
    }

    @GetMapping(value="/{id}")
    @ApiOperation("Find by id listaNegra")
    public ComandoRespuesta<Boolean> findById(@PathVariable Long id) {
        return this.manejadorFindById.ejecutar(id);
    }

    @GetMapping
    @ApiOperation("Listar Vetados")
    public List<DtoListaNegra> listar() {
        return this.manejadorListarVetados.ejecutar();
    }
}
