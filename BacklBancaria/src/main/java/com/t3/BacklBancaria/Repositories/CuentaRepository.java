package com.t3.BacklBancaria.Repositories;

import com.t3.BacklBancaria.Models.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity,Long> {

    @Query("SELECT c FROM CuentaEntity c WHERE c.cliente.clienteId = :clienteId")
    List<CuentaEntity> findCuentasByClienteId(@Param("clienteId") Long clienteId);

    @Query("SELECT c.saldo FROM CuentaEntity c WHERE c.cuentaId = :idCuenta")
    Double obtenerSaldoPorId(@Param("idCuenta") Long idCuenta);
}
