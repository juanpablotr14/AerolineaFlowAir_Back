package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dto.*;
import co.edu.usbcali.aerolinea.services.Interfaces.AeropuertoService;
import co.edu.usbcali.aerolinea.services.Interfaces.RolUsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/rolUsuario")
@CrossOrigin(origins = "*", methods= { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Slf4j
public class RolUsuarioController {
    private RolUsuarioService rolUsuarioService;
    public RolUsuarioController(RolUsuarioService rolUsuarioService) {
        this.rolUsuarioService = rolUsuarioService;
    }
    @GetMapping("/obtenerTodos")
    public ResponseEntity<List<RolUsuarioDTO>> obtenerTodos() throws Exception {
        return new ResponseEntity(rolUsuarioService.obtenerRolUsuarios(), HttpStatus.OK);
    }
    @GetMapping("/obtenerRolUsuario/{idRolusuario}")
    public ResponseEntity<RolUsuarioDTO> obtenerRolUsuario(@PathVariable("idRolusuario") Integer idRolusuario) {
        try {
            return new ResponseEntity(rolUsuarioService.obtenerRolUsuario(idRolusuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/obtenerRolUsuariosActivos")
    public ResponseEntity<List<RolUsuarioDTO>> obtenerRolUsuariosActivos() {
        return new ResponseEntity(rolUsuarioService.obtenerRolUsuariosActivos(), HttpStatus.OK);
    }
    @PostMapping(path = "/guardarRolUsuario",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarRolUsuario(@RequestBody RolUsuarioDTO rolUsuarioDTO) {
        try {
            return new ResponseEntity(rolUsuarioService.guardarRolUsuario(rolUsuarioDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(path = "/updateRolUsuario",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateRolUsuario(@RequestBody RolUsuarioDTO rolUsuarioDTO) {
        try {
            return new ResponseEntity(rolUsuarioService.updateRolUsuario(rolUsuarioDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteRolUsuario/{idRolUsuario}")
    public ResponseEntity deleteRolUsuario(@PathVariable("idRolUsuario") Integer idRolUsuario) {
        try {
            return new ResponseEntity(rolUsuarioService.deleteRolUsuario(idRolUsuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
