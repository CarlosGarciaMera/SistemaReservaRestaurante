package com.ceiba.mesa.controlador;


import com.ceiba.ComandoRespuesta;
import com.ceiba.mesa.comando.ComandoMesa;
import com.ceiba.mesa.comando.manejador.ManejadorActualizarMesa;
import com.ceiba.mesa.comando.manejador.ManejadorCrearMesa;
import com.ceiba.mesa.comando.manejador.ManejadorEliminarMesa;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mesas")
@Api(tags = { "Controlador comando mesa"})
public class ComandoControladorMesa {

    private final ManejadorCrearMesa manejadorCrear;
	private final ManejadorEliminarMesa manejadorEliminar;
	private final ManejadorActualizarMesa manejadorActualizar;

    @Autowired
    public ComandoControladorMesa(ManejadorCrearMesa manejadorCrear,
									 ManejadorEliminarMesa manejadorEliminar,
									 ManejadorActualizarMesa manejadorActualizar) {
        this.manejadorCrear = manejadorCrear;
		this.manejadorEliminar = manejadorEliminar;
		this.manejadorActualizar = manejadorActualizar;
    }

    @PostMapping
    @ApiOperation("Crear Mesa")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoMesa comandoMesa) {
        return manejadorCrear.ejecutar(comandoMesa);
    }

    @DeleteMapping(value="/{id}")
	@ApiOperation("Eliminar Mesa")
	public void eliminar(@PathVariable Long id) {
		manejadorEliminar.ejecutar(id);
	}

	@PutMapping(value="/{id}")
	@ApiOperation("Actualizar Mesa")
	public void actualizar(@RequestBody ComandoMesa comandoMesa, @PathVariable Long id) {
		comandoMesa.setId(id);
		manejadorActualizar.ejecutar(comandoMesa);
	}
}
