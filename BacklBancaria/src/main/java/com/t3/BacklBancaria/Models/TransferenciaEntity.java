package com.t3.BacklBancaria.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "transferencia")
public class TransferenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer transferenciaId;

    @ManyToOne
    @JoinColumn(name = "cuenta_origen_id")
    @JsonIgnore
    public CuentaEntity cuentaOrigen;

    @ManyToOne
    @JoinColumn(name = "cuenta_destino_id")
    @JsonIgnore
    public CuentaEntity cuentaDestino;

    @Column(nullable = false, precision = 15, scale = 2)
    public BigDecimal monto;

    @Column(nullable = false)
    public LocalDateTime fecha;

    @Column(length = 255)
    public String descripcion;

    @PrePersist
    public void prePersist() {
        this.fecha = LocalDateTime.now();
    }
}
