package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dto.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dto.AsientoDTO;
import co.edu.usbcali.aerolinea.dto.MensajeDTO;
import co.edu.usbcali.aerolinea.services.Interfaces.AsientoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asiento")
@CrossOrigin(origins = "*", methods= { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE  })
@Slf4j
public class AsientoController {
    private final AsientoService asientoService;
    public AsientoController(AsientoService asientoService) {
        this.asientoService = asientoService;
    }
    @GetMapping("/obtenerAsientos")
    public ResponseEntity<List<AsientoDTO>> obtenerAsientos() {
        return new ResponseEntity(asientoService.obtenerAsientos(), HttpStatus.OK);
    }
    @GetMapping("/obtenerAsiento/{idAsiento}")
    public ResponseEntity<AsientoDTO> obtenerAsiento(@PathVariable("idAsiento") Integer idAsiento) {
        try {
            return new ResponseEntity(asientoService.obtenerAsiento(idAsiento), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/obtenerAsientosActivos")
    public ResponseEntity<List<AsientoDTO>> obtenerAsientosActivos() {
        return new ResponseEntity(asientoService.obtenerAsientosActivos(), HttpStatus.OK);
    }
    @PostMapping(path = "/agregarAsiento",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarAsiento(@RequestBody AsientoDTO asientoDTO) {
        try {
            return new ResponseEntity(asientoService.agregarAsiento(asientoDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(path = "/updateAsiento",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAsiento(@RequestBody AsientoDTO asientoDTO) {
        try {
            return new ResponseEntity(asientoService.updateAsiento(asientoDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteAsiento/{idAsiento}")
    public ResponseEntity deleteAsiento(@PathVariable("idAsiento") Integer idAsiento) {
        try {
            return new ResponseEntity(asientoService.deleteAsiento(idAsiento), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
