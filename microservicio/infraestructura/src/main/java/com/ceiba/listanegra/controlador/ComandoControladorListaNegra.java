package com.ceiba.listanegra.controlador;


import com.ceiba.ComandoRespuesta;
import com.ceiba.listanegra.comando.ComandoListaNegra;
import com.ceiba.listanegra.comando.manejador.ManejadorEliminarListaNegra;
import com.ceiba.listanegra.comando.manejador.ManejadorRegistrarListaNegra;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/listanegra")
@Api(tags = { "Controlador comando vetado en lista negra"})
public class ComandoControladorListaNegra {

    private final ManejadorRegistrarListaNegra manejadorCrear;
	private final ManejadorEliminarListaNegra manejadorEliminar;

    @Autowired
    public ComandoControladorListaNegra(ManejadorRegistrarListaNegra manejadorCrear, ManejadorEliminarListaNegra manejadorEliminar) {
        this.manejadorCrear = manejadorCrear;
		this.manejadorEliminar = manejadorEliminar;
    }

    @PostMapping
    @ApiOperation("Crear vetado en lista negra")
    public ComandoRespuesta<Long> registrar(@RequestBody ComandoListaNegra comando) {
        return manejadorCrear.ejecutar(comando);
    }

    @DeleteMapping(value="/{id}")
	@ApiOperation("Eliminar vetado de lista negra")
	public void eliminar(@PathVariable Long id) {
		manejadorEliminar.ejecutar(id);
	}

}
