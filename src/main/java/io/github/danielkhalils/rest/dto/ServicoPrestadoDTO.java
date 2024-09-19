package io.github.danielkhalils.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServicoPrestadoDTO {

    private String descricao;
    private String valor;
    private String data;
    private Integer idCliente;

}
