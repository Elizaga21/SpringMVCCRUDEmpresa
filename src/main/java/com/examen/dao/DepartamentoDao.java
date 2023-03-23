package com.examen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.entities.Departamento;

@Repository
public interface DepartamentoDao extends JpaRepository<Departamento, Integer>{
    
}
