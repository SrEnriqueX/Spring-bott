package com.t3.BacklBancaria.Controllers;

import com.t3.BacklBancaria.DTO.JsendResponse;
import com.t3.BacklBancaria.DTO.TransferenciaDepositoDTO;
import com.t3.BacklBancaria.Models.CuentaEntity;
import com.t3.BacklBancaria.Models.TransferenciaEntity;
import com.t3.BacklBancaria.Models.realizartransferenciaDTO.RealizarTransferenciaDTO;
import com.t3.BacklBancaria.Repositories.TransferenciaRepository;
import com.t3.BacklBancaria.Services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;
    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @GetMapping("/obtenerTodo")
    public ResponseEntity<Page<TransferenciaEntity>> getTransferencias(Pageable pageable){
        return ResponseEntity.ok(transferenciaRepository.findAll(pageable));
    }

//    @GetMapping("/ObtenerTransferernciasDepositos/{cuentaId}")
//    public ResponseEntity<JsendResponse<Page<Object[]>>> obtenerTransferenciasYDepositos(
//            @PathVariable int cuentaId,
//            Pageable pageable
//    ) {
//
//        try{
//            Page<Object[]> listaTransferencias = transferenciaService.obtenerTransferenciasYDepositos(
//                    cuentaId,
//                    pageable
//            );
//
//            if(listaTransferencias != null && !listaTransferencias.isEmpty()){
//                return ResponseEntity.ok( new JsendResponse<>("success",listaTransferencias));
//            }
//            else{
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(new JsendResponse<>("fail", "Transferencias sin cuentas"));
//            }
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new JsendResponse<>("error", "Ocurrió un error al obtener las transferencias: " + e.getMessage()));
//        }
//    }

    @GetMapping("/ObtenerTransferernciasDepositos")
    public ResponseEntity<JsendResponse<Page<TransferenciaDepositoDTO>>> obtenerTransferenciasYDepositos(
            @RequestParam int cuentaId,
            Pageable pageable
    ) {

        try{
            Page<TransferenciaDepositoDTO> listaTransferencias = transferenciaService.obtenerTransferenciasYDepositos(
                    cuentaId,
                    pageable
            );


            if(listaTransferencias != null && !listaTransferencias.isEmpty()){
                return ResponseEntity.ok( new JsendResponse<>("success",listaTransferencias));
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new JsendResponse<>("fail", "Transferencias sin cuentas"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new JsendResponse<>("error", "Ocurrió un error al obtener las transferencias: " + e.getMessage()));
        }
    }
    
    
    @GetMapping("/ObtenerTransferernciasDepositosPorRangoFecha")
    public ResponseEntity<JsendResponse<Page<TransferenciaDepositoDTO>>> obtenerTransferenciasYDepositosPorRangoFecha(
            @RequestParam int cuentaId,
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin,
            Pageable pageable
            ){

        try{
            Page<TransferenciaDepositoDTO> listaTransferenciasPorRangoFecha = transferenciaService.obtenerTransferenciasYDepositosPorRangoFecha(
                    cuentaId,
                    fechaInicio,
                    fechaFin,
                    pageable
            );

            if(listaTransferenciasPorRangoFecha != null && !listaTransferenciasPorRangoFecha.isEmpty()){
                return ResponseEntity.ok( new JsendResponse<>("success",listaTransferenciasPorRangoFecha));
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new JsendResponse<>("fail", "Transferencias sin cuentas"));
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new JsendResponse<>("error", "Ocurrió un error al obtener las transferencias por rango fecha: " + e.getMessage()));

        }


    }

    @PostMapping("/RealizarTransferencia")
    public ResponseEntity<JsendResponse<String>> realizarTransferencia(@RequestBody RealizarTransferenciaDTO realizarTransferenciaDTO){
        try{
            transferenciaService.realizarTransferencia(
                    realizarTransferenciaDTO.getCuentaOrigenId(),
                    realizarTransferenciaDTO.getCuentaDestinoId(),
                    realizarTransferenciaDTO.getMonto(),
                    realizarTransferenciaDTO.getDescripcion()
            );
            return ResponseEntity.ok( new JsendResponse<>("success","Se realizo la transferencia correctamente"));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new JsendResponse<>("error", "Ocurrió un error al realizar una transferencia: " + e.getMessage()));
        }
    }

    @GetMapping("/verificarSaldo")
    public Boolean verificarSaldo(
            @RequestParam Long idCuenta,
            @RequestParam Double monto
    ){
        return transferenciaService.verificarFondosSuficientes(idCuenta,monto);
    }




}
