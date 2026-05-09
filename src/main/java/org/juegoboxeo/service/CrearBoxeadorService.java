package org.juegoboxeo.service;

import org.juegoboxeo.dao.BoxeadorDAO;
import org.juegoboxeo.dao.InformacionGolpeDAO;
import org.juegoboxeo.dao.PartidaBoxeadorDAO;
import org.juegoboxeo.dao.impl.BoxeadorDAOImpl;
import org.juegoboxeo.dao.impl.InformacionGolpeDAOImpl;
import org.juegoboxeo.dao.impl.PartidaBoxeadorDAOImpl;
import org.juegoboxeo.model.Boxeador;
import org.juegoboxeo.model.PartidaBoxeador;
import org.juegoboxeo.model.Rol;

public class CrearBoxeadorService {
    
    private final BoxeadorDAO boxeadorDAO = new BoxeadorDAOImpl();
    
    private final PartidaBoxeadorDAO partidaBoxeadorDAO = new PartidaBoxeadorDAOImpl();
    
    private final InformacionGolpeDAO informacionGolpeDAO = new InformacionGolpeDAOImpl();
    
    public int crearJugadorEnPartida(Boxeador b, int idPartida) {
        
        int idBoxeador = boxeadorDAO.insertarBoxeador(b);
        
        PartidaBoxeador pb = new PartidaBoxeador(idPartida, idBoxeador, Rol.JUGADOR, 0, 0);
        
        partidaBoxeadorDAO.insertarPartidaBoxeador(pb);
        
        informacionGolpeDAO.insertarGolpesBase(idBoxeador);
        
        return idBoxeador;
    }
}