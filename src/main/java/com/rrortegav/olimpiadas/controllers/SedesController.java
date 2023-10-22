package com.rrortegav.olimpiadas.controllers;

import com.rrortegav.olimpiadas.models.entities.Sede;
import com.rrortegav.olimpiadas.services.sedes.SedesServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sedes")
public class SedesController{

	@Autowired
	private SedesServiceImpl sedesService;

	private Logger logger = LoggerFactory.getLogger(SedesServiceImpl.class);

	@GetMapping({"/", ""})
	public String listarSedes(Model model){

		logger.info("Listando sedes");

		model.addAttribute("titulo", "Listado de Sedes");
		model.addAttribute("sedes", sedesService.listarSedes());

		return "sedes/sedes";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarSede(Model model, @PathVariable(value = "id") Long id){

		logger.info("Eliminando sede con id: " + id);

		model.addAttribute("titulo", "Listado de Sedes");
		model.addAttribute("sedes", sedesService.eliminarPorId(id));

		logger.info("Sede eliminada con éxito");

		return "redirect:/sedes";
	}

	@GetMapping("/guardar")
	public String guardarSede(Model model){

		model.addAttribute("titulo", "Formulario de Sede");
		model.addAttribute("sede", new Sede());

		return "sedes/guardar";
	}

	@GetMapping("/crear")
	public String guardarSede(Model model, @RequestParam String nombre, @RequestParam int numero_complejos, @PathVariable(value = "id", required = false) Long id){

		Sede sede = new Sede();
		sede.setNombre(nombre);
		sede.setNumero_complejos(numero_complejos);

		sedesService.guardar(sede, id);

		logger.info("Sede guardada con éxito");

		return "redirect:/sedes";
	}
}