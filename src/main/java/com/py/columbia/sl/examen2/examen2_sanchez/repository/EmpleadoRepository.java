
package com.py.columbia.sl.examen2.examen2_sanchez.repository;

import com.py.columbia.sl.examen2.examen2_sanchez.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Long>{
    
}