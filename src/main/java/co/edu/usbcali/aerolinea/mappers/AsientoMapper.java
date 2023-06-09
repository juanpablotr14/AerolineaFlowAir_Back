package co.edu.usbcali.aerolinea.mappers;

import co.edu.usbcali.aerolinea.domain.Aeropuerto;
import co.edu.usbcali.aerolinea.domain.Asiento;
import co.edu.usbcali.aerolinea.dto.AsientoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AsientoMapper {
    public static AsientoDTO domainToDTO(Asiento asiento) {
        return AsientoDTO.builder()
                .idAsiento(asiento.getIdAsiento())
                .idTipoa(asiento.getIdTipoa() != null ? asiento.getIdTipoa().getIdTipoa() : null)
                .idAvion(asiento.getIdAvion() != null ? asiento.getIdAvion().getIdAvion() : null)
                .ubicacion(asiento.getUbicacion())
                .precio(asiento.getPrecio())
                .estado(asiento.getEstado())
                .build();
    }
    public static List<AsientoDTO> domainToDTOList(List<Asiento> asientos) {
        return asientos.stream().map(asiento -> domainToDTO(asiento)).collect(Collectors.toList());
    }
    public static Asiento dtoToDomain(AsientoDTO asientoDTO) {
        return Asiento.builder()
                .idAsiento(asientoDTO.getIdAsiento())
                .ubicacion(asientoDTO.getUbicacion())
                .precio(asientoDTO.getPrecio())
                .estado(asientoDTO.getEstado())
                .build();
    }
    public static List<Asiento> dtoToDomainList(List<AsientoDTO> asientosDTO) {
        return asientosDTO.stream().map(asientoDTO -> dtoToDomain(asientoDTO)).collect(Collectors.toList());
    }
}
