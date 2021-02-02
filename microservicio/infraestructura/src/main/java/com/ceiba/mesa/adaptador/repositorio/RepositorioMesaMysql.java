package com.ceiba.mesa.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioMesaMysql implements RepositorioMesa {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="mesa", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="mesa", value="actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace="mesa", value="eliminar")
    private static String sqlEliminar;


    public RepositorioMesaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Mesa mesa) {
        return this.customNamedParameterJdbcTemplate.crear(mesa, sqlCrear);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

    @Override
    public void actualizar(Mesa mesa) {
        this.customNamedParameterJdbcTemplate.actualizar(mesa, sqlActualizar);
    }

}
