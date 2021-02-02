package com.ceiba.reserva.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoReserva implements RowMapper<DtoReserva>, MapperResult {

    @Override
    public DtoReserva mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        Long idCliente = resultSet.getLong("idCliente");
        String nombreCliente = resultSet.getString("nombreCliente");
        int cantidadComensales = resultSet.getInt("cantidadComensales");
        Long idMesa = resultSet.getLong("idMesa");
        LocalDateTime fecha =  resultSet.getObject("fecha", LocalDateTime.class);

        return new DtoReserva(id, idCliente, nombreCliente,cantidadComensales,idMesa,fecha);
    }

}
