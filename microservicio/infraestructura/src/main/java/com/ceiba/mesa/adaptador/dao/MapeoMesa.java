package com.ceiba.mesa.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.mesa.modelo.dto.DtoMesa;
import org.springframework.jdbc.core.RowMapper;

public class MapeoMesa implements RowMapper<DtoMesa>, MapperResult {

    @Override
    public DtoMesa mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        int cantidadMaximaComensales = resultSet.getInt("cantidadMaximaComensales");

        return new DtoMesa(id,nombre,cantidadMaximaComensales);
    }

}
