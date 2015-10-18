package ideasw.secopre.web;

import ideasw.secopre.web.controller.SecopreCache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase de configuracion que se ejecuta al iniciar el contexto de Spring, esta
 * clase configura todos los atributos estaticos de {@link SecopreConstants}
 *
 * @author jorge.canoc@gmail.com
 *
 */
@Component
public class InitConfig {

	@Autowired
	SecopreCache cache;
	
	static final Logger LOG = LoggerFactory.getLogger(InitConfig.class);

	/**
	 * Metodo que ejecuta la configuracion de las constantes que utilizara
	 * secopre
	 */
	public void onInit() {
        LOG.debug("========> Iniciando Configuracion Secopre <========");
        try{
            initConstants();
        }catch(Exception e){
            LOG.debug("Ocurrio un error al inciar la configuracion de secopre: " + e.getMessage());
            LOG.error("Ocurrio un error al inciar la configuracion ", e);
        }
        LOG.debug("========> Termina Configuracion Secopre <========");
		
	}
	
    /**
     * Metodo auxiliar de configuracion
     */
    private void initConstants() throws Exception{
    	cache.getAlldistricts();
    	cache.getAllStates();
    }
}
