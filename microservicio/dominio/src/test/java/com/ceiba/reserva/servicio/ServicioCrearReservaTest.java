package com.ceiba.reserva.servicio;

import com.ceiba.core.BasePrueba;
import com.ceiba.dominio.excepcion.ReservaException;
import com.ceiba.listanegra.puerto.dao.DaoListaNegra;
import com.ceiba.mesa.modelo.dto.DtoMesa;
import com.ceiba.mesa.puerto.dao.DaoMesa;
import com.ceiba.mesa.servicio.testdatabuilder.DtoMesaTestDataBuilder;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.DtoReservaTestDataBuilder;
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
    private ServicioCrearReserva servicio ;

    @Before
    public void setUp() {
        repositorioReserva = Mockito.mock(RepositorioReserva.class);
        daoReserva = Mockito.mock(DaoReserva.class);
        daoMesa = Mockito.mock(DaoMesa.class);
        daoListaNegra = Mockito.mock(DaoListaNegra.class);
        servicioValidacionesFechaCrear = Mockito.mock(ServicioValidacionFechaCrearReserva.class);
        servicio = new ServicioCrearReserva(repositorioReserva, daoReserva, daoMesa, daoListaNegra, servicioValidacionesFechaCrear);
    }

    @Test
    public void crearReservaNoPermitePorClienteVetado() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        Mockito.when(daoListaNegra.isVetado(reserva.getIdCliente())).thenReturn(true);
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
        // act - assert
        BasePrueba.assertThrows(() -> servicio.ejecutar(reserva), ReservaException.class,"El Cliente ya cuenta con una reserva para a el mismo dia y la misma hora");
    }

    @Test
    public void crearReservaNoPermiteServicioValidacionfechaLanzaException() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        Mockito.doThrow(new ReservaException("cualquier Excepcion de este servicio")).when(servicioValidacionesFechaCrear).validar(reserva.getFecha());
        // act - assert
        BasePrueba.assertThrows(() -> servicio.ejecutar(reserva), ReservaException.class,"cualquier Excepcion de este servicio");
    }

    @Test
    public void crearReservaNoPermiteMesaNoDisponible() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        List<DtoMesa> mesasPersistentes = new ArrayList<>();
        long idMesaPrueba = 123L;
        Mockito.when(daoMesa.listar()).thenReturn(mesasPersistentes);
        List<DtoReserva> reservasPersistentes = new ArrayList<>();
        DtoReserva reservaEncontrada = new DtoReserva(21L, 21L, "otro nombre", 11, idMesaPrueba, reserva.getFecha());
        Mockito.when(daoReserva.listar()).thenReturn(reservasPersistentes);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.ejecutar(reserva), ReservaException.class,"No hay mesas disponibles para la reserva");
    }

    @Test
    public void crearReservaMesaDisponibleNoReservada() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        long idMesaPrueba = 123L;

        List<DtoMesa> mesasPersistentes = new ArrayList<>();
        DtoMesa mesaEncontrada = new DtoMesaTestDataBuilder().conId(idMesaPrueba).build();
        mesasPersistentes.add(mesaEncontrada);
        Mockito.when(daoMesa.listar()).thenReturn(mesasPersistentes);

        List<DtoReserva> reservasPersistentes = new ArrayList<>();
        DtoReserva reservaEncontrada = new DtoReservaTestDataBuilder().build();
        reservasPersistentes.add(reservaEncontrada);
        Mockito.when(daoReserva.listar()).thenReturn(reservasPersistentes);

        //Act
        servicio.ejecutar(reserva);
        //assert
        Mockito.verify(repositorioReserva,Mockito.times(1)).crear(reserva);
    }

    @Test
    public void crearReservaMesaDisponibleReservadaPeroEnHorariosDiferentes() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        long idMesaPrueba = 123L;

        List<DtoMesa> mesasPersistentes = new ArrayList<>();
        DtoMesa mesaEncontrada = new DtoMesaTestDataBuilder().conId(idMesaPrueba).build();
        mesasPersistentes.add(mesaEncontrada);
        Mockito.when(daoMesa.listar()).thenReturn(mesasPersistentes);

        List<DtoReserva> reservasPersistentes = new ArrayList<>();
        DtoReserva reservaEncontrada = new DtoReservaTestDataBuilder().conIdMesa(idMesaPrueba).conFecha(reserva.getFecha().minusHours(4)).build();
        reservasPersistentes.add(reservaEncontrada);
        Mockito.when(daoReserva.listar()).thenReturn(reservasPersistentes);
        //Act
        servicio.ejecutar(reserva);
        //assert
        Mockito.verify(repositorioReserva,Mockito.times(1)).crear(reserva);
    }

    @Test
    public void crearReservaMesaNoDisponiblePorCantidadComensales() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().conCantidadComensales(25).build();
        long idMesaPrueba = 123L;

        List<DtoMesa> mesasPersistentes = new ArrayList<>();
        DtoMesa mesaEncontrada = new DtoMesaTestDataBuilder().conId(idMesaPrueba).conCantidadMaximaComensales(5).build();
        mesasPersistentes.add(mesaEncontrada);
        Mockito.when(daoMesa.listar()).thenReturn(mesasPersistentes);

        List<DtoReserva> reservasPersistentes = new ArrayList<>();
        DtoReserva reservaEncontrada = new DtoReservaTestDataBuilder().conIdMesa(idMesaPrueba).conFecha(reserva.getFecha().minusHours(4)).build();
        reservasPersistentes.add(reservaEncontrada);
        Mockito.when(daoReserva.listar()).thenReturn(reservasPersistentes);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.ejecutar(reserva), ReservaException.class,"No hay mesas disponibles para la reserva");
    }

    @Test
    public void crearReservaNoPerminteMesaDisponiblePorCantidadComensalesOcupadaMismaHora() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().conCantidadComensales(5).build();
        long idMesaPrueba = 123L;

        List<DtoMesa> mesasPersistentes = new ArrayList<>();
        DtoMesa mesaEncontrada = new DtoMesaTestDataBuilder().conId(idMesaPrueba).conCantidadMaximaComensales(6).build();
        mesasPersistentes.add(mesaEncontrada);
        Mockito.when(daoMesa.listar()).thenReturn(mesasPersistentes);

        List<DtoReserva> reservasPersistentes = new ArrayList<>();
        DtoReserva reservaEncontrada = new DtoReservaTestDataBuilder().conIdMesa(idMesaPrueba).conFecha(reserva.getFecha()).build();
        reservasPersistentes.add(reservaEncontrada);
        Mockito.when(daoReserva.listar()).thenReturn(reservasPersistentes);
        // act - assert
        BasePrueba.assertThrows(() -> servicio.ejecutar(reserva), ReservaException.class,"No hay mesas disponibles para la reserva");
    }
}
