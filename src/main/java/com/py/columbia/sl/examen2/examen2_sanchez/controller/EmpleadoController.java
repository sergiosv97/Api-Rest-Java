
package com.py.columbia.sl.examen2.examen2_sanchez.controller;

import com.py.columbia.sl.examen2.examen2_sanchez.exception.RecursoNoEncontradoException;
import com.py.columbia.sl.examen2.examen2_sanchez.modelo.Empleado;
import com.py.columbia.sl.examen2.examen2_sanchez.repository.EmpleadoRepository;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/empleados"})
public class EmpleadoController {
    private EmpleadoRepository empleadoRepository;
    
    public EmpleadoController(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }
    
    @GetMapping 
    public Page<Empleado> getEmpleados(Pageable pageable) {
        return empleadoRepository.findAll(pageable);
    }
    @PostMapping
    public Empleado crearEmpleado(@Valid @RequestBody Empleado empleado) {
        return empleadoRepository.save(empleado);
        }
    @PutMapping("/{empleadoId}")
    public Empleado actualizarEmpleado(@PathVariable Long empleadoId,
                                    @Valid @RequestBody Empleado empleadoRequest){
          return empleadoRepository.findById(empleadoId)
                  .map(empleadoBD->{
                      empleadoBD.setNombre(empleadoRequest.getNombre());
                      empleadoBD.setApellido(empleadoRequest.getApellido());
                      empleadoBD.setEmail(empleadoRequest.getEmail());
                      return empleadoRepository.save(empleadoBD);
                  }).orElseThrow(() -> new RecursoNoEncontradoException("no existe el empleado con id" +empleadoId));
    }
    @DeleteMapping("/{empleadoId}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Long empleadoId){
        return empleadoRepository.findById(empleadoId)
                .map(empleadoBD ->{
                    empleadoRepository.delete(empleadoBD);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new RecursoNoEncontradoException("no existe el empleado con id"+ empleadoId));
    }

    Object findById(Long empleadoId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}