package com.t3.BacklBancaria.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferenciaDepositoDTO {

    private Integer idTransferencia;
    private Integer cuentaOrigenId;
    private Integer cuentaDestinoId;
    private BigDecimal monto;
    private LocalDateTime fecha;
    private String descripcion;
    private String tipo;

    // Constructor
    public TransferenciaDepositoDTO(Integer idTransferencia, Integer cuentaOrigenId, Integer cuentaDestinoId,
                                    BigDecimal monto, LocalDateTime fecha, String descripcion, String tipo) {
        this.idTransferencia = idTransferencia;
        this.cuentaOrigenId = cuentaOrigenId;
        this.cuentaDestinoId = cuentaDestinoId;
        this.monto = monto;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    // Getters y Setters
    public Integer getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(Integer idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public Integer getCuentaOrigenId() {
        return cuentaOrigenId;
    }

    public void setCuentaOrigenId(Integer cuentaOrigenId) {
        this.cuentaOrigenId = cuentaOrigenId;
    }

    public Integer getCuentaDestinoId() {
        return cuentaDestinoId;
    }

    public void setCuentaDestinoId(Integer cuentaDestinoId) {
        this.cuentaDestinoId = cuentaDestinoId;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
