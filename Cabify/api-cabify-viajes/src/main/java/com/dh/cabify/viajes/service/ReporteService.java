package com.dh.cabify.viajes.service;

import com.dh.cabify.commons.model.Viaje;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReporteService {





    public void reporte(List<Viaje> viajes) {

        var result = viajes.stream().map(Viaje::getImporteReal).reduce(BigDecimal.ZERO, BigDecimal::add);

        var maximaDistancia =  viajes.stream().map(Viaje::getDistanciaRecorrida).max(Comparator.comparing(Double::doubleValue)).get();

        viajes.sort(Comparator.comparing(Viaje::getFechaHora));

        viajes.stream().filter(predicateViaje()).collect(Collectors.toList());
    }

    private static Predicate<Viaje> predicateViaje() {
        return viaje -> viaje.getDestino().equals("BUenos Aires");
    }
}
