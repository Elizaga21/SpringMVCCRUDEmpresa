package com.examen.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleados")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int id;
    private String nombre;
    @NotNull(message = "El nombre no puede ser Null")

    private String apellidos;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaAlta; 

/** Relación entre tablas
 *  */

 @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
 @JoinColumn(name = "idDepartamento")
 private Departamento departamento;


 @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "empleado")
 private List<Telefono> telefonos;

 @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "empleado")
private List<Correo> correos;

    private Genero genero;


    public enum Genero {
        HOMBRE, MUJER, OTRO
    }


    
}
