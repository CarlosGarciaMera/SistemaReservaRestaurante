package com.ceiba.reserva.servicio;


import java.time.LocalDateTime;


public class ServicioValidacionFechaCrearReserva {

    private ServicioValidarFechaParaDiaLunesOMiercoles servicioValidarReservaParaDiaLunesOMiercoles;
    private ServicioValidarHoraParEntre8y20Horas servicioValidarHoraParEntre8y20Horas;
    private ServicioValidarDiaAnticipacionParaReserva servicioValidarDiaAnticipacionParaReserva;
    public ServicioValidacionFechaCrearReserva(ServicioValidarFechaParaDiaLunesOMiercoles servicioValidarFechaParaDiaLunesOMiercoles,
                                               ServicioValidarHoraParEntre8y20Horas servicioValidarHoraParEntre8y20Horas,
                                               ServicioValidarDiaAnticipacionParaReserva servicioValidarDiaAnticipacionParaReserva) {
        this.servicioValidarReservaParaDiaLunesOMiercoles = servicioValidarFechaParaDiaLunesOMiercoles;
        this.servicioValidarHoraParEntre8y20Horas = servicioValidarHoraParEntre8y20Horas;
        this.servicioValidarDiaAnticipacionParaReserva = servicioValidarDiaAnticipacionParaReserva;
    }

    public void validar(LocalDateTime fechaReserva) {
        servicioValidarReservaParaDiaLunesOMiercoles.validar(fechaReserva);
        servicioValidarHoraParEntre8y20Horas.validar(fechaReserva);
        servicioValidarDiaAnticipacionParaReserva.validar(fechaReserva.toLocalDate());
    }

}
