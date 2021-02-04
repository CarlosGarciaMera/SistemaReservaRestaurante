package com.ceiba.listanegra.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.listanegra.modelo.dto.DtoListaNegra;
import com.ceiba.listanegra.puerto.dao.DaoListaNegra;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;


@Component
public class DaoListaNegraMysql implements DaoListaNegra {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="listanegra", value="findById")
    private static String sqlFindById;

    public DaoListaNegraMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public boolean isVetado(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        DtoListaNegra vetado = null;
        try {
            return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                    .queryForObject(sqlFindById, parameterSource, new MapeoListaNegra()) != null;
        } catch (IncorrectResultSizeDataAccessException exception) {
            return false;
        }
    }
}
