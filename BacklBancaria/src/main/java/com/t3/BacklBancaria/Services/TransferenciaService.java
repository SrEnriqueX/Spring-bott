package com.t3.BacklBancaria.Services;

import com.t3.BacklBancaria.DTO.TransferenciaDepositoDTO;
import com.t3.BacklBancaria.Models.CuentaEntity;
import com.t3.BacklBancaria.Models.TransferenciaEntity;
import com.t3.BacklBancaria.Repositories.CuentaRepository;
import com.t3.BacklBancaria.Repositories.TransferenciaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public Page<TransferenciaDepositoDTO> obtenerTransferenciasYDepositos(Integer cuentaId, Pageable pageable) {

        return transferenciaRepository.obtenerTransferenciasDepositos(cuentaId,pageable);

    }

    public Page<TransferenciaDepositoDTO> obtenerTransferenciasYDepositosPorRangoFecha(Integer cuentaId,
                                                                                       LocalDate fechaInicio,
                                                                                       LocalDate fechaFin,
                                                                                       Pageable pageable) {

        return transferenciaRepository.obtenerTransferenciasDepositosPorRangoFecha(cuentaId, fechaInicio, fechaFin, pageable);

    }

    public Boolean verificarFondosSuficientes(Long idCuenta, Double monto){
        Double saldo = cuentaRepository.obtenerSaldoPorId(idCuenta);

        if(saldo == null){
            return false;
        }

        return saldo >= monto;
    }

    @Transactional
    public void realizarTransferencia(Long cuentaOrigenId, Long cuentaDestinoId, BigDecimal monto, String descripcion){
        CuentaEntity cuentaOrigen = cuentaRepository.findById(cuentaOrigenId)
                .orElseThrow(()-> new RuntimeException("Cuenta origen no encontrada"));

        CuentaEntity cuentaDestino = cuentaRepository.findById(cuentaDestinoId)
                .orElseThrow(() -> new RuntimeException("Cuenta destino no encontrada"));

        if (cuentaOrigen.getSaldo().compareTo(monto) < 0) {
            throw new RuntimeException("Fondos insuficientes en la cuenta de origen");
        }

        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo().subtract(monto));
        cuentaRepository.save(cuentaOrigen);

        if (descripcion != null && descripcion.contains("error")) {
            throw new RuntimeException("Error forzado para probar el rollback");
        }



        cuentaDestino.setSaldo(cuentaDestino.getSaldo().add(monto));
        cuentaRepository.save(cuentaDestino);

        TransferenciaEntity transferencia = TransferenciaEntity.builder()
                .cuentaOrigen(cuentaOrigen)
                .cuentaDestino(cuentaDestino)
                .monto(monto)
                .descripcion(descripcion)
                .build();
        transferenciaRepository.save(transferencia);
    }


}
