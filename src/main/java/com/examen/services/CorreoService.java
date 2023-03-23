package com.examen.services;

import java.util.List;

import com.examen.entities.Correo;
import com.examen.entities.Empleado;

public interface CorreoService {
    public List<Correo> findAll(); 
    public Correo findById(int idCorreo); 
    public void deleteById(int idCorreo); 
    public void save (Correo correo); 
    public void deleteByEmpleado (Empleado empleado);
    public List<Correo> findByEmpleado(Empleado empleado);
}
