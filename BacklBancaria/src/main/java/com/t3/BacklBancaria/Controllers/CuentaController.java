package com.t3.BacklBancaria.Controllers;

import com.t3.BacklBancaria.DTO.JsendResponse;
import com.t3.BacklBancaria.Models.ClienteEntity;
import com.t3.BacklBancaria.Models.CuentaEntity;
import com.t3.BacklBancaria.Services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/Obtener-cuentas-por-id-cliente/{clienteId}")
    public ResponseEntity<JsendResponse<List<CuentaEntity>>> obtenerCuentasPorIdCliente(@PathVariable("clienteId") Long clienteId){
        try{
            List<CuentaEntity> listaCuentas = cuentaService.findCuentasByClienteId(
                    clienteId
            );

            if(listaCuentas != null && !listaCuentas.isEmpty()){
                return ResponseEntity.ok( new JsendResponse<>("success",listaCuentas));
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new JsendResponse<>("fail", "Cuentas sin cliente"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new JsendResponse<>("error", "Ocurrió un error al obtener cuentas: " + e.getMessage()));
        }
    }
}
