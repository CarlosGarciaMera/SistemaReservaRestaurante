package com.ceiba.listanegra.controlador;


import com.ceiba.ComandoRespuesta;
import com.ceiba.listanegra.consulta.ManejadorFindByIdListaNegra;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/listanegra")
@Api(tags={"Controlador consulta lista negra"})
public class ConsultaControladorListaNegra {

    private final ManejadorFindByIdListaNegra manejadorFindById;

    @Autowired
    public ConsultaControladorListaNegra(ManejadorFindByIdListaNegra manejadorFindById) {
        this.manejadorFindById = manejadorFindById;
    }

    @GetMapping(value="/{id}")
    @ApiOperation("Find by id listaNegra")
    public ComandoRespuesta<Boolean> findById(@PathVariable Long id) {
        return this.manejadorFindById.ejecutar(id);
    }

}
