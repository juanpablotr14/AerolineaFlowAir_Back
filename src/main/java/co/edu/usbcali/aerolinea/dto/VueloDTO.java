package co.edu.usbcali.aerolinea.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class VueloDTO {
    private Integer idVuelo;
    private Integer idAeropuertoOrigen;
    private Integer idAeropuertoDestino;
    private long precio;
    private Date fechaHoraSalida;
    private Date fechaHoraLlegada;
    private long precioAsientoPreferencial;
    private long precioAsientoVip;
    private long precioAsientoTurista;
    private String estado;

}