package com.t3.BacklBancaria.Repositories;

import com.t3.BacklBancaria.DTO.TransferenciaDepositoDTO;
import com.t3.BacklBancaria.Models.TransferenciaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.t3.BacklBancaria.DTO.TransferenciaDepositoDTO;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity,Long> {



    @Query("SELECT t, 'Transferencia' AS tipo FROM TransferenciaEntity t WHERE t.cuentaOrigen.id = :cuentaId ORDER BY t.fecha DESC")
    Page<Object[]> obtenerTransferencias(@Param("cuentaId") Integer cuentaIda, Pageable pageable);

    @Query("SELECT t, 'Depósito' AS tipo FROM TransferenciaEntity t WHERE t.cuentaDestino.id = :cuentaId ORDER BY t.fecha DESC")
    Page<Object[]> obtenerDepositos(@Param("cuentaId") Integer cuentaId, Pageable pageable);

//    @Query(value = "SELECT transferencia_id as idTransferencia,cuenta_origen_id AS cuentaOrigenId,cuenta_destino_id AS cuentaDestinoId,monto,fecha,descripcion, " +
//            "CASE WHEN cuenta_origen_id = :cuentaId THEN 'Transferencia' " +
//            "WHEN cuenta_destino_id = :cuentaId THEN 'Depósito' END AS tipo " +
//            "FROM transferencia " +
//            "WHERE (cuenta_origen_id = :cuentaId OR cuenta_destino_id = :cuentaId) ", nativeQuery = true)
//    Page<Object[]> obtenerTransferenciasDepositos(@Param("cuentaId") Integer cuentaId, Pageable pageable);




    @Query(value = "SELECT new com.t3.BacklBancaria.DTO.TransferenciaDepositoDTO(" +
            "t.transferenciaId, t.cuentaOrigen.cuentaId, t.cuentaDestino.cuentaId, t.monto, t.fecha, t.descripcion, " +
            "CASE WHEN t.cuentaOrigen.cuentaId = :cuentaId THEN 'Transferencia' " +
            "WHEN t.cuentaDestino.cuentaId = :cuentaId THEN 'Depósito' END) " +
            "FROM TransferenciaEntity t " +
            "WHERE t.cuentaOrigen.cuentaId = :cuentaId OR t.cuentaDestino.cuentaId = :cuentaId")
    Page<TransferenciaDepositoDTO> obtenerTransferenciasDepositos(@Param("cuentaId") Integer cuentaId, Pageable pageable);

    @Query(value = "SELECT new com.t3.BacklBancaria.DTO.TransferenciaDepositoDTO(" +
            "t.transferenciaId, t.cuentaOrigen.cuentaId, t.cuentaDestino.cuentaId, t.monto, t.fecha, t.descripcion, " +
            "CASE WHEN t.cuentaOrigen.cuentaId = :cuentaId THEN 'Transferencia' " +
            "WHEN t.cuentaDestino.cuentaId = :cuentaId THEN 'Depósito' END) " +
            "FROM TransferenciaEntity t " +
            "WHERE (t.cuentaOrigen.cuentaId = :cuentaId OR t.cuentaDestino.cuentaId = :cuentaId) AND "+
            "(DATE(t.fecha) BETWEEN :fechaInicio AND :fechaFin)"
    )
    Page<TransferenciaDepositoDTO> obtenerTransferenciasDepositosPorRangoFecha(@Param("cuentaId") Integer cuentaId,
                                                                               @Param("fechaInicio") LocalDate fechaInicio,
                                                                               @Param("fechaFin") LocalDate fechaFin,
                                                                               Pageable pageable);


    @Query("SELECT t, 'Transferencia' AS tipo FROM TransferenciaEntity t WHERE "+
            "(t.cuentaOrigen.id = :cuentaId) AND "+
            "(DATE(t.fecha) BETWEEN :fechaInicio AND :fechaFin) "+
            "ORDER BY t.fecha DESC")
    Page<Object[]> obtenerTransferenciasPorRangoFecha(@Param("cuentaId") Integer cuentaIda,
                                                      @Param("fechaInicio") LocalDate fechaInicio,
                                                      @Param("fechaFin") LocalDate fechaFin,
                                                      Pageable pageable);

    @Query("SELECT t, 'Depósito' AS tipo FROM TransferenciaEntity t WHERE "+
            "(t.cuentaDestino.id = :cuentaId) AND "+
            "(DATE(t.fecha) BETWEEN :fechaInicio AND :fechaFin) "+
            "ORDER BY t.fecha DESC")
    Page<Object[]> obtenerDepositosPorRangoFecha(@Param("cuentaId") Integer cuentaIda,
                                                 @Param("fechaInicio") LocalDate fechaInicio,
                                                 @Param("fechaFin") LocalDate fechaFin,
                                                 Pageable pageable);

}
