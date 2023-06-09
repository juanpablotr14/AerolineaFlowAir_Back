package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dto.AsientoDTO;
import co.edu.usbcali.aerolinea.dto.AvionDTO;
import co.edu.usbcali.aerolinea.dto.MensajeDTO;
import co.edu.usbcali.aerolinea.services.Interfaces.AvionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/aviones")
@CrossOrigin(origins = "*", methods= { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Slf4j
public class AvionesController {
    private AvionService avionService;
    public AvionesController(AvionService avionService) {
        this.avionService = avionService;
    }
    @GetMapping("/obtenerAvion/{idAvion}")
    public ResponseEntity<AvionDTO> obtenerAvion(@PathVariable("idAvion") Integer idAvion) {
        try {
            return new ResponseEntity(avionService.obtenerAvion(idAvion), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/obtenerAviones")
    public ResponseEntity<List<AvionDTO>> obtenerAviones() {
        return new ResponseEntity(avionService.obtenerAviones(), HttpStatus.OK);
    }
    @GetMapping("/obtenerAvionesActivos")
    public ResponseEntity<List<AvionDTO>> obtenerAvionesActivos() {
        return new ResponseEntity(avionService.obtenerAvionesActivos(), HttpStatus.OK);
    }
    @PostMapping(path = "/agregarAvion",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity agregarAvion(@RequestBody AvionDTO avionDTO) {
        try {
            return new ResponseEntity(avionService.agregarAvion(avionDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(path = "/updateAvion",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAvion(@RequestBody AvionDTO avionDTO) {
        try {
            return new ResponseEntity(avionService.updateAvion(avionDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteAvion/{idAvion}")
    public ResponseEntity deleteAvion(@PathVariable("idAvion") Integer idAvion) {
        try {
            return new ResponseEntity(avionService.deleteAvion(idAvion), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}

