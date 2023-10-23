package com.rrortegav.olimpiadas;

import com.rrortegav.olimpiadas.models.entities.Sede;
import com.rrortegav.olimpiadas.repository.SedeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DataSyncService {

	@Autowired
	private SedeRepository localSedeRepository;

	@Autowired
	private SedeRepository cloudSedeRepository;

	private final Logger logger = LoggerFactory.getLogger(DataSyncService.class);

	@Transactional(transactionManager = "localTransactionManager")
	public void syncSedes(){

		logger.info("Sincronizando sedes");

		List<Sede> localSedes = localSedeRepository.findAll();

		/*Creación y actualización*/

		for(int i = 0; i < localSedes.size(); i++){

			Sede localSede = localSedes.get(i);

			if(!cloudSedeRepository.existsById(localSede.getId())){
				logger.info("Guardando sede en la nube: " + localSede.getId());
			} else {
				logger.info("Actualizando sede en la nube: " + localSede.getId());
			}
			cloudSedeRepository.save(localSede);
		}

		/*ELiminación*/

		List<Sede> cloudSedes = cloudSedeRepository.findAll();

		for(int i = 0; i < cloudSedes.size(); i++){

			Sede cloudSede = cloudSedes.get(i);

			if(!localSedeRepository.existsById(cloudSede.getId())){
				logger.info("Eliminando sede en la nube: " + cloudSede.getId());
				cloudSedeRepository.deleteById(cloudSede.getId());
			}
		}

		logger.info("Sincronización de sedes finalizada");
	}
}