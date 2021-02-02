package com.ceiba.mesa.controlador;

import java.util.List;

import com.ceiba.mesa.consulta.ManejadorListarMesas;
import com.ceiba.mesa.modelo.dto.DtoMesa;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/mesas")
@Api(tags={"Controlador consulta usuario"})
public class ConsultaControladorMesa {

    private final ManejadorListarMesas manejadorListarmesas;

    public ConsultaControladorMesa(ManejadorListarMesas manejadorListarmesas) {
        this.manejadorListarmesas = manejadorListarmesas;
    }

    @GetMapping
    @ApiOperation("Listar Mesas")
    public List<DtoMesa> listar() {
        return this.manejadorListarmesas.ejecutar();
    }

}
