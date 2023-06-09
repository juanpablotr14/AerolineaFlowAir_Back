package co.edu.usbcali.aerolinea.services.Implements;

import co.edu.usbcali.aerolinea.domain.Aeropuerto;
import co.edu.usbcali.aerolinea.domain.TipoAsiento;
import co.edu.usbcali.aerolinea.dto.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dto.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.mappers.AeropuertoMapper;
import co.edu.usbcali.aerolinea.mappers.TipoAsientoMapper;
import co.edu.usbcali.aerolinea.repository.TipoAsientoRepository;
import co.edu.usbcali.aerolinea.services.Interfaces.TipoAsientoService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TipoAsientoServiceImpl implements TipoAsientoService {
    private final TipoAsientoRepository tipoAsientoService;
    private final TipoAsientoRepository tipoAsientoRepository;
    private final ModelMapper modelMapper;
    public TipoAsientoServiceImpl(TipoAsientoRepository tipoAsientoService, TipoAsientoRepository tipoAsientoRepository, ModelMapper modelMapper) {
        this.tipoAsientoService = tipoAsientoService;
        this.tipoAsientoRepository = tipoAsientoRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<TipoAsientoDTO> obtenerTipoAsientos() {
        return TipoAsientoMapper.domainToDTOList(tipoAsientoService.findAll());
    }
    @Override
    public TipoAsientoDTO agregarTipoAsiento(TipoAsientoDTO tipoAsientoDTO) throws Exception {
        if (tipoAsientoDTO == null) {
            throw new Exception("El tipo de asiento es invalido!");
        }
        if (tipoAsientoDTO.getIdTipoa() == null || tipoAsientoDTO.getIdTipoa() < 0) {
            throw new Exception("El id del tipo de asiento es invalida!");
        }
        if (tipoAsientoDTO.getDescripcion() == null || tipoAsientoDTO.getDescripcion().isBlank() || tipoAsientoDTO.getDescripcion().trim().isEmpty()) {
            throw new Exception("La descripción del tipo de asiento es invalida!");
        }
        if (tipoAsientoDTO.getEstado() == null || tipoAsientoDTO.getEstado().isBlank() || tipoAsientoDTO.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del tipo de asiento es invalido!");
        }
        if(tipoAsientoService.findById(tipoAsientoDTO.getIdTipoa()).isPresent()){
            throw new Exception("Ya existe el id del asiento!");
        }
        TipoAsiento tipoAsiento = TipoAsientoMapper.dtoToDomain(tipoAsientoDTO);
        return TipoAsientoMapper.domainToDTO(tipoAsientoService.save(tipoAsiento));
    }
    @Override
    public TipoAsientoDTO obtenerTipoAsiento(Integer id) throws Exception {
        if (!tipoAsientoService.existsById(id)) {
            throw new Exception("El id " + id + " no corresponde a ningun tipo de asiento!");
        }
        return TipoAsientoMapper.domainToDTO(tipoAsientoService.getReferenceById(id));
    }
    @Override
    public List<TipoAsientoDTO> obtenerTipoAsientosActivos() {
        return TipoAsientoMapper.domainToDTOList(tipoAsientoRepository.findAllByEstado("A"));
    }

    @Override
    public TipoAsientoDTO updateTipoAsiento(TipoAsientoDTO tipoAsientoDTO) throws Exception {
        if (tipoAsientoDTO == null) {
            throw new Exception("El tipo de asiento es invalido!");
        }
        if (tipoAsientoDTO.getIdTipoa() == null || tipoAsientoDTO.getIdTipoa() < 0) {
            throw new Exception("El id del tipo de asiento es invalida!");
        }
        if (tipoAsientoDTO.getDescripcion() == null || tipoAsientoDTO.getDescripcion().isBlank() || tipoAsientoDTO.getDescripcion().trim().isEmpty()) {
            throw new Exception("La descripción del tipo de asiento es invalida!");
        }
        if (tipoAsientoDTO.getEstado() == null || tipoAsientoDTO.getEstado().isBlank() || tipoAsientoDTO.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del tipo de asiento es invalido!");
        }

        TipoAsiento tipoAsiento = TipoAsientoMapper.dtoToDomain(tipoAsientoDTO);
        return TipoAsientoMapper.domainToDTO(tipoAsientoService.save(tipoAsiento));
    }

    @Override
    public TipoAsientoDTO deleteTipoAsiento(Integer id) throws Exception {
        Optional<TipoAsiento> tipoAsientoOptional = tipoAsientoService.findById(id);

        if (tipoAsientoOptional.isPresent()) {

            TipoAsiento tipoAsiento = tipoAsientoOptional.get();
            tipoAsientoService.deleteById(id);

            return TipoAsientoMapper.domainToDTO(tipoAsiento);
        } else {
            throw new Exception("No se encontró el tipo asiento con ese id!");
        }
    }
}
