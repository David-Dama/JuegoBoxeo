package org.juegoboxeo.model;

import java.util.HashMap;
import java.util.Map;

public class Boxeador {
    
    private int id;
    private String nombre;
    private int anyoNacimiento;
    private String descripcion;
    private int vidaMax;
    private int vidaActual;
    private int staminaMax;
    private int staminaActual;
    private Map <Golpe, InformacionGolpe> golpes;
    
    /**
     Constructor completo del boxeador.
     
     La vida y stamina actuales se inicializan al valor máximo, ya que el objeto se crea en estado de combate.
     
     @param id identificador único del boxeador
     @param nombre nombre del boxeador
     @param anyoNacimiento año de nacimiento del boxeador
     @param descripcion descripción del boxeador
     @param vidaMax vida maxima del boxeador
     @param staminaMax stamina maxima del boxeador
     */
    public Boxeador(int id, String nombre, int anyoNacimiento, String descripcion, int vidaMax, int staminaMax) {
        this.id = id;
        this.nombre = nombre;
        this.anyoNacimiento = anyoNacimiento;
        this.descripcion = descripcion;
        this.vidaMax = vidaMax;
        this.staminaMax = staminaMax;
        this.golpes = new HashMap <Golpe, InformacionGolpe>();
        
        //Cuando se crea el objeto queremos que tengan vida maxima
        this.vidaActual = vidaMax;
        this.staminaActual = staminaMax;
    }
    
    /**
     Obtiene el identificador del boxeador.
     
     @return id del boxeador
     */
    public int getId() {
        return id;
    }
    
    /**
     Establece el identificador del boxeador.
     
     @param id identificador único del boxeador
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     Obtiene el nombre del boxeador.
     
     @return nombre del boxeador.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     Establece el nombre del boxeador.
     
     @param nombre nuevo nombre del boxeador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     Obtiene el año de nacimiento del boxeador.
     
     @return año de nacimiento.
     */
    public int getAnyoNacimiento() {
        return anyoNacimiento;
    }
    
    /**
     Establece el año de nacimiento del boxeador.
     
     @param anyoNacimiento nuevo año de nacimiento.
     */
    public void setAnyoNacimiento(int anyoNacimiento) {
        this.anyoNacimiento = anyoNacimiento;
    }
    
    /**
     Obtiene la descripción del boxeador
     
     @return descripción del boxeador
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     Establece la descripción del boxeador.
     
     @param descripcion descripción del boxeador.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * Obtiene la vida máxima del boxeador.
     *
     * @return vida máxima del boxeador
     */
    public int getVidaMax() {
        return vidaMax;
    }
    
    /**
     * Establece la vida máxima del boxeador.
     *
     * @param vidaMax nueva vida máxima del boxeador
     */
    public void setVidaMax(int vidaMax) {
        this.vidaMax = vidaMax;
    }
    
    /**
     Obtiene la vida actual del boxeador.
     
     @return puntos de vida.
     */
    public int getVida() {
        return vidaActual;
    }
    
    /**
     Establece la vida del boxeador.
     
     @param vida nuevos puntos de vida.
     */
    public void setVida(int vida) {
        this.vidaActual = vida;
    }
    
    /**
     * Obtiene la stamina máxima del boxeador.
     *
     * @return stamina máxima del boxeador
     */
    public int getStaminaMax() {
        return staminaMax;
    }
    
    /**
     * Establece la stamina máxima del boxeador.
     *
     * @param staminaMax nueva stamina máxima del boxeador
     */
    public void setStaminaMax(int staminaMax) {
        this.staminaMax = staminaMax;
    }
    
    /**
     Obtiene la stamina del boxeador.
     
     @return energía disponible.
     */
    public int getStamina() {
        return staminaActual;
    }
    
    /**
     Establece la stamina del boxeador.
     
     @param stamina nueva energía del boxeador.
     */
    public void setStamina(int stamina) {
        this.staminaActual = stamina;
    }
    
    /**
     * Obtiene el conjunto de golpes del boxeador junto con sus estadísticas.
     *
     * @return mapa donde la clave es el tipo de golpe y el valor sus estadísticas asociadas
     */
    public Map <Golpe, InformacionGolpe> getGolpes() {
        return golpes;
    }
    
    /**
     * Establece el conjunto de golpes del boxeador.
     *
     * @param golpes mapa que contiene los golpes y sus estadísticas
     */
    public void setGolpes(Map <Golpe, InformacionGolpe> golpes) {
        this.golpes = golpes;
    }
    
    /**
     Devuelve una representación en texto del boxeador enemigo.
     
     @return información completa del boxeador en formato legible.
     */
    @Override
    public String toString() {
        return String.format("""
            Información boxeador:
            Id: %d
            Nombre: %s
            Año de nacimiento: %d
            Vida: %d
            Stamina: %d
            Información golpes: %s
            """, this.id, this.nombre, this.anyoNacimiento, this.vidaActual, this.staminaActual, this.golpes);
    }
    
    /**
     Reduce la vida del boxeador en función del daño recibido.
     Si la vida baja de 0, se ajusta automáticamente a 0 para evitar valores negativos.
     
     @param danyo cantidad de daño recibido por el boxeador.
     */
    public void recibirDanyo(int danyo) {
        this.vidaActual = Math.max(0, this.vidaActual - danyo);
    }
    
    /**
     Indica si el boxeador sigue vivo.
     Un boxeador se considera vivo mientras su vida sea mayor que 0.
     
     @return true si el boxeador tiene vida restante, false si ha sido derrotado.
     */
    public boolean estaVivo() {
        return this.vidaActual > 0;
    }
    
    /**
     Reduce la stamina del boxeador.
     Si la stamina baja de 0, se ajusta automáticamente a 0.
     
     @param cantidad cantidad de stamina a reducir
     */
    public void reducirStamina(int cantidad) {
        this.staminaActual = Math.max(0, this.staminaActual - cantidad);
    }
    
    /**
     Indica si el boxeador tiene stamina suficiente.
     
     @return true si la stamina es mayor que 0, false si está agotada.
     */
    public boolean tieneStamina() {
        return this.staminaActual > 0;
    }
}