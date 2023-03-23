package com.examen;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.examen.entities.Correo;
import com.examen.entities.Departamento;
import com.examen.entities.Empleado;
import com.examen.entities.Telefono;
import com.examen.entities.Empleado.Genero;
import com.examen.services.CorreoService;
import com.examen.services.DepartamentoService;
import com.examen.services.EmpleadoService;
import com.examen.services.TelefonoService;

@SpringBootApplication
public class SpringMvccruDempresaApplication implements CommandLineRunner {

	
	@Autowired
	private DepartamentoService departamentoService;

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private TelefonoService telefonoService;

	@Autowired
	private CorreoService correoService;

	public static void main(String[] args) {
		SpringApplication.run(SpringMvccruDempresaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/**
		 * Agregar registros de muestra para Departamento, Empleado, Telefono y Correo
		 */

		 departamentoService.save(
			Departamento.builder()
			.nombre("RRHH")
			.build());

		departamentoService.save(
		Departamento.builder()
		.nombre("Contabilidad")
		.build());	


		empleadoService.save(
			Empleado.builder()
			.nombre("Juan")
			.apellidos("Martinez")
			.fechaAlta(LocalDate.of(1978, 06, 14))
			.id(1)
			.genero(Genero.HOMBRE)
			.build());

		telefonoService.save(
			Telefono.builder()
			.numero("74673927")
			.id(1)
			.empleado(empleadoService.findById(1))
			.build());
	

		correoService.save(
			Correo.builder()
			.email("juanMartinez.gmail.com")
			.id(1)
			.empleado(empleadoService.findById(1))
			.build());;
	

	}

}
