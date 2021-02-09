package com.ceiba.listanegra.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.listanegra.modelo.dto.DtoListaNegra;
import com.ceiba.listanegra.puerto.dao.DaoListaNegra;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DaoListaNegraMysql implements DaoListaNegra {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="listanegra", value="esVetado")
    private static String sqlFindById;

    @SqlStatement(namespace="listanegra", value="listar")
    private static String sqlListar;

    public DaoListaNegraMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public boolean isVetado(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idCliente", id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlFindById, parameterSource, Boolean.class);
    }

    @Override
    public List<DtoListaNegra> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoListaNegra());
    }
}
