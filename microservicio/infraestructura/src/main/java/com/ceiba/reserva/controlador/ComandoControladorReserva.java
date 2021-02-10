package com.ceiba.reserva.controlador;


import com.ceiba.ComandoRespuesta;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.comando.manejador.ManejadorCrearReserva;
import com.ceiba.reserva.comando.manejador.ManejadorEliminarReserva;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/reservas")
@Api(tags = { "Controlador comando reserva"})
public class ComandoControladorReserva {

    private final ManejadorCrearReserva manejadorCrear;
	private final ManejadorEliminarReserva manejadorEliminar;

    @Autowired
    public ComandoControladorReserva(ManejadorCrearReserva manejadorCrear,
									 ManejadorEliminarReserva manejadorEliminar) {
        this.manejadorCrear = manejadorCrear;
		this.manejadorEliminar = manejadorEliminar;
    }

    @PostMapping
    @ApiOperation("Crear Reserva")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoReserva comandoReserva) {
        return manejadorCrear.ejecutar(comandoReserva);
    }

    @DeleteMapping(value="/{id}")
	@ApiOperation("Eliminar Mesa")
	public void cancelar(@PathVariable Long id) {
		manejadorEliminar.ejecutar(id);
	}
}
