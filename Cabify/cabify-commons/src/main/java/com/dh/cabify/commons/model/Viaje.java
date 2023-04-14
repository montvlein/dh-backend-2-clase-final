package com.dh.cabify.commons.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Viaje {
    private String origen;
    private String destino;
    private List<String> destinosIntermedios;
    private Conductor conductor;
    private Usuario usuario;
    private String metodoPago;
    private LocalDateTime fechaHora;
    private BigDecimal importeEstimado;
    private double duracionEstimadaSegundos;
    private boolean viajeCompletado;
    private BigDecimal importeReal;
    private double distanciaRecorrida;


}
