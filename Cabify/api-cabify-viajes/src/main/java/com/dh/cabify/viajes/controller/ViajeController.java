package com.dh.cabify.viajes.controller;

import com.dh.cabify.commons.model.Viaje;
import com.dh.cabify.viajes.mapper.ViajeMapper;
import com.dh.cabify.viajes.repository.ViajeRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ViajeController {
    private ViajeRepo viajeRepo;

    private ViajeMapper viajeMapper;


    public record ViajeDTO(
            Ubicacion ubicacion


    ){}

    public record Ubicacion(
String latitude


    ){}
    @GetMapping("/api/cabify/viajes")
    public Map<String,Object> getViajes(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(defaultValue = "-fechaHora") String sort,
            @RequestParam(defaultValue = "") String filter

    ) {
        Pageable paging = PageRequest.of(offset, limit, Sort.by(sort.charAt(0) == '-' ? Sort.Direction.DESC : Sort.Direction.ASC, sort.replace("-", "")));
        Page<Viaje> result = viajeRepo.findAll(filterViajes(), paging);
String value =" {'nombre':'Jonathan'}";
        ObjectMapper objectMapper= new ObjectMapper();

        Map<String,Object> response = objectMapper.convertValue(value, Map.class);
        response.put("currentPage", result.getNumber());
        response.put("totalItems", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());
        response.put("data", result.getContent().stream().map(viaje -> new ViajeDTO(null)).collect(Collectors.toList()));

        return response;
    }

    private Specification filterViajes() {
        return (Specification<Viaje>) (viajeRoot, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.greaterThanOrEqualTo(viajeRoot.get("fechaHora"), LocalDate.now().minusDays(30)));
            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }


}
