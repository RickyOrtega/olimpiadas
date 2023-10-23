/**
 * Esta clase contiene la lógica necesaria para sincronizar los datos
 * de la base de datos local con los datos de la base de datos remota,
 * ejecutándose como una tarea programada.
 *
 * @version 1.0
 *
 */

package com.rrortegav.olimpiadas;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DataSyncScheduler {

	@Autowired
	private DataSyncService dataSyncService;

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(DataSyncScheduler.class);

	@Scheduled(fixedRate = 3600000)
	public void syncSedes(){

		logger.info("Sincronizando sedes");

		dataSyncService.syncSedes();

		logger.info("Sincronización de sedes finalizada");
	}
}