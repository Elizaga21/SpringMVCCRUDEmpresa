package com.examen.services;

import java.util.List;

import com.examen.entities.Departamento;

public interface DepartamentoService {
    public List<Departamento> findAll(); 
    public Departamento findById(int idDepartamento); 
    public void deleteById(int idDepartamento);
    public void save (Departamento departamento); 
}
    

