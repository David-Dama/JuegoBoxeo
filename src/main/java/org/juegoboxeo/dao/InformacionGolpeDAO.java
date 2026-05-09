package org.juegoboxeo.dao;

import org.juegoboxeo.model.Golpe;
import org.juegoboxeo.model.InformacionGolpe;

import java.util.Map;

public interface InformacionGolpeDAO {
    //Create
    void insertarGolpesBase(int boxeadorId);
    
    Map <Golpe, InformacionGolpe> obtenerGolpesPorBoxeador(int idBoxeador, int idPartida);
    
    //Delete
    void eliminarPorBoxeador(int boxeadorId);
}
