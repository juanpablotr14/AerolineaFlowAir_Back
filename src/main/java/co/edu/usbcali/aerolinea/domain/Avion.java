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
@Table(name = "avion")
public class Avion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avion", nullable = false, unique = true)
    private Integer idAvion;
    @Column(name = "aerolinea_avion", nullable = false, length = 30)
    private String aerolineaAvion;
    @Column(name = "estado", nullable = false, length = 1)
    private String estado;
}