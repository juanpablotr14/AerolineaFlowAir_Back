package co.edu.usbcali.aerolinea.services.Implements;

import co.edu.usbcali.aerolinea.domain.Aeropuerto;
import co.edu.usbcali.aerolinea.dto.AeropuertoDTO;
import co.edu.usbcali.aerolinea.mappers.AeropuertoMapper;
import co.edu.usbcali.aerolinea.repository.AeropuertoRepository;
import co.edu.usbcali.aerolinea.services.Interfaces.AeropuertoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Service
public class AeropuertoServiceImpl implements AeropuertoService {
    private final AeropuertoRepository aeropuertoRepository;
    private final ModelMapper modelMapper;
    public AeropuertoServiceImpl(AeropuertoRepository aeropuertoRepository, ModelMapper modelMapper) {
        this.aeropuertoRepository = aeropuertoRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public AeropuertoDTO agregarAeropuerto(AeropuertoDTO aeropuertoDTO) throws Exception {
        if (aeropuertoDTO == null) {
            throw new Exception("El aeropuerto no puede ser nulo");
        }
        if (aeropuertoDTO.getIdAeropuerto() == null || aeropuertoDTO.getIdAeropuerto() == null) {
            throw new Exception("El id del aeropuerto es obligatorio!");
        }
        if (aeropuertoDTO.getNombre() == null || aeropuertoDTO.getNombre().isBlank() || aeropuertoDTO.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del aeropuerto es invalido!");
        }
        if (aeropuertoDTO.getIata() == null || aeropuertoDTO.getIata().isBlank() || aeropuertoDTO.getIata().trim().isEmpty()) {
            throw new Exception("El IATA es invalido!");
        }
        if (aeropuertoDTO.getUbicacion() == null || aeropuertoDTO.getUbicacion().isBlank() || aeropuertoDTO.getUbicacion().trim().isEmpty()) {
            throw new Exception("La ubicación es invalida!");
        }
        if (aeropuertoDTO.getEstado() == null || aeropuertoDTO.getEstado().isBlank() || aeropuertoDTO.getEstado().trim().isEmpty()) {
            throw new Exception("El estado es invalido!");
        }
        if(aeropuertoRepository.findById(aeropuertoDTO.getIdAeropuerto()).isPresent()){
            throw new Exception("Ya existe el id del aeropuerto!");
        }
        Aeropuerto aeropuerto = AeropuertoMapper.dtoToDomain(aeropuertoDTO);
        return AeropuertoMapper.domainToDTO(aeropuertoRepository.save(aeropuerto));
    }
    @Override
    public List<AeropuertoDTO> obtenerAeropuertosActivos() {
        return AeropuertoMapper.domainToDTOList(aeropuertoRepository.findAllByEstado("A"));
    }

    @Override
    public AeropuertoDTO updateAeropuerto(AeropuertoDTO aeropuertoDTO) throws Exception {
        if (aeropuertoDTO == null) {
            throw new Exception("El aeropuerto no puede ser nulo");
        }
        if (aeropuertoDTO.getNombre() == null || aeropuertoDTO.getNombre().isBlank() || aeropuertoDTO.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del aeropuerto es invalido!");
        }
        if (aeropuertoDTO.getIata() == null || aeropuertoDTO.getIata().isBlank() || aeropuertoDTO.getIata().trim().isEmpty()) {
            throw new Exception("El IATA es invalido!");
        }
        if (aeropuertoDTO.getUbicacion() == null || aeropuertoDTO.getUbicacion().isBlank() || aeropuertoDTO.getUbicacion().trim().isEmpty()) {
            throw new Exception("La ubicación es invalida!");
        }
        if (aeropuertoDTO.getEstado() == null || aeropuertoDTO.getEstado().isBlank() || aeropuertoDTO.getEstado().trim().isEmpty()) {
            throw new Exception("El estado es invalido!");
        }

        Aeropuerto aeropuerto = AeropuertoMapper.dtoToDomain(aeropuertoDTO);
        return AeropuertoMapper.domainToDTO(aeropuertoRepository.save(aeropuerto));
    }

    @Override
    public AeropuertoDTO deleteAeropuerto(Integer id) throws Exception {

        Optional<Aeropuerto> aeropuertoOptional = aeropuertoRepository.findById(id);

        if (aeropuertoOptional.isPresent()) {

            Aeropuerto aeropuerto = aeropuertoOptional.get();
            aeropuertoRepository.deleteById(id);

            return AeropuertoMapper.domainToDTO(aeropuerto);
        } else {
            throw new Exception("No se encontró el aeropuerto con ese id!");
        }
    }

    @Override
    public AeropuertoDTO obtenerAeropuerto(Integer id) throws Exception {
        if (!aeropuertoRepository.existsById(id)) {
            throw new Exception("El id " + id + " no corresponde a ningun aeropuerto!");
        }
        return AeropuertoMapper.domainToDTO(aeropuertoRepository.getReferenceById(id));
    }
    @Override
    public List<AeropuertoDTO> obtenerAeropuertos() {
        return AeropuertoMapper.domainToDTOList(aeropuertoRepository.findAll());
    }
}
