package com.examen.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Elisabet Agulló
 */

@Entity
@Table(name = "Departamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private int id;
    private String nombre;

     //Relación entre tablas. MappedBy realiza una relacion bidireccional

     @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "departamento" ) 
     private List<Empleado> empleados;
    
}
