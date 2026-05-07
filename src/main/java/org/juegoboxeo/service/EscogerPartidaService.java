package org.juegoboxeo.service;

import org.juegoboxeo.dao.BoxeadorDAO;
import org.juegoboxeo.dao.PartidaBoxeadorDAO;
import org.juegoboxeo.dao.impl.BoxeadorDAOImpl;
import org.juegoboxeo.dao.impl.PartidaBoxeadorDAOImpl;
import org.juegoboxeo.dto.PartidaInfoDTO;
import org.juegoboxeo.exceptions.NoSeEncuentranRegistrosException;
import org.juegoboxeo.model.PartidaBoxeador;

public class EscogerPartidaService {
    
    private final PartidaBoxeadorDAO partidaDAO = new PartidaBoxeadorDAOImpl();
    private final BoxeadorDAO boxeadorDAO = new BoxeadorDAOImpl();
    
    public PartidaInfoDTO obtenerInfoPartida(int idPartida) throws NoSeEncuentranRegistrosException {
        
        Integer idBoxeador = partidaDAO.idBoxeadorDePartida(idPartida);
        
        if (idBoxeador == null) {
            return null;
        }
        
        PartidaBoxeador pb = partidaDAO.cargarPartidaBoxeador(idBoxeador, idPartida);
        String nombre = boxeadorDAO.nombreBoxeador(idBoxeador, idPartida);
        
        return new PartidaInfoDTO(
            nombre,
            pb.getVictorias(),
            pb.getDerrotas()
        );
    }
}
