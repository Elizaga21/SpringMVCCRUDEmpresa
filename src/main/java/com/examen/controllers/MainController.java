package com.examen.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

        List<Departamento> departamentos = departamentoService.findAll(); // Se añade listado de facultades para el formulario

        Empleado empleado = new Empleado();

        model.addAttribute("empleado", new Empleado()); // esta vacio el parametro porque en el formulario se
                                                            // rellena
        model.addAttribute("departamentos", departamentos);

        return "views/formularioAltaEmpleado";
        
    }


        /**
     * Método que recibe los datos procedentes de los controles del formulario
     * 
     */

    @PostMapping("/altaModificacionEmpleado")
    public String altaEmpleado (@ModelAttribute Empleado empleado,
            @RequestParam (name = "numerosTelefonos") String telefonosRecibidos) { 

        LOG.info("Telefonos recibidos: " + telefonosRecibidos);

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

        }

        return "redirect:/listar";
      



    

    }
    
}
