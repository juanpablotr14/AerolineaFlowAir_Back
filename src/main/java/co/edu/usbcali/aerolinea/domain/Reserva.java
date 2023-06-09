package co.edu.usbcali.aerolinea.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "reserva")
public class Reserva {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva", nullable = false, unique = true)
    private Integer idReserva;
    @ManyToOne
    @JoinColumn(name = "id_vuelo", referencedColumnName = "id_vuelo")
    private Vuelo idVuelo;
    @ManyToOne
    @JoinColumn(name = "id_asiento", referencedColumnName = "id_asiento")
    private Asiento idAsiento;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario idUsuario;
    @Column(name = "precio_total", nullable = false)
    private long precioTotal;
    @Column(name = "estado_pago", nullable = false, length = 1)
    private String estadoPago;
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @Column(name = "estado", nullable = false, length = 1)
    private String estado;
}
