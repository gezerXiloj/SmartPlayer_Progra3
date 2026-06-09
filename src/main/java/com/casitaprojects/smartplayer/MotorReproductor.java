/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casitaprojects.smartplayer;

    import java.io.FileInputStream;
    import javazoom.jl.player.Player;
    /**
     *
     * @author gezer
     */
    public class MotorReproductor {
        
    private static MotorReproductor instancia;
    private Player playerActual;
    private Thread hiloReproduccion;
    
    // Variables para controlar la pausa
    private boolean pausado = false;
    private final Object lock = new Object(); 
    
    // --- NUEVO: Guardará la instrucción de qué hacer al terminar ---
    private Runnable accionAlTerminar;

    private MotorReproductor() { }

    public static MotorReproductor getInstancia() {
        if (instancia == null) {
            instancia = new MotorReproductor();
        }
        return instancia;
    }

    // --- NUEVO: Método para recibir la instrucción ---
    public void setAccionAlTerminar(Runnable accion) {
        this.accionAlTerminar = accion;
    }

    public void reproducirCancion(String rutaArchivo) {
        detenerCancion(); 
        pausado = false; 
        
        try {
            FileInputStream fis = new FileInputStream(rutaArchivo);
            playerActual = new Player(fis);
            
            hiloReproduccion = new Thread(() -> {
                try {
                    // Reproducimos frame por frame
                    while (playerActual != null && playerActual.play(1)) {
                        if (pausado) {
                            synchronized (lock) {
                                lock.wait(); 
                            }
                        }
                    }
                    
                    // --- NUEVO: Magia de Autoplay ---
                    // Si la canción termina solita, ejecutamos el "Next" automático
                    if (playerActual != null && accionAlTerminar != null) {
                        javax.swing.SwingUtilities.invokeLater(accionAlTerminar);
                    }
                    
                } catch (Exception e) {
                    System.out.println("Error en reproducción: " + e.getMessage());
                }
            });
            hiloReproduccion.start();
        } catch (Exception e) {
            System.out.println("No se pudo cargar: " + e.getMessage());
        }
    }

    public void pausar() { pausado = true; }

    public void reanudar() {
        pausado = false;
        synchronized (lock) { lock.notify(); }
    }

    public boolean estaPausado() { return pausado; }

    public void detenerCancion() {
        if (playerActual != null) {
            playerActual.close();
            playerActual = null;
        }
        if (hiloReproduccion != null) {
            hiloReproduccion.interrupt();
            hiloReproduccion = null;
        }
    }
}    

