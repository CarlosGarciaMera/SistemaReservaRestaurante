package com.ceiba.listanegra.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.listanegra.comando.ComandoListaNegra;
import com.ceiba.listanegra.servicio.testdatabuilder.ComandoListaNegraTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ComandoControladorListaNegra.class)
public class ComandoControladorListaNegraTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void crear() throws Exception{
        // arrange
        ComandoListaNegra listanegra = new ComandoListaNegraTestDataBuilder().build();
        // act - assert
        mocMvc.perform(post("/listanegra")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(listanegra)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 3}"));
    }

    @Test
    public void eliminar() throws Exception {
        // arrange
        Long idVetadoExistente = 2L;
        // act - assert
        mocMvc.perform(delete("/listanegra/{id}",idVetadoExistente)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
