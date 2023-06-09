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
@Table(name = "usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false, unique = true)
    private Integer idUsuario;
    @ManyToOne
    @JoinColumn(name = "id_rolusuario", referencedColumnName = "id_rolusuario")
    private RolUsuario idRolUsuario;
    @Column(name = "cedula", nullable = false, length = 12)
    private String cedula;
    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;
    @Column(name = "apellido", nullable = false, length = 30)
    private String apellido;
    @Column(name = "correo", nullable = false, length = 50)
    private String correo;
    @Column(name = "estado", nullable = false, length = 1)
    private String estado;

}
