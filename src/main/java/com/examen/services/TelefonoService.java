package com.examen.services;

import java.util.List;

import com.examen.entities.Empleado;
import com.examen.entities.Telefono;

public interface TelefonoService {
    public List<Telefono> findAll(); 
    public Telefono findById(int idTelefono); 
    public void deleteById(int idTelefono); 
    public void save (Telefono telefono); 
    public void deleteByEmpleado (Empleado empleado);
    public List<Telefono> findByEmpleado(Empleado empleado);
}
