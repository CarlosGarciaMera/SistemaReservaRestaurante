package com.ceiba.reserva.consulta;

import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarReservas {

    private final DaoReserva dao;

    public ManejadorListarReservas(DaoReserva dao){
        this.dao = dao;
    }

    public List<DtoReserva> ejecutar(){ return this.dao.listar(); }
}
