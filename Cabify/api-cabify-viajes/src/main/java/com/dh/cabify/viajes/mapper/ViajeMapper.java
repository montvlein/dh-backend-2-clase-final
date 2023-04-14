package com.dh.cabify.viajes.mapper;


import com.dh.cabify.commons.model.Viaje;
import com.dh.cabify.viajes.controller.ViajeController;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ViajeMapper {

    @Mappings({
            @Mapping(source = "origen", target = "ubicacion.latitude")
    })
    ViajeController.ViajeDTO toViajeDto(Viaje viaje);


    @InheritInverseConfiguration
    Viaje toViaje(ViajeController.ViajeDTO viaje);
}
