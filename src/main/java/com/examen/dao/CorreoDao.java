package com.examen.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.entities.Correo;
import com.examen.entities.Empleado;

@Repository
public interface CorreoDao extends JpaRepository <Correo,Integer> {

    long deleteByEmpleado (Empleado empleado);
    
    List <Correo> findByEmpleado (Empleado empleado);
    
}
