package org.juegoboxeo.dao;

import org.juegoboxeo.exceptions.NoSeEncuentranRegistrosException;
import org.juegoboxeo.model.Boxeador;

public interface BoxeadorDAO {
    
    // Create
    void insertarBoxeador(Boxeador b);
    
    // Read
    Boxeador cargarBoxeador(int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException;
    
    String nombreBoxeador(int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException;
    
    // Update
    void actualizarStatsBoxeador(int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException;
}