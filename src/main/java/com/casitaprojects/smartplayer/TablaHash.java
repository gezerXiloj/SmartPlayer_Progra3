/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casitaprojects.smartplayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 *
 * @author gezer
 */
public class TablaHash {
// Creamos tres "diccionarios" (HashMaps) para búsquedas súper rápidas
    private HashMap<String, List<Cancion>> hashPorTitulo;
    private HashMap<String, List<Cancion>> hashPorArtista;
    private HashMap<String, List<Cancion>> hashPorGenero;

    public TablaHash() {
        hashPorTitulo = new HashMap<>();
        hashPorArtista = new HashMap<>();
        hashPorGenero = new HashMap<>();
    }

    // Método para indexar la canción en las tres tablas al mismo tiempo
    public void insertar(Cancion cancion) {
        // 1. Indexar por Título (en minúsculas para ignorar mayúsculas al buscar)
        String titulo = cancion.getTitulo().toLowerCase();
        hashPorTitulo.putIfAbsent(titulo, new ArrayList<>()); // Si no existe la lista, la crea
        hashPorTitulo.get(titulo).add(cancion); // Mete la canción a la lista

        // 2. Indexar por Artista
        String artista = cancion.getArtista().toLowerCase();
        hashPorArtista.putIfAbsent(artista, new ArrayList<>());
        hashPorArtista.get(artista).add(cancion);

        // 3. Indexar por Género
        String genero = cancion.getGenero().toLowerCase();
        hashPorGenero.putIfAbsent(genero, new ArrayList<>());
        hashPorGenero.get(genero).add(cancion);
    }

        // Método de búsqueda PARCIAL Y UNIVERSAL (Busca en las 3 tablas)
        public List<Cancion> buscar(String termino) {
        termino = termino.toLowerCase().trim();
        List<Cancion> resultados = new ArrayList<>();

        // Iteramos buscando coincidencias PARCIALES en lugar de exactas
        for (String key : hashPorTitulo.keySet()) {
            if (key.contains(termino)) resultados.addAll(hashPorTitulo.get(key));
        }
        for (String key : hashPorArtista.keySet()) {
            if (key.contains(termino)) resultados.addAll(hashPorArtista.get(key));
        }
        for (String key : hashPorGenero.keySet()) {
            if (key.contains(termino)) resultados.addAll(hashPorGenero.get(key));
        }

        // Eliminamos duplicados y retornamos
        return resultados.stream().distinct().toList();
    }    
}
