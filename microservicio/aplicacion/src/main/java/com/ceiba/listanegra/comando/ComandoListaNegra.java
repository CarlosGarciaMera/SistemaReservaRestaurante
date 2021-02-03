package com.ceiba.listanegra.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoListaNegra {

    private Long id;
    private Long idCliente;
    private String nombreCliente;
}
