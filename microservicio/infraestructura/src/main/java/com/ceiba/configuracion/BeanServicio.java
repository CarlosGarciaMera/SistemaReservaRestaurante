package com.ceiba.configuracion;

import com.ceiba.listanegra.puerto.dao.DaoListaNegra;
import com.ceiba.listanegra.puerto.repositorio.RepositorioListaNegra;
import com.ceiba.listanegra.servicio.ServicioEliminarListaNegra;
import com.ceiba.listanegra.servicio.ServicioRegistrarListaNegra;
import com.ceiba.listanegra.servicio.ServicioValidarListaNegra;
import com.ceiba.mesa.puerto.dao.DaoMesa;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;
import com.ceiba.mesa.servicio.ServicioActualizarMesa;
import com.ceiba.mesa.servicio.ServicioCrearMesa;
import com.ceiba.mesa.servicio.ServicioEliminarMesa;
import com.ceiba.mesa.servicio.ServicioValidacionMesa;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearMesa servicioCrearMesa(RepositorioMesa repositorioMesa, ServicioValidacionMesa servicioValidacionMesa) {
        return new ServicioCrearMesa(repositorioMesa, servicioValidacionMesa);
    }

    @Bean
    public ServicioEliminarMesa servicioEliminarMesa(RepositorioMesa repositorioMesa) {
        return new ServicioEliminarMesa(repositorioMesa);
    }

    @Bean
    public ServicioActualizarMesa servicioActualizarMesa(RepositorioMesa repositorioMesa, ServicioValidacionMesa servicioValidacionMesa) {
        return new ServicioActualizarMesa(repositorioMesa, servicioValidacionMesa);
    }

    @Bean
    public ServicioValidacionMesa servicioValidacionMesa(DaoMesa daoMesa) {
        return new ServicioValidacionMesa(daoMesa);
    }

    @Bean
    public ServicioEliminarListaNegra servicioEliminarListaNegra(RepositorioListaNegra repositorioListaNegra) {
        return new ServicioEliminarListaNegra(repositorioListaNegra);
    }
    @Bean
    public ServicioRegistrarListaNegra servicioRegistrarListaNegra(RepositorioListaNegra repositorioListaNegra) {
        return new ServicioRegistrarListaNegra(repositorioListaNegra);
    }

    @Bean
    public ServicioValidarListaNegra servicioValidarListaNegra(DaoListaNegra daoListaNegra) {
        return new ServicioValidarListaNegra(daoListaNegra);
    }

    @Bean
    public ServicioValidarDiaAnticipacionParaReserva servicioValidarDiaAnticipacionParaReserva() {
        return new ServicioValidarDiaAnticipacionParaReserva();
    }

    @Bean
    public ServicioValidarFechaParaDiaLunesOMiercoles servicioValidarFechaParaDiaLunesOMiercoles() {
        return new ServicioValidarFechaParaDiaLunesOMiercoles();
    }

    @Bean
    public ServicioValidarHoraParEntre8y20Horas servicioValidarHoraParEntre8y20Horas() {
        return new ServicioValidarHoraParEntre8y20Horas();
    }

    @Bean
    public ServicioValidacionFechaCrearReserva servicioValidacionFechaCrearReserva(
            ServicioValidarFechaParaDiaLunesOMiercoles servicioValidarFechaParaDiaLunesOMiercoles,
            ServicioValidarHoraParEntre8y20Horas servicioValidarHoraParEntre8y20Horas,
            ServicioValidarDiaAnticipacionParaReserva servicioValidarDiaAnticipacionParaReserva) {
        return new ServicioValidacionFechaCrearReserva(servicioValidarFechaParaDiaLunesOMiercoles,
                servicioValidarHoraParEntre8y20Horas, servicioValidarDiaAnticipacionParaReserva);
    }

    @Bean
    public ServicioValidacionCancelarReserva servicioValidacionCancelarReserva() {
        return new ServicioValidacionCancelarReserva();
    }

    @Bean
    public ServicioCrearReserva servicioCrearReserva(RepositorioReserva repositorioReserva, DaoReserva daoReserva,
                                                     DaoMesa daoMesa,DaoListaNegra daoListaNegra,
                                                     ServicioValidacionFechaCrearReserva servicioValidacionesFechaCrear) {
        return new ServicioCrearReserva(repositorioReserva,daoReserva, daoMesa, daoListaNegra, servicioValidacionesFechaCrear);
    }

    @Bean
    public ServicioEliminarReserva servicioEliminarReserva(RepositorioReserva repositorioReserva, DaoReserva daoReserva, ServicioValidacionCancelarReserva servicioValidacionCancelarReserva) {
        return new ServicioEliminarReserva(repositorioReserva,daoReserva,servicioValidacionCancelarReserva);
    }
}
