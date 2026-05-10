package org.juegoboxeo.service;

import org.juegoboxeo.dao.BoxeadorDAO;
import org.juegoboxeo.dao.PartidaBoxeadorDAO;
import org.juegoboxeo.dao.impl.BoxeadorDAOImpl;
import org.juegoboxeo.dao.impl.PartidaBoxeadorDAOImpl;
import org.juegoboxeo.dto.PartidaInfoDTO;
import org.juegoboxeo.exceptions.NoSeEncuentranRegistrosException;
import org.juegoboxeo.model.PartidaBoxeador;

public class EscogerPartidaService {
    
    private final BoxeadorDAO boxeadorDAO = new BoxeadorDAOImpl();
    private final PartidaBoxeadorDAO partidaBoxeadorDAO = new PartidaBoxeadorDAOImpl();
    
    public PartidaInfoDTO obtenerInfoPartida(int idPartida) throws NoSeEncuentranRegistrosException {
        
        Integer idBoxeador = partidaBoxeadorDAO.idBoxeadorDePartida(idPartida);
        
        if (idBoxeador == null) {
            return null;
        }
        
        PartidaBoxeador pb = partidaBoxeadorDAO.cargarPartidaBoxeador(idBoxeador, idPartida);
        String nombre = boxeadorDAO.nombreBoxeador(idBoxeador, idPartida);
        
        return new PartidaInfoDTO(nombre, pb.getVictorias(), pb.getDerrotas());
    }
    
    public boolean existeJugadorEnPartida(int idPartida) {
        return partidaBoxeadorDAO.existeJugador(idPartida);
    }
    
    public int idBoxeadorDePartida(int idPartida) {
        return partidaBoxeadorDAO.idBoxeadorDePartida(idPartida);
    }
    
    public void eliminarJugador(int idPartida) {
        
        Integer idBoxeador = partidaBoxeadorDAO.idBoxeadorDePartida(idPartida);
        
        if (idBoxeador == null) {
            return;
        }
        
        // Reinicia victorias y derrotas
        partidaBoxeadorDAO.resetearStatsPartida(idPartida);
        
        // Elimina al jugador de la partida
        partidaBoxeadorDAO.eliminarJugador(idPartida, idBoxeador);
        
        // Elimina el boxeador
        boxeadorDAO.eliminarBoxeador(idBoxeador);
    }
}
