package com.py.columbia.sl.examen2.examen2_sanchez.controller;

import com.py.columbia.sl.examen2.examen2_sanchez.exception.RecursoNoEncontradoException;
import com.py.columbia.sl.examen2.examen2_sanchez.modelo.Telefono;
import com.py.columbia.sl.examen2.examen2_sanchez.repository.EmpleadoRepository;
import com.py.columbia.sl.examen2.examen2_sanchez.repository.TelefonoRepository;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TelefonoController {

    private TelefonoRepository telefonoRepository;
    private EmpleadoRepository empleadoRepository;

    public TelefonoController(TelefonoRepository telefonoRepository, EmpleadoRepository empleadoRepository) {
        this.telefonoRepository = telefonoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @GetMapping("/empleados/{empleadoId}/telefonos")
    public Page<Telefono> getTelefonos(Pageable pageable) {
        return telefonoRepository.findAll(pageable);
    }

    @PostMapping("/empleados/{empleadoId}/telefonos")
    public Telefono addTelefono(@PathVariable Long empleadoId,
            @Valid @RequestBody Telefono telefono) {
        return empleadoRepository.findById(empleadoId)
                .map(empleado -> {
                    telefono.setEmpleado(empleado);
                    return telefonoRepository.save(telefono);
                }).orElseThrow(() -> new RecursoNoEncontradoException("el empleado no existe con id" + empleadoId));
    }

    @PutMapping("empleados/{empleadoId}/telefonos/{telefonoId}")
    public Telefono updateTelefono(@PathVariable Long empleadoId,
            @PathVariable Long telefonoId,
            @Valid @RequestBody Telefono telefonoRequest) {
        if (!empleadoRepository.existsById(empleadoId)) {
            throw new RecursoNoEncontradoException("Question not found with id" + empleadoId);
        }
        return telefonoRepository.findById(telefonoId)
                .map(telefonoBD -> {
                    telefonoBD.setCodigoarea(telefonoRequest.getCodigoarea());
                    telefonoBD.setNumero(telefonoRequest.getNumero());
                    return telefonoRepository.save(telefonoBD);
                }).orElseThrow(() -> new RecursoNoEncontradoException("El telefono no existe con id" + telefonoId));
    }

    @DeleteMapping("empleados/{empleadoId}/telefonos/{telefonoId}")
    public ResponseEntity<?> deleteTelefono(@PathVariable Long empleadoId,
            @PathVariable Long telefonoId) {
        if (!empleadoRepository.existsById(empleadoId)) {
            throw new RecursoNoEncontradoException("el empleado no existe con id" + empleadoId);
        }
        return telefonoRepository.findById(telefonoId)
                .map(telefonoBD -> {
                    telefonoRepository.delete(telefonoBD);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new RecursoNoEncontradoException("el telefono no existe con id " + telefonoId));
    }

}
