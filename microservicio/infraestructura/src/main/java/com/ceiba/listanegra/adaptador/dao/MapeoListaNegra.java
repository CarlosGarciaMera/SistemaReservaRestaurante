package com.ceiba.listanegra.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.listanegra.modelo.dto.DtoListaNegra;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoListaNegra implements RowMapper<DtoListaNegra>, MapperResult {

    @Override
    public DtoListaNegra mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        Long idCliente = resultSet.getLong("idCliente");
        String nombreCliente = resultSet.getString("nombreCliente");

        return new DtoListaNegra( id, idCliente,nombreCliente);
    }

}
