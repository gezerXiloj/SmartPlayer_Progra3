/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.casitaprojects.smartplayer;

import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarkLaf;

/**
 *
 * @author gezer
 */
public class SmartPlayer {

    public static void main(String[] args) {
        /* 1. Obligamos a Java a usar el diseño Oscuro y Elegante (FlatLaf) */
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.out.println("Error al cargar el tema visual: " + ex.getMessage());
        }
        
        /* 2. Arrancamos la ventana principal */
        VentanaPrincipal verSmart = new VentanaPrincipal();
        verSmart.setVisible(true);
    }
}
