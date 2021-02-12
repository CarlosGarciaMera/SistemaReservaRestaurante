package com.ceiba.reserva.servicio;


import com.ceiba.core.BasePrueba;
import com.ceiba.dominio.excepcion.ReservaException;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

public class ServicioEliminarReservaTest {
   
    @Test
    public void eliminarNoPermiteReservaNoEncontradaConIdTest() {
        // arrange
        Long idReserva = 1L;
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        ServicioValidacionCancelarReserva servicioValidacionCancelar = Mockito.mock(ServicioValidacionCancelarReserva.class);

        Mockito.when(daoReserva.getById(idReserva)).thenReturn(Optional.empty());
        ServicioEliminarReserva servicio = new ServicioEliminarReserva(repositorioReserva, daoReserva,servicioValidacionCancelar);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.ejecutar(idReserva), ReservaException.class,"No existe una reserva con ID: 1");
    }

    @Test
    public void eliminarNoPermiteServicioValidacionCancelarLanzaExcepcionTest() {
        // arrange
        Long idReserva = 1L;
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        ServicioValidacionCancelarReserva servicioValidacionCancelar = Mockito.mock(ServicioValidacionCancelarReserva.class);
        DtoReserva reserva = new DtoReserva(12L,123L,"nombre cliente", 8, 321L, LocalDateTime.now());
        Mockito.when(daoReserva.getById(idReserva)).thenReturn(Optional.ofNullable(reserva));
        Mockito.doThrow(new ReservaException("cualquier Excepcion de este servicio")).when(servicioValidacionCancelar).validar(reserva);
        ServicioEliminarReserva servicio = new ServicioEliminarReserva(repositorioReserva, daoReserva,servicioValidacionCancelar);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.ejecutar(idReserva), ReservaException.class,"cualquier Excepcion de este servicio");
    }

    @Test
    public void eliminarTest() {
        // arrange
        Long idReserva = 1L;
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        ServicioValidacionCancelarReserva servicioValidacionCancelar = Mockito.mock(ServicioValidacionCancelarReserva.class);
        DtoReserva reserva = new DtoReserva(12L,123L,"nombre cliente", 8, 321L, LocalDateTime.now());
        Mockito.when(daoReserva.getById(idReserva)).thenReturn(Optional.ofNullable(reserva));
        ServicioEliminarReserva servicio = new ServicioEliminarReserva(repositorioReserva, daoReserva,servicioValidacionCancelar);
        // act - assert
        // act
        servicio.ejecutar(idReserva);
        // assert
        Mockito.verify(repositorioReserva).eliminar(idReserva);

    }
}
