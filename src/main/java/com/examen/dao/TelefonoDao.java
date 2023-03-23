package com.examen.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.entities.Empleado;
import com.examen.entities.Telefono;

@Repository
public interface TelefonoDao extends JpaRepository<Telefono, Integer> {
    long deleteByEmpleado (Empleado empleado);
    
    List <Telefono> findByEmpleado (Empleado empleado);
  
    
}
