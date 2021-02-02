package com.ceiba.listanegra.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.listanegra.modelo.entidad.ListaNegra;
import com.ceiba.listanegra.puerto.repositorio.RepositorioListaNegra;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioListaNegraMysql implements RepositorioListaNegra {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="listanegra", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="listanegra", value="eliminar")
    private static String sqlEliminar;


    public RepositorioListaNegraMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public void registrar(ListaNegra vetado) {
        this.customNamedParameterJdbcTemplate.crear(vetado, sqlCrear);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

}
