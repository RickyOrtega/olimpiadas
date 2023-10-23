package com.rrortegav.olimpiadas.services.sedes;

import com.rrortegav.olimpiadas.models.entities.Sede;
import com.rrortegav.olimpiadas.repository.SedeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SedesServiceImpl implements ISedesService {

	@Autowired
	private SedeRepository localSedeRepository;


	private final Logger logger = LoggerFactory.getLogger(SedesServiceImpl.class);

	@Override
	public List<Sede> listarSedes() {
		return this.localSedeRepository.findAll();
	}

	@Override
	public Sede buscarPorId(Long id) {
		return this.localSedeRepository.findById(id).orElse(null);
	}

	@Override
	public void guardar(Sede sede, Long id) {

		logger.info("Guardando sede: " + id);

		if(id != null && id != 0){
			logger.info("Actualizando sede con id: " + id);
			sede.setId(id);
		}else{
			logger.info("Guardando nueva sede");
		}

		this.localSedeRepository.save(sede);
	}

	@Override
	public boolean eliminarPorId(Long id) {

		logger.info("Eliminando sede con id: " + id);

		if(this.localSedeRepository.existsById(id))
			this.localSedeRepository.deleteById(id);
		else
			return false;
		return true;
	}

	@Override
	public void eliminarTodos() {

	}
}