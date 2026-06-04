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
    private final Object lock = new Object(); // Nuestro "candado"

    private MotorReproductor() { }

    public static MotorReproductor getInstancia() {
        if (instancia == null) {
            instancia = new MotorReproductor();
        }
        return instancia;
    }

    public void reproducirCancion(String rutaArchivo) {
        detenerCancion(); 
        pausado = false; // Nos aseguramos que empiece sonando
        
        try {
            FileInputStream fis = new FileInputStream(rutaArchivo);
            playerActual = new Player(fis);
            
            hiloReproduccion = new Thread(() -> {
                try {
                    // El truco maestro: reproducimos frame por frame (1)
                    while (playerActual != null && playerActual.play(1)) {
                        if (pausado) {
                            synchronized (lock) {
                                lock.wait(); // Congelamos el tiempo aquí
                            }
                        }
                    }
                    // Si sale del while, la canción terminó (Pronto haremos que salte a la siguiente sola)
                } catch (Exception e) {
                    System.out.println("Error en reproducción: " + e.getMessage());
                }
            });
            hiloReproduccion.start();
            System.out.println("🎵 Reproduciendo: " + rutaArchivo);
        } catch (Exception e) {
            System.out.println("No se pudo cargar: " + e.getMessage());
        }
    }

    public void pausar() {
        pausado = true;
    }

    public void reanudar() {
        pausado = false;
        synchronized (lock) {
            lock.notify(); // Despertamos el hilo congelado
        }
    }

    public boolean estaPausado() {
        return pausado;
    }

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

