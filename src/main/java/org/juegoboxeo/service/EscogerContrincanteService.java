package org.juegoboxeo.service;

import org.juegoboxeo.dao.BoxeadorDAO;
import org.juegoboxeo.dao.InformacionGolpeDAO;
import org.juegoboxeo.dao.PartidaBoxeadorDAO;
import org.juegoboxeo.dao.impl.BoxeadorDAOImpl;
import org.juegoboxeo.dao.impl.InformacionGolpeDAOImpl;
import org.juegoboxeo.dao.impl.PartidaBoxeadorDAOImpl;
import org.juegoboxeo.dto.BoxeadorDTO;
import org.juegoboxeo.exceptions.NoSeEncuentranRegistrosException;
import org.juegoboxeo.model.Boxeador;

public class EscogerContrincanteService {
    
    private final BoxeadorDAO boxeadorDAO = new BoxeadorDAOImpl();
    private final PartidaBoxeadorDAO partidaDAO = new PartidaBoxeadorDAOImpl();
    private final InformacionGolpeDAO informacionGolpeDAO = new InformacionGolpeDAOImpl();
    
    public BoxeadorDTO obtenerContrincante(int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException {
        
        Boxeador b = boxeadorDAO.cargarBoxeador(idBoxeador, idPartida, informacionGolpeDAO.obtenerGolpesPorBoxeador(idBoxeador, idPartida));
        
        int v = partidaDAO.obtenerVictorias(idBoxeador, idPartida);
        int d = partidaDAO.obtenerDerrotas(idBoxeador, idPartida);
        
        return new BoxeadorDTO(b.getNombre(), b.getAnyoNacimiento(), b.getDescripcion(), b.getVidaMax(), b.getStaminaMax(), v, d);
    }
}
