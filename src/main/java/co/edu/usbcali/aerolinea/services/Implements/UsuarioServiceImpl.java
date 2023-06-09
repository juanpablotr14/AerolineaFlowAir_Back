package co.edu.usbcali.aerolinea.services.Implements;

import co.edu.usbcali.aerolinea.domain.Aeropuerto;
import co.edu.usbcali.aerolinea.domain.RolUsuario;
import co.edu.usbcali.aerolinea.domain.TipoAsiento;
import co.edu.usbcali.aerolinea.domain.Usuario;
import co.edu.usbcali.aerolinea.dto.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dto.UsuarioDTO;
import co.edu.usbcali.aerolinea.mappers.AeropuertoMapper;
import co.edu.usbcali.aerolinea.mappers.UsuarioMapper;
import co.edu.usbcali.aerolinea.repository.RolUsuarioRepository;
import co.edu.usbcali.aerolinea.repository.UsuarioRepository;
import co.edu.usbcali.aerolinea.services.Interfaces.UsuarioService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RolUsuarioRepository rolUsuarioRepository;
    private final ModelMapper modelMapper;
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RolUsuarioRepository rolUsuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.rolUsuarioRepository = rolUsuarioRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public UsuarioDTO agregarUsuario(UsuarioDTO usuarioDTO) throws Exception {
        if (usuarioDTO == null) {
            throw new Exception("El usuario es invalido!");
        }
        if (usuarioDTO.getCedula() == null || usuarioDTO.getCedula().isBlank() || !usuarioDTO.getCedula().matches("\\d+") || usuarioDTO.getCedula().trim().isEmpty()) {
            throw new Exception("La cédula del usuario es inválida!");
        }
        if (usuarioDTO.getNombre() == null || usuarioDTO.getNombre().isBlank() || usuarioDTO.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del usuario es invalido!");
        }
        if (usuarioDTO.getApellido() == null || usuarioDTO.getApellido().isBlank() || usuarioDTO.getApellido().trim().isEmpty()) {
            throw new Exception("El apellido del usuario es invalido!");
        }
        if (usuarioDTO.getCorreo() == null || usuarioDTO.getCorreo().isBlank() || usuarioDTO.getCorreo().trim().isEmpty()) {
            throw new Exception("El correo del usuario es invalido!");
        }
        if (usuarioDTO.getEstado() == null || usuarioDTO.getEstado().isBlank() || usuarioDTO.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del usuario es invalido!");
        }
        if(usuarioRepository.findById(usuarioDTO.getIdUsuario()).isPresent()){
            throw new Exception("Ya existe el id del usuario!");
        }
        Usuario usuario = UsuarioMapper.dtoToDomain(usuarioDTO);

        RolUsuario rolUsuario = rolUsuarioRepository.getReferenceById(usuarioDTO.getIdRolusuario());
        usuario.setIdRolUsuario(rolUsuario);

        return UsuarioMapper.domainToDTO(usuarioRepository.save(usuario));
    }
    @Override
    public List<UsuarioDTO> obtenerUsuarios() {
        return UsuarioMapper.domainToDTOList(usuarioRepository.findAll());
    }
    @Override
    public UsuarioDTO obtenerUsuario(Integer id) throws Exception {
        if (!usuarioRepository.existsById(id)) {
            throw new Exception("El id " + id + " no corresponde a ningun usuario!");
        }

        return UsuarioMapper.domainToDTO(usuarioRepository.getReferenceById(id));
    }
    @Override
    public List<UsuarioDTO> obtenerUsuariosActivos() {
        return UsuarioMapper.domainToDTOList(usuarioRepository.findAllByEstado("A"));
    }

    @Override
    public UsuarioDTO updateUsuario(UsuarioDTO usuarioDTO) throws Exception {
        if (usuarioDTO == null) {
            throw new Exception("El usuario es invalido!");
        }
        if (usuarioDTO.getCedula() == null || usuarioDTO.getCedula().isBlank() || usuarioDTO.getNombre().trim().isEmpty()) {
            throw new Exception("La cédula del usuario es invalida!");
        }
        if (usuarioDTO.getNombre() == null || usuarioDTO.getNombre().isBlank() || usuarioDTO.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del usuario es invalido!");
        }
        if (usuarioDTO.getApellido() == null || usuarioDTO.getApellido().isBlank() || usuarioDTO.getApellido().trim().isEmpty()) {
            throw new Exception("El apellido del usuario es invalido!");
        }
        if (usuarioDTO.getCorreo() == null || usuarioDTO.getCorreo().isBlank() || usuarioDTO.getCorreo().trim().isEmpty()) {
            throw new Exception("El correo del usuario es invalido!");
        }
        if (usuarioDTO.getEstado() == null || usuarioDTO.getEstado().isBlank() || usuarioDTO.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del usuario es invalido!");
        }

        Usuario usuario = UsuarioMapper.dtoToDomain(usuarioDTO);

        RolUsuario rolUsuario = rolUsuarioRepository.getReferenceById(usuarioDTO.getIdRolusuario());
        usuario.setIdRolUsuario(rolUsuario);

        return UsuarioMapper.domainToDTO(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDTO deleteUsuario(Integer id) throws Exception {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {

            Usuario usuario = usuarioOptional.get();
            usuarioRepository.deleteById(id);

            return UsuarioMapper.domainToDTO(usuario);
        } else {
            throw new Exception("No se encontró el usuario con ese id!");
        }
    }
}
