package co.edu.usbcali.aerolinea.mappers;

import co.edu.usbcali.aerolinea.domain.Aeropuerto;
import co.edu.usbcali.aerolinea.dto.AeropuertoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AeropuertoMapper {
    public static AeropuertoDTO domainToDTO(Aeropuerto aeropuerto) {
        return AeropuertoDTO.builder()
                .idAeropuerto(Math.toIntExact(aeropuerto.getIdAeropuerto()))
                .nombre(aeropuerto.getNombre())
                .iata(aeropuerto.getIata())
                .ubicacion(aeropuerto.getUbicacion())
                .estado(aeropuerto.getEstado())
                .build();
    }
    public static List<AeropuertoDTO> domainToDTOList(List<Aeropuerto> aeropuertos) {
        return aeropuertos.stream().map(aeropuerto -> domainToDTO(aeropuerto)).collect(Collectors.toList());
    }
    public static Aeropuerto dtoToDomain(AeropuertoDTO aeropuertoDTO) {
        return Aeropuerto.builder()
                .idAeropuerto(aeropuertoDTO.getIdAeropuerto())
                .nombre(aeropuertoDTO.getNombre())
                .iata(aeropuertoDTO.getIata())
                .ubicacion(aeropuertoDTO.getUbicacion())
                .estado(aeropuertoDTO.getEstado())
                .build();
    }
    public static List<Aeropuerto> dtoToDomainList(List<AeropuertoDTO> aeropuertosDTO) {
        return aeropuertosDTO.stream().map(aeropuertoDTO -> dtoToDomain(aeropuertoDTO)).collect(Collectors.toList());
    }
}
