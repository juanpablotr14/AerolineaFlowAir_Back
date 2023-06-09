package co.edu.usbcali.aerolinea.mappers;

import co.edu.usbcali.aerolinea.domain.Avion;
import co.edu.usbcali.aerolinea.dto.AvionDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AvionMapper {
    public static AvionDTO domainToDTO(Avion avion) {
        return AvionDTO.builder()
                .idAvion(avion.getIdAvion())
                .aerolineaAvion(avion.getAerolineaAvion())
                .estado(avion.getEstado())
                .build();
    }
    public static List<AvionDTO> domainToDTOList(List<Avion> aviones) {
        return aviones.stream().map(avion -> domainToDTO(avion)).collect(Collectors.toList());
    }
    public static Avion dtoToDomain(AvionDTO avionDTO) {
        return Avion.builder()
                .idAvion(avionDTO.getIdAvion())
                .aerolineaAvion(avionDTO.getAerolineaAvion())
                .estado(avionDTO.getEstado())
                .build();
    }
    public static List<Avion> dtoToDomainList(List<AvionDTO> avionesDTO) {
        return avionesDTO.stream().map(avionDTO -> dtoToDomain(avionDTO)).collect(Collectors.toList());
    }
}

