package co.edu.usbcali.aerolinea.mappers;
import co.edu.usbcali.aerolinea.domain.TipoAsiento;
import co.edu.usbcali.aerolinea.dto.TipoAsientoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TipoAsientoMapper {
    public static TipoAsientoDTO domainToDTO(TipoAsiento tipoAsiento) {
        return TipoAsientoDTO.builder()
                .idTipoa(tipoAsiento.getIdTipoa())
                .descripcion(tipoAsiento.getDescripcion())
                .estado(tipoAsiento.getEstado())
                .build();
    }

    public static List<TipoAsientoDTO> domainToDTOList(List<TipoAsiento> tipoAsientos) {
        return tipoAsientos.stream().map(tipoAsiento -> domainToDTO(tipoAsiento)).collect(Collectors.toList());
    }

    public static TipoAsiento dtoToDomain(TipoAsientoDTO tipoAsientoDTO) {
        return TipoAsiento.builder()
                .idTipoa(tipoAsientoDTO.getIdTipoa())
                .descripcion(tipoAsientoDTO.getDescripcion())
                .estado(tipoAsientoDTO.getEstado())
                .build();
    }

    public static List<TipoAsiento> dtoToDomainList(List<TipoAsientoDTO> tipoAsientosDTO) {
        return tipoAsientosDTO.stream().map(tipoAsientoDTO -> dtoToDomain(tipoAsientoDTO)).collect(Collectors.toList());
    }
}
