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

    private MotorReproductor() { }

    public static MotorReproductor getInstancia() {
        if (instancia == null) {
            instancia = new MotorReproductor();
        }
        return instancia;
    }

    public void reproducirCancion(String rutaArchivo) {
        detenerCancion(); 
        try {
            FileInputStream fis = new FileInputStream(rutaArchivo);
            playerActual = new Player(fis);
            
            hiloReproduccion = new Thread(() -> {
                try {
                    playerActual.play();
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

