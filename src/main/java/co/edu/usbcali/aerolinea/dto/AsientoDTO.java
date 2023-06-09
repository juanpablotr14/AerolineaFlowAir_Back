package co.edu.usbcali.aerolinea.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class AsientoDTO {
    private Integer idAsiento;
    private Integer idTipoa;
    private Integer idAvion;
    private String ubicacion;
    private long precio;
    private String estado;

}