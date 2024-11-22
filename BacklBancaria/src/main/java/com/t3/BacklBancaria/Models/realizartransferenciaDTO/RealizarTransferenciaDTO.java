package com.t3.BacklBancaria.Models.realizartransferenciaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RealizarTransferenciaDTO {

    private Long cuentaOrigenId;
    private Long cuentaDestinoId;

    private BigDecimal monto;

    private String descripcion;

}
