package com.t3.BacklBancaria.Services;

import com.t3.BacklBancaria.Models.CuentaEntity;
import com.t3.BacklBancaria.Repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<CuentaEntity> findCuentasByClienteId(Long clienteId){
        return cuentaRepository.findCuentasByClienteId(clienteId);
    }

    public Boolean verificarFondosSuficientes(Long idCuenta, Double monto){
        Double saldo = cuentaRepository.obtenerSaldoPorId(idCuenta);

        if(saldo == null){
            return false;
        }

        return saldo >= monto;
    }
}
