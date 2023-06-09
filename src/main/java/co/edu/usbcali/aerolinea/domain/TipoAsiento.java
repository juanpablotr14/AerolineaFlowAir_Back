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
@Table(name = "tipo_asiento")
public class TipoAsiento {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipoa", nullable = false, unique = true)
    private Integer idTipoa;
    @Column(name = "descripcion", nullable = false, length = 30)
    private String descripcion;
    @Column(name = "estado", nullable = false, length = 1)
    private String estado;
}