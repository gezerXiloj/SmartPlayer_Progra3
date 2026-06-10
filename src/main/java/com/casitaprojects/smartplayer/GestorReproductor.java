/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casitaprojects.smartplayer;

/**
 *
 * @author gezer
 */
public class GestorReproductor {
    public ArbolABB arbolABB;
    public ArbolAVL arbolAVL;
    public TablaHash tablaHash;
    public ListaDobleCircular listaReproduccion = new ListaDobleCircular();
    
    public java.util.List<Playlist> misPlaylists;

    public GestorReproductor() {
        arbolABB = new ArbolABB();
        arbolAVL = new ArbolAVL();
        tablaHash = new TablaHash();
        misPlaylists = new java.util.ArrayList<>();
    }
    
    // Método para medir el tiempo de búsqueda (Lo que pidió el Ingeniero)
    public void buscarCancionesPorLetra(String letra) {
        long inicio = System.nanoTime();
        
        // Aquí irá la lógica de recorrer el árbol buscando la letra
        // bibliotecaPrincipal.buscarCoincidencias(letra);
        
        long fin = System.nanoTime();
        long tiempoTardado = fin - inicio;
        System.out.println("Búsqueda completada en: " + tiempoTardado + " nanosegundos");
    }
    
    // CORREGIDO: Ahora busca sobre 'misPlaylists'
    public Playlist buscarPlaylistPorNombre(String nombre) {
        for (Playlist p : misPlaylists) { 
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p; 
            }
        }
        return null; 
    }

    // NUEVO: Método necesario para registrar las nuevas playlists creadas
    public void crearPlaylist(Playlist nueva) {
        if (nueva != null) {
            misPlaylists.add(nueva);
        }
    }
}
