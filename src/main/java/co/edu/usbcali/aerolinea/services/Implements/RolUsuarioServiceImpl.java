package co.edu.usbcali.aerolinea.services.Implements;

import co.edu.usbcali.aerolinea.domain.Aeropuerto;
import co.edu.usbcali.aerolinea.dto.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dto.RolUsuarioDTO;
import co.edu.usbcali.aerolinea.domain.RolUsuario;
import co.edu.usbcali.aerolinea.mappers.AeropuertoMapper;
import co.edu.usbcali.aerolinea.mappers.RolUsuarioMapper;
import co.edu.usbcali.aerolinea.repository.RolUsuarioRepository;
import co.edu.usbcali.aerolinea.services.Interfaces.RolUsuarioService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RolUsuarioServiceImpl implements RolUsuarioService {
    private final RolUsuarioRepository rolUsuarioRepository;
    private final ModelMapper modelMapper;
    public RolUsuarioServiceImpl(RolUsuarioRepository rolUsuarioRepository, ModelMapper modelMapper) {
        this.rolUsuarioRepository = rolUsuarioRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<RolUsuarioDTO> obtenerRolUsuarios() {
        List<RolUsuario> rolUsuarios = rolUsuarioRepository.findAll();
        return RolUsuarioMapper.domainToDtoList(rolUsuarioRepository.findAll());
    }
    @Override
    public RolUsuarioDTO obtenerRolUsuario(Integer id) throws Exception {
        if (!rolUsuarioRepository.existsById(id)) {
            throw new Exception("El id " + id + " no corresponde a ningun usuario!");
        }
        return RolUsuarioMapper.domainToDto(rolUsuarioRepository.getReferenceById(id));
    }
    @Override
    public RolUsuarioDTO guardarRolUsuario(RolUsuarioDTO rolUsuarioDTO) throws Exception {
        if(rolUsuarioDTO == null){
            throw new Exception("Rol del usuario no valido!");
        }
        if(rolUsuarioDTO.getIdRolusuario() == null || rolUsuarioDTO.getIdRolusuario() < 0){
            throw new Exception("Id del usuario no valido!");
        }
        if(rolUsuarioDTO.getDescripcion() == null || rolUsuarioDTO.getDescripcion().trim().isEmpty()){
            throw new Exception("Descripcion no valida!");
        }
        if( rolUsuarioDTO.getEstado() == null || rolUsuarioDTO.getEstado().trim().isEmpty()){
            throw new Exception("El estado es invalido");
        }
        if(rolUsuarioRepository.findById(rolUsuarioDTO.getIdRolusuario()).isPresent()){
            throw new Exception("Ya existe el id del usuario!");
        }

        RolUsuario rolUsuario = RolUsuarioMapper.dtoToDomain((rolUsuarioDTO));
        return RolUsuarioMapper.domainToDto(rolUsuarioRepository.save(rolUsuario));
    }
    @Override
    public List<RolUsuarioDTO> obtenerRolUsuariosActivos() {
        return RolUsuarioMapper.domainToDtoList(rolUsuarioRepository.findAllByEstado("A"));
    }

    @Override
    public RolUsuarioDTO updateRolUsuario(RolUsuarioDTO rolUsuarioDTO) throws Exception {
        if(rolUsuarioDTO == null){
            throw new Exception("Rol del usuario no valido!");
        }
        if(rolUsuarioDTO.getIdRolusuario() == null || rolUsuarioDTO.getIdRolusuario() < 0){
            throw new Exception("Id del usuario no valido!");
        }
        if(rolUsuarioDTO.getDescripcion() == null || rolUsuarioDTO.getDescripcion().trim().isEmpty()){
            throw new Exception("Descripcion no valida!");
        }
        if( rolUsuarioDTO.getEstado() == null || rolUsuarioDTO.getEstado().trim().isEmpty()){
            throw new Exception("El estado es invalido");
        }

        RolUsuario rolUsuario = RolUsuarioMapper.dtoToDomain((rolUsuarioDTO));
        return RolUsuarioMapper.domainToDto(rolUsuarioRepository.save(rolUsuario));
    }

    @Override
    public RolUsuarioDTO deleteRolUsuario(Integer id) throws Exception {
        Optional<RolUsuario> rolUsuarioOptional = rolUsuarioRepository.findById(id);

        if (rolUsuarioOptional.isPresent()) {

            RolUsuario rolUsuario = rolUsuarioOptional.get();
            rolUsuarioRepository.deleteById(id);

            return RolUsuarioMapper.domainToDto(rolUsuario);
        } else {
            throw new Exception("No se encontró el rol usuario con ese id!");
        }
    }
}
