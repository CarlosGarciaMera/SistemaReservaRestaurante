package com.ceiba.reserva.servicio;

import com.ceiba.Excepcion.ReservaException;
import com.ceiba.core.BasePrueba;
import com.ceiba.listanegra.puerto.dao.DaoListaNegra;
import com.ceiba.mesa.modelo.dto.DtoMesa;
import com.ceiba.mesa.puerto.dao.DaoMesa;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;


public class ServicioCrearReservaTest {

    private RepositorioReserva repositorioReserva;
    private DaoReserva daoReserva;
    private DaoMesa daoMesa;
    private DaoListaNegra daoListaNegra;
    private ServicioValidacionFechaCrearReserva servicioValidacionesFechaCrear;

    @Before
    public void setUp() {
        repositorioReserva = Mockito.mock(RepositorioReserva.class);
        daoReserva = Mockito.mock(DaoReserva.class);
        daoMesa = Mockito.mock(DaoMesa.class);
        daoListaNegra = Mockito.mock(DaoListaNegra.class);
        servicioValidacionesFechaCrear = Mockito.mock(ServicioValidacionFechaCrearReserva.class);
    }

    @Test
    public void crearReservaNoPermitePorClienteVetado() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        Mockito.when(daoListaNegra.isVetado(Mockito.anyLong())).thenReturn(true);
        ServicioCrearReserva servicio = new ServicioCrearReserva(repositorioReserva, daoReserva, daoMesa, daoListaNegra, servicioValidacionesFechaCrear);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.ejecutar(reserva), ReservaException.class,"El Cliente se encuentra vetado, no es posible hacer reservas");
    }

    @Test
    public void crearReservaNoPermiteClienteDosReservasMismaHoraMismoDia() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        List<DtoReserva> reservasPersistentes = new ArrayList<>();
        DtoReserva reservaEncontrada = new DtoReserva(21L, reserva.getIdCliente(), reserva.getNombreCliente(), 4,565L, reserva.getFecha());
        reservasPersistentes.add(reservaEncontrada);
        Mockito.when(daoReserva.listar()).thenReturn(reservasPersistentes);
        ServicioCrearReserva servicio = new ServicioCrearReserva(repositorioReserva, daoReserva, daoMesa, daoListaNegra, servicioValidacionesFechaCrear);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.ejecutar(reserva), ReservaException.class,"El Cliente ya cuenta con una reserva para a el mismo dia y la misma hora");
    }

    @Test
    public void crearReservaNoPermiteServiciovalidacionfechaLanzaException() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        Mockito.doThrow(new ReservaException("cualquier Excepcion de este servicio")).when(servicioValidacionesFechaCrear).validar(reserva);
        ServicioCrearReserva servicio = new ServicioCrearReserva(repositorioReserva, daoReserva, daoMesa, daoListaNegra, servicioValidacionesFechaCrear);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.ejecutar(reserva), ReservaException.class,"cualquier Excepcion de este servicio");
    }

    @Test
    public void crearReservaNoPermiteMesaNoDisponible() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        List<DtoMesa> mesasPersistentes = new ArrayList<>();
        long idMesaPrueba = 123L;
        DtoMesa mesaEncontrada = new DtoMesa(idMesaPrueba, "La Mejor mesa", 15);
        Mockito.when(daoMesa.listar()).thenReturn(mesasPersistentes);
        List<DtoReserva> reservasPersistentes = new ArrayList<>();
        DtoReserva reservaEncontrada = new DtoReserva(21L, 21L, "otro nombre", 11, idMesaPrueba, reserva.getFecha());
        Mockito.when(daoReserva.listar()).thenReturn(reservasPersistentes);
        ServicioCrearReserva servicio = new ServicioCrearReserva(repositorioReserva, daoReserva, daoMesa, daoListaNegra, servicioValidacionesFechaCrear);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.ejecutar(reserva), ReservaException.class,"No hay mesas disponibles para la reserva");
    }
}
