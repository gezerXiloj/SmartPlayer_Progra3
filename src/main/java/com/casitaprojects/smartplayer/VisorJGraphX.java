/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casitaprojects.smartplayer;

import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.awt.BorderLayout;
import javax.swing.JDialog;

/**
 *
 * @author gezer
 */
public class VisorJGraphX extends JDialog {
    private mxGraph graph;
    private Object parent;
    private java.util.HashMap<String, Object> nodos;

    public VisorJGraphX(java.awt.Frame padre, boolean modal) {
        super(padre, modal);
        setSize(1000, 700); 
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());

        graph = new mxGraph();
        parent = graph.getDefaultParent();
        nodos = new java.util.HashMap<>();
    }

    // --- MANEJADOR DE INTERACCIÓN (ZOOM Y ARRASTRE) ---
    private mxGraphComponent crearComponenteGrafico() {
        mxGraphComponent componente = new mxGraphComponent(graph) {
            @Override
            public boolean isPanningEvent(java.awt.event.MouseEvent event) {
                return javax.swing.SwingUtilities.isLeftMouseButton(event);
            }
        };
        
        componente.setPanning(true); 
        componente.setConnectable(false);
        componente.getGraph().setCellsEditable(false);
        componente.getGraph().setCellsMovable(false);
        componente.getGraph().setCellsResizable(false);
        componente.getGraph().setCellsSelectable(false); 

        // ZOOM con la ruedita del mouse
        componente.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            @Override
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent e) {
                if (e.getWheelRotation() < 0) {
                    componente.zoomIn();
                } else {
                    componente.zoomOut();
                }
            }
        });

        componente.zoomOut(); // Zoom inicial leve para perspectiva
        return componente;
    }

    public void mostrarABB(Nodo raiz) {
        if (raiz == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "El árbol ABB está vacío");
            return;
        }

        setTitle("Visualización completa del Árbol ABB");
        graph.getModel().beginUpdate();
        try {
            agregarABB(raiz);
            mxCompactTreeLayout layout = new mxCompactTreeLayout(graph, false);
            layout.setLevelDistance(80); 
            layout.setNodeDistance(40);  
            layout.execute(parent);
        } finally { 
            graph.getModel().endUpdate();
        }

        mxGraphComponent componente = crearComponenteGrafico();
        add(componente, BorderLayout.CENTER);
        setVisible(true);
    }

    public void mostrarAVL(Nodo raiz) {
        if (raiz == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "El árbol AVL está vacío");
            return;
        }

        setTitle("Visualización completa del Árbol AVL");
        graph.getModel().beginUpdate();
        try {
            agregarAVL(raiz);
            mxCompactTreeLayout layout = new mxCompactTreeLayout(graph, false);
            layout.setLevelDistance(80);
            layout.setNodeDistance(40);
            layout.execute(parent);
        } finally {
            graph.getModel().endUpdate();
        }

        mxGraphComponent componente = crearComponenteGrafico();
        add(componente, BorderLayout.CENTER);
        setVisible(true);
    }

    public Object agregarABB(Nodo nodo) {
        if (nodo == null) return null;

        String id = nodo.cancion.getRutaArchivo();

        // --- NUEVO COLOR: Cyan Eléctrico (#00B0FF) ---
        Object nodoGrafico = graph.insertVertex(
                parent,
                id,
                cortarNombre(nodo.cancion.getTitulo()),
                0, 0, 80, 80, 
                "shape=ellipse;fillColor=#00B0FF;fontColor=#ffffff;strokeColor=#ffffff;fontStyle=1;whiteSpace=wrap"
        );

        nodos.put(id, nodoGrafico);

        if (nodo.izquierdo != null) {
            Object hijoIzq = agregarABB(nodo.izquierdo);
            graph.insertEdge(parent, null, "Izq", nodoGrafico, hijoIzq);
        }

        if (nodo.derecho != null) {
            Object hijoDer = agregarABB(nodo.derecho);
            graph.insertEdge(parent, null, "Der", nodoGrafico, hijoDer);
        }

        return nodoGrafico;
    }

    public Object agregarAVL(Nodo nodo) {
        if (nodo == null) return null;

        String id = nodo.cancion.getRutaArchivo();

        // --- NUEVO COLOR: Azul Cobalto Neon (#2979FF) ---
        Object nodoGrafico = graph.insertVertex(
                parent,
                id,
                cortarNombre(nodo.cancion.getTitulo()),
                0, 0, 80, 80,
                "shape=ellipse;fillColor=#2979FF;fontColor=#ffffff;strokeColor=#ffffff;fontStyle=1;whiteSpace=wrap"
        );

        nodos.put(id, nodoGrafico);

        if (nodo.izquierdo != null) {
            Object hijoIzq = agregarAVL(nodo.izquierdo);
            graph.insertEdge(parent, null, "Izq", nodoGrafico, hijoIzq);
        }

        if (nodo.derecho != null) {
            Object hijoDer = agregarAVL(nodo.derecho);
            graph.insertEdge(parent, null, "Der", nodoGrafico, hijoDer);
        }

        return nodoGrafico;
    }

    public String cortarNombre(String nombre) {
        if (nombre == null) return "Sin nombre";
        if (nombre.length() > 10) return nameTruncate(nombre);
        return nombre;
    }

    private String nameTruncate(String name) {
        return name.substring(0, 10) + "..";
    }
}