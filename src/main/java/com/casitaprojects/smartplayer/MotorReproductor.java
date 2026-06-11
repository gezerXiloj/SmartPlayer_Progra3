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
    
    // --- NUEVO: Método para adelantar o atrasar la canción ---
    public void saltarAPunto(String rutaArchivo, int segundoDestino, int segundosTotales) {
        detenerCancion(); 
        pausado = false; 
        
        try {
            java.io.File archivo = new java.io.File(rutaArchivo);
            long totalBytes = archivo.length(); // Cuánto pesa el MP3
            
            // Regla de 3: Calculamos qué byte corresponde al segundo que elegiste
            long bytesASaltar = (long) (((double) segundoDestino / segundosTotales) * totalBytes);
            
            java.io.FileInputStream fis = new java.io.FileInputStream(archivo);
            fis.skip(bytesASaltar); // Nos saltamos esa parte del archivo
            playerActual = new Player(fis);
            
            hiloReproduccion = new Thread(() -> {
                try {
                    while (playerActual != null && playerActual.play(1)) {
                        if (pausado) {
                            synchronized (lock) { lock.wait(); }
                        }
                    }
                    if (playerActual != null && accionAlTerminar != null) {
                        javax.swing.SwingUtilities.invokeLater(accionAlTerminar);
                    }
                } catch (Exception e) {
                    System.out.println("Error en reproducción tras salto: " + e.getMessage());
                }
            });
            hiloReproduccion.start();
        } catch (Exception e) {
            System.out.println("Error al saltar: " + e.getMessage());
        }
    }
    
    // --- NUEVO: Método para cambiar el volumen del sistema ---
    public void setVolumen(int porcentaje) {
        try {
            // El slider va de 0 a 100. Aseguramos límites.
            if (porcentaje < 0) porcentaje = 0;
            if (porcentaje > 100) porcentaje = 100;

            // Buscamos la línea de audio activa en el sistema
            javax.sound.sampled.Mixer.Info[] mixers = javax.sound.sampled.AudioSystem.getMixerInfo();
            for (javax.sound.sampled.Mixer.Info mixerInfo : mixers) {
                javax.sound.sampled.Mixer mixer = javax.sound.sampled.AudioSystem.getMixer(mixerInfo);
                javax.sound.sampled.Line[] lines = mixer.getSourceLines();
                
                for (javax.sound.sampled.Line line : lines) {
                    if (line.isOpen() && line.isControlSupported(javax.sound.sampled.FloatControl.Type.MASTER_GAIN)) {
                        javax.sound.sampled.FloatControl gainControl = 
                            (javax.sound.sampled.FloatControl) line.getControl(javax.sound.sampled.FloatControl.Type.MASTER_GAIN);
                        
                        // Conversión matemática de porcentaje (0-100) a decibelios
                        float dB = (float) (Math.log10(porcentaje / 100.0) * 20.0);
                        if (porcentaje == 0) {
                            dB = gainControl.getMinimum(); // Silencio total
                        }
                        
                        gainControl.setValue(dB);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al ajustar volumen: " + e.getMessage());
        }
    }
    
    
}    

