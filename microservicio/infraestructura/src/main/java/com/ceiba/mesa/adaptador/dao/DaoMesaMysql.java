package com.ceiba.mesa.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.mesa.modelo.dto.DtoMesa;
import com.ceiba.mesa.puerto.dao.DaoMesa;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class DaoMesaMysql implements DaoMesa {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="mesa", value="listar")
    private static String sqlListar;

    @SqlStatement(namespace="mesa", value="findById")
    private static String sqlFindById;

    public DaoMesaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoMesa> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoMesa());
    }

    @Override
    public Optional<DtoMesa> getById(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        DtoMesa mesa = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().
                queryForObject(sqlFindById, parameterSource, new MapeoMesa());
        return Optional.ofNullable(mesa);
    }
}
