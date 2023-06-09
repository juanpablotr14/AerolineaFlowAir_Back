package co.edu.usbcali.aerolinea.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class AvionDTO {
    private Integer idAvion;
    private String aerolineaAvion;
    private String estado;
}
