package com.rrortegav.olimpiadas.services.sedes;


import com.rrortegav.olimpiadas.models.entities.Sede;

import java.util.List;

public interface ISedesService {
	List<Sede> listarSedes();
	Sede buscarPorId(Long id);
	void guardar(Sede sede, Long id);
	boolean eliminarPorId(Long id);
	void eliminarTodos();
}