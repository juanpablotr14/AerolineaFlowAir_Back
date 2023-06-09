package co.edu.usbcali.aerolinea.mappers;

import co.edu.usbcali.aerolinea.domain.Vuelo;
import co.edu.usbcali.aerolinea.dto.VueloDTO;

import java.util.List;
import java.util.stream.Collectors;

public class VueloMapper {
    public static VueloDTO domainToDTO(Vuelo vuelo) {
        return VueloDTO.builder()
                .idVuelo(vuelo.getIdVuelo())
                .idAeropuertoOrigen(Math.toIntExact(vuelo.getIdAeropuertoOrigen() != null ? vuelo.getIdAeropuertoOrigen().getIdAeropuerto() : null))
                .idAeropuertoDestino(Math.toIntExact(vuelo.getIdAeropuertoDestino() != null ? vuelo.getIdAeropuertoDestino().getIdAeropuerto() : null))
                .precio(vuelo.getPrecio())
                .fechaHoraSalida(vuelo.getFechaHoraSalida())
                .fechaHoraLlegada(vuelo.getFechaHoraLlegada())
                .precioAsientoPreferencial(vuelo.getPrecioAsientoPreferencial())
                .precioAsientoVip(vuelo.getPrecioAsientoVip())
                .precioAsientoTurista(vuelo.getPrecioAsientoTurista())
                .estado(vuelo.getEstado())
                .build();
    }

    public static List<VueloDTO> domainToDTOList(List<Vuelo> vuelos) {
        return vuelos.stream().map(vuelo -> domainToDTO(vuelo)).collect(Collectors.toList());
    }

    public static Vuelo dtoToDomain(VueloDTO vueloDTO) {
        return Vuelo.builder()
                .idVuelo(vueloDTO.getIdVuelo())
                .precio(vueloDTO.getPrecio())
                .fechaHoraSalida(vueloDTO.getFechaHoraSalida())
                .fechaHoraLlegada(vueloDTO.getFechaHoraLlegada())
                .precioAsientoPreferencial(vueloDTO.getPrecioAsientoPreferencial())
                .precioAsientoVip(vueloDTO.getPrecioAsientoVip())
                .precioAsientoTurista(vueloDTO.getPrecioAsientoTurista())
                .estado(vueloDTO.getEstado())
                .build();
    }

    public static List<Vuelo> dtoToDomainList(List<VueloDTO> vuelosDTO) {
        return vuelosDTO.stream().map(vueloDTO -> dtoToDomain(vueloDTO)).collect(Collectors.toList());
    }
}
