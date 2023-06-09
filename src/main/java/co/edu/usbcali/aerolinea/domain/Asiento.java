package co.edu.usbcali.aerolinea.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "asiento")
public class Asiento {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asiento", nullable = false, unique = true)
    private Integer idAsiento;
    @ManyToOne
    @JoinColumn(name = "id_tipoa", referencedColumnName = "id_tipoa")
    private TipoAsiento idTipoa;
    @ManyToOne
    @JoinColumn(name = "id_avion", referencedColumnName = "id_avion")
    private Avion idAvion;
    @Column(name = "ubicacion", nullable = false, length = 80)
    private String ubicacion;
    @Column(name = "precio", nullable = false)
    private long precio;
    @Column(name = "estado", nullable = false, length = 1)
    private String estado;
}
