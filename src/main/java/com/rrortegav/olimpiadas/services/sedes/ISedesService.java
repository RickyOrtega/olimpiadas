package com.rrortegav.olimpiadas.services.sedes;


import com.rrortegav.olimpiadas.models.entities.Sede;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ISedesService {
	@Transactional(transactionManager = "localTransactionManager")
	List<Sede> listarSedes();
	@Transactional(transactionManager = "localTransactionManager")
	Sede buscarPorId(Long id);
	@Transactional(transactionManager = "localTransactionManager")
	void guardar(Sede sede, Long id);
	@Transactional(transactionManager = "localTransactionManager")
	boolean eliminarPorId(Long id);
	@Transactional(transactionManager = "localTransactionManager")
	void eliminarTodos();
}