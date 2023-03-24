package com.examen.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.examen.entities.Correo;
import com.examen.entities.Departamento;
import com.examen.entities.Empleado;
import com.examen.entities.Telefono;
import com.examen.services.CorreoService;
import com.examen.services.DepartamentoService;
import com.examen.services.EmpleadoService;
import com.examen.services.TelefonoService;

@Controller
@RequestMapping("/") 
public class MainController {

    private static final Logger LOG = Logger.getLogger("MainController");


    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private TelefonoService telefonoService;

    @Autowired
    private CorreoService correoService;

    @GetMapping("/listar")
    public ModelAndView listar() { 

        List<Empleado> empleados = empleadoService.findAll();


        ModelAndView mav = new ModelAndView("views/listarEmpleados");
        mav.addObject("empleados", empleados);
        

        return mav;
    }

    /**
     * Formulario de Empleados
     * 
     * 
     */
    @GetMapping("/frmAltaEmpleado")
    public String formularioAltaEmpleado(Model model) {

        List<Departamento> departamentos = departamentoService.findAll(); 
        Empleado empleado = new Empleado();

        model.addAttribute("empleado", new Empleado()); 
        model.addAttribute("departamentos", departamentos);

        return "views/formularioAltaEmpleado";

    }
        /**
     * Método que recibe los datos procedentes de los controles del formulario
     * 
     */

    @PostMapping("/altaModificacionEmpleado")
    public String altaEmpleado (@ModelAttribute Empleado empleado,
            @RequestParam (name = "numerosTelefonos") String telefonosRecibidos,
            @RequestParam (name = "correosEmpleados") String correosRecibidos) { 

        LOG.info("Telefonos recibidos: " + telefonosRecibidos);
        LOG.info("Correos recibidos: " + correosRecibidos);


        empleadoService.save(empleado);

        List<String> listadoNumerosTelefonos = null; 

        if (telefonosRecibidos != null) { 

            String[] arrayTelefonos = telefonosRecibidos.split("/");

            listadoNumerosTelefonos = Arrays.asList(arrayTelefonos);
        }
     

        if (listadoNumerosTelefonos != null) {
            telefonoService.deleteByEmpleado(empleado); 
            listadoNumerosTelefonos.stream().forEach(n -> {
                Telefono telefonoObject = Telefono
                        .builder()
                        .numero(n)
                        .empleado(empleado)
                        .build();

                telefonoService.save(telefonoObject); 
            });


        List<String> listadoCorreos = null; 

        if (correosRecibidos != null) { 

            String[] arrayCorreos = correosRecibidos.split("/");

            listadoCorreos = Arrays.asList(arrayCorreos);
        }

        if (listadoCorreos != null) {
            correoService.deleteByEmpleado(empleado); 
            listadoCorreos.stream().forEach(e -> {
                Correo correoObject = Correo
                        .builder()
                        .email(e)
                        .empleado(empleado)
                        .build();

                correoService.save(correoObject); 
            });

        }

        return "redirect:/listar";

    }
        return correosRecibidos;

            }
        /**
     * Actualiza un empleado
     */

    @GetMapping("/frmActualizar/{id}")
    public String frmActualizarEmpleado(@PathVariable(name = "id") int idEmpleado, Model model) { 

        Empleado empleado = empleadoService.findById(idEmpleado);
        List<Telefono> todosTelefonos = telefonoService.findAll();
        List<Correo> todosCorreos = correoService.findAll();
        
        List<Telefono> telefonosDelEmpleado = todosTelefonos.stream()
                .filter(telefono -> telefono.getEmpleado().getId() == idEmpleado)
                .collect(Collectors.toList());

        List<Correo> correosDelEmpleado = todosCorreos.stream()
        .filter(correo -> correo.getEmpleado().getId() == idEmpleado)
        .collect(Collectors.toList());

        String numerosDeTelefono = telefonosDelEmpleado.stream()
                .map(telefono -> telefono.getNumero()).collect(Collectors.joining(";"));

        String correos = correosDelEmpleado.stream().map(correo -> correo.getEmail())
        .collect(Collectors.joining(","));       

        List<Departamento> departamentos = departamentoService.findAll();

        // Model se utiliza para rellenar el formulario con datos
        model.addAttribute("empleado", empleado);
        model.addAttribute("telefonos", numerosDeTelefono);
        model.addAttribute("correos", correos);
        model.addAttribute("departamentos", departamentos);

        return "views/formularioAltaEmpleado";
    }

    /**
     * Método de borrar estudiante
     */

    @GetMapping("/borrar/{id}")
    public String borrarEmpleado(@PathVariable(name = "id") int idEmpleado) {

        empleadoService.delete(empleadoService.findById(idEmpleado));

        return "redirect:/listar";
    }

     /**
         * Método que muestra los detalles
         */

         @GetMapping("/detalles/{id}") 
         public String detallesEmpleado(@PathVariable(name = "id") int idEmpleado, Model model) {
            
            Empleado empleado = empleadoService.findById(idEmpleado);
    
            List<Telefono> telefonos = telefonoService.findByEmpleado(empleado);

            List<Correo> correos = correoService.findByEmpleado(empleado);
    
            List<String> numerosTelefonos = telefonos.stream()
            .map(t -> t.getNumero())
            .collect(Collectors.toList());

            List<String> correosEmpleado = correos.stream().map(c -> c.getEmail())
            .collect(Collectors.toList());
    
            model.addAttribute("empleado", empleado);
            model.addAttribute("telefonos", numerosTelefonos);
            model.addAttribute("correos", correosEmpleado);
    
            return "views/empleadoDetalles";
    
    
}
}