/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.casitaprojects.smartplayer;

import javax.swing.UIManager;

/**
 *
 * @author gezer
 */
public class SmartPlayer {

    public static void main(String[] args) {
        /* 1. Obligamos a Java a usar el diseño moderno de Windows */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.out.println("Error al cargar el tema visual: " + ex.getMessage());
        }
            VentanaPrincipal verSmart=new VentanaPrincipal();
            verSmart.setVisible(true);
    }
}
