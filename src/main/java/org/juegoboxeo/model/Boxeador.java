package org.juegoboxeo.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa a un boxeador dentro del juego.
 * Contiene sus atributos básicos como nombre, estadísticas de combate
 * y estado durante los enfrentamientos.
 */
public class Boxeador {
    
    private String nombre;
    private int anyoNacimiento;
    private int vida;
    private int stamina;
    private Map <Golpe, Integer> danyoPorGolpe;
    private Map<Golpe, Integer> precisionPorGolpe;
    
    /**
     * Constructor completo que inicializa todos los atributos del boxeador enemigo.
     *
     * @param nombre Nombre del peleador.
     * @param anyoNacimiento Año de nacimiento del peleador.
     * @param vida Puntos de vida del peleador.
     * @param stamina Energía del peleador utilizada para acciones especiales o esquivas.
     */
    public Boxeador(String nombre, int anyoNacimiento, int vida, int stamina) {
        this.nombre = nombre;
        this.anyoNacimiento = anyoNacimiento;
        this.vida = vida;
        this.stamina = stamina;
        this.danyoPorGolpe = new HashMap <>();
        this.precisionPorGolpe = new HashMap<>();
    }
    
    /**
     * Obtiene el nombre del boxeador.
     *
     * @return nombre del peleador.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del boxeador.
     *
     * @param nombre nuevo nombre del peleador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el año de nacimiento del boxeador.
     *
     * @return año de nacimiento.
     */
    public int getAnyoNacimiento() {
        return anyoNacimiento;
    }
    
    /**
     * Establece el año de nacimiento del boxeador.
     *
     * @param anyoNacimiento nuevo año de nacimiento.
     */
    public void setAnyoNacimiento(int anyoNacimiento) {
        this.anyoNacimiento = anyoNacimiento;
    }
    
    /**
     * Obtiene la vida actual del boxeador.
     *
     * @return puntos de vida.
     */
    public int getVida() {
        return vida;
    }
    
    /**
     * Establece la vida del boxeador.
     *
     * @param vida nuevos puntos de vida.
     */
    public void setVida(int vida) {
        this.vida = vida;
    }
    
    /**
     * Obtiene la stamina del boxeador.
     *
     * @return energía disponible.
     */
    public int getStamina() {
        return stamina;
    }
    
    /**
     * Establece la stamina del boxeador.
     *
     * @param stamina nueva energía del peleador.
     */
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }
    
    /**
     * Devuelve una representación en texto del boxeador enemigo.
     *
     * @return información completa del peleador en formato legible.
     */
    @Override
    public String toString() {
        return String.format("""
            Información peleador:
            Nombre: %s
            Año de nacimiento: %d
            Vida: %d
            Stamina: %d""",
            nombre, anyoNacimiento, vida, stamina);
    }
    
    /**
     * Reduce la vida del boxeador en función del daño recibido.
     * Si la vida baja de 0, se ajusta automáticamente a 0 para evitar valores negativos.
     *
     * @param danyo cantidad de daño recibido por el boxeador.
     */
    public void recibirDanyo(int danyo) {
        this.vida = Math.max(0, this.vida - danyo);
    }
    
    /**
     * Indica si el boxeador sigue vivo.
     * Un boxeador se considera vivo mientras su vida sea mayor que 0.
     *
     * @return true si el boxeador tiene vida restante, false si ha sido derrotado.
     */
    public boolean estaVivo() {
        return this.vida > 0;
    }
    
    /**
     * Reduce la stamina del boxeador.
     * Si la stamina baja de 0, se ajusta automáticamente a 0.
     *
     * @param cantidad cantidad de stamina a reducir
     */
    public void reducirStamina(int cantidad) {
        this.stamina = Math.max(0, this.stamina - cantidad);
    }
    
    /**
     * Indica si el boxeador tiene stamina suficiente.
     *
     * @return true si la stamina es mayor que 0, false si está agotada.
     */
    public boolean tieneStamina() {
        return this.stamina > 0;
    }
}