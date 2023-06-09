package co.edu.usbcali.aerolinea.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "vuelo")
public class Vuelo {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vuelo", nullable = false, unique = true)
    private Integer idVuelo;
    @ManyToOne
    @JoinColumn(name = "id_aeropuerto_origen", referencedColumnName = "id_aeropuerto")
    private Aeropuerto idAeropuertoOrigen;
    @ManyToOne
    @JoinColumn(name = "id_aeropuerto_destino", referencedColumnName = "id_aeropuerto")
    private Aeropuerto idAeropuertoDestino;
    @Column(name = "precio", nullable = false)
    private long precio;
    @Column(name = "hora_salida", nullable = false)
    private Date fechaHoraSalida;
    @Column(name = "hora_llegada", nullable = false)
    private Date fechaHoraLlegada;
    @Column(name = "precio_asiento_preferencial", nullable = false)
    private long precioAsientoPreferencial;
    @Column(name = "precio_asiento_vip", nullable = false)
    private long precioAsientoVip;
    @Column(name = "precio_asiento_turista", nullable = false)
    private long precioAsientoTurista;
    @Column(name = "estado", nullable = false, length = 1)
    private String estado;
}
