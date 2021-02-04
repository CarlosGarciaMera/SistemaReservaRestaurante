package com.ceiba.mesa.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.mesa.comando.ComandoMesa;
import com.ceiba.mesa.servicio.testdatabuilder.ComandoMesaTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ComandoControladorMesa.class)
public class ComandoControladorMesaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void crear_return_status200_idMesaCreada() throws Exception{
        // arrange
        ComandoMesa mesa = new ComandoMesaTestDataBuilder().build();

        // act - assert
        mocMvc.perform(post("/mesas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mesa)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 4}"));
    }

    @Test
    public void actualizar_return_status200() throws Exception{
        // arrange
        Long id = 2L;
        ComandoMesa mesa = new ComandoMesaTestDataBuilder().conNombre("Mesa Actualizada").build();

        // act - assert
        mocMvc.perform(put("/mesas/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mesa)))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminar_return_status200() throws Exception {
        // arrange
        Long id = 3L;

        // act - assert
        mocMvc.perform(delete("/mesas/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
