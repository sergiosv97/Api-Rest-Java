
package com.py.columbia.sl.examen2.examen2_sanchez.repository;

import com.py.columbia.sl.examen2.examen2_sanchez.modelo.Telefono;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefonoRepository extends JpaRepository<Telefono,Long>{
    List <Telefono> findByEmpleadoId(Long id);
    
}
