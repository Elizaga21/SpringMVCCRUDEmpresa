package com.examen.services;

import java.util.List;

import com.examen.entities.Empleado;

public interface EmpleadoService {
    public List<Empleado> findAll(); //Busca todo
    public Empleado findById(int idEmpleado); //Busca por Id
    public void deleteById(int idEmpleado); //Borra
    public void save (Empleado empleado); //Guarda y actualiza
    public void delete (Empleado empleado); // Borra
}
