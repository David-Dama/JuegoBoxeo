package org.juegoboxeo.service;

import org.juegoboxeo.dao.BoxeadorDAO;
import org.juegoboxeo.dao.InformacionGolpeDAO;
import org.juegoboxeo.dao.impl.BoxeadorDAOImpl;
import org.juegoboxeo.dao.impl.InformacionGolpeDAOImpl;
import org.juegoboxeo.exceptions.NoSeEncuentranRegistrosException;
import org.juegoboxeo.model.Boxeador;
import org.juegoboxeo.model.Golpe;
import org.juegoboxeo.model.InformacionGolpe;

import java.util.Random;

public class PelearService {
    
    private final BoxeadorDAO boxeadorDAO = new BoxeadorDAOImpl();
    private final InformacionGolpeDAO informacionGolpeDAO = new InformacionGolpeDAOImpl();
    
    private final Random random = new Random();
    
    private Boxeador jugador;
    private Boxeador contrincante;
    
    public void iniciarPelea(int idJugador, int idContrincante, int idPartida) throws NoSeEncuentranRegistrosException {
        
        jugador = boxeadorDAO.cargarBoxeador(idJugador, idPartida, informacionGolpeDAO.obtenerGolpesPorBoxeador(idJugador, idPartida));
        
        contrincante = boxeadorDAO.cargarBoxeador(idContrincante, idPartida, informacionGolpeDAO.obtenerGolpesPorBoxeador(idContrincante, idPartida));
    }
    
    public void turnoJugador(Golpe golpeJugador) {
        
        usarGolpeSiPuede(jugador, contrincante, golpeJugador);
        
        if (contrincante.estaVivo()) {
            
            Golpe golpeIA = elegirGolpeIA();
            
            usarGolpeSiPuede(contrincante, jugador, golpeIA);
        }
    }
    
    private void usarGolpeSiPuede(Boxeador atacante, Boxeador defensor, Golpe golpe) {
        
        InformacionGolpe info = atacante.getGolpes().get(golpe);
        
        if (atacante.getStaminaActual() < info.getCosteStamina()) {
            atacante.recuperarStaminaMitad();
            return;
        }
        
        usarGolpe(atacante, defensor, golpe);
    }
    
    private void usarGolpe(Boxeador atacante, Boxeador defensor, Golpe golpe) {
        
        InformacionGolpe infoGolpeSeleccionado = atacante.getGolpes().get(golpe);
        
        int numeroAleatorio = random.nextInt(100);
        
        if (numeroAleatorio > infoGolpeSeleccionado.getPrecision()) {
            int danyo = infoGolpeSeleccionado.getDanyo();
            
            //Recalculamos la probabilidad para saber si es crítico
            numeroAleatorio = random.nextInt(100);
            
            if (numeroAleatorio > infoGolpeSeleccionado.getProbabilidadCritico()) {
                danyo *= 2;
            }
            
            defensor.recibirDanyo(danyo);
        } else {
            //El defensor esquiva el golpe y pierde stamina
            defensor.reducirStamina(infoGolpeSeleccionado.getCosteStamina()/2);
        }
        
        atacante.reducirStamina(infoGolpeSeleccionado.getCosteStamina());
    }
    
    private Golpe elegirGolpeIA() {
        
        Golpe[] golpes = Golpe.values();
        
        int indice = random.nextInt(golpes.length);
        
        return golpes[indice];
    }
    
    public Boxeador getJugador() {
        return jugador;
    }
    
    public Boxeador getContrincante() {
        return contrincante;
    }
}