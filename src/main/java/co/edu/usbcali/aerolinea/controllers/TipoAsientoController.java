package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dto.MensajeDTO;
import co.edu.usbcali.aerolinea.dto.RolUsuarioDTO;
import co.edu.usbcali.aerolinea.dto.TipoAsientoDTO;
import co.edu.usbcali.aerolinea.services.Interfaces.TipoAsientoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoasiento")
@CrossOrigin(origins = "*", methods= { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Slf4j
public class TipoAsientoController {
    private final TipoAsientoService tipoAsientoService;

    public TipoAsientoController(TipoAsientoService tipoAsientoService) {
        this.tipoAsientoService = tipoAsientoService;
    }

    @GetMapping("/obtenerTipoasientos")
    public ResponseEntity<List<TipoAsientoDTO>> obtenerTipoAsientos() {
        return new ResponseEntity(tipoAsientoService.obtenerTipoAsientos(), HttpStatus.OK);
    }
    @PostMapping(path = "/agregarTipoasiento",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarTipoAsiento(@RequestBody TipoAsientoDTO tipoAsientoDTO) {
        try {
            return new ResponseEntity(tipoAsientoService.agregarTipoAsiento(tipoAsientoDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/obtenerTipoasiento/{idTipoa}")
    public ResponseEntity<TipoAsientoDTO> obtenerTipoAsiento(@PathVariable("idTipoa") Integer idTipoa) {
        try {
            return new ResponseEntity(tipoAsientoService.obtenerTipoAsiento(idTipoa), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/obtenerTipoAsientosActivos")
    public ResponseEntity<List<TipoAsientoDTO>> obtenerTipoAsientosActivos() {
        return new ResponseEntity(tipoAsientoService.obtenerTipoAsientosActivos(), HttpStatus.OK);
    }
    @PutMapping(path = "/updateTipoAsiento",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateTipoAsiento(@RequestBody TipoAsientoDTO tipoAsientoDTO) {
        try {
            return new ResponseEntity(tipoAsientoService.updateTipoAsiento(tipoAsientoDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteTipoAsiento/{idTipoAsiento}")
    public ResponseEntity deleteTipoAsiento(@PathVariable("idTipoAsiento") Integer idTipoAsiento) {
        try {
            return new ResponseEntity(tipoAsientoService.deleteTipoAsiento(idTipoAsiento), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

}
