/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.casitaprojects.smartplayer;

import java.io.File;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;

/**
 *
 * @author gezer
 */
public class panelPlaylist extends javax.swing.JPanel {
    private Playlist playlistSeleccionadaActual = null; // Para saber cuál estamos viendo
    /**
     * Creates new form panelPlaylist
     */
    public panelPlaylist() {
        initComponents();
        // Configuramos el panel izquierdo para que los botones se apilen hacia abajo
        pnlMisPlaylis.setLayout(new javax.swing.BoxLayout(pnlMisPlaylis, javax.swing.BoxLayout.Y_AXIS));
    }

    // --- 1. DIBUJAR LAS PLAYLISTS A LA IZQUIERDA ---
    public void actualizarListaPlaylists() {
        pnlMisPlaylis.removeAll(); // Borramos botones viejos
        VentanaPrincipal ventana = (VentanaPrincipal) javax.swing.SwingUtilities.getWindowAncestor(this);
        
        if (ventana != null && ventana.gestor.misPlaylists != null) {
            for (Playlist p : ventana.gestor.misPlaylists) {
                JButton btnPlay = new JButton("🎵 " + p.getNombre());
                // Estilo para que se vea como texto plano moderno
                btnPlay.setContentAreaFilled(false);
                btnPlay.setBorderPainted(false);
                btnPlay.setForeground(java.awt.Color.WHITE);
                btnPlay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnPlay.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                btnPlay.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 35));
                
                // Evento al dar clic en una playlist del menú izquierdo
                btnPlay.addActionListener(e -> seleccionarPlaylist(p, ventana));
                
                pnlMisPlaylis.add(btnPlay);
            }
        }
        pnlMisPlaylis.revalidate();
        pnlMisPlaylis.repaint();
    }

    // --- 2. QUÉ PASA AL SELECCIONAR O CREAR UNA PLAYLIST ---
    private void seleccionarPlaylist(Playlist p, VentanaPrincipal ventana) {
        playlistSeleccionadaActual = p;
        
        // Si veníamos de la Biblioteca con una canción en la mano, ¡la agregamos!
        if (ventana.cancionPendiente != null) {
            p.agregarCancion(ventana.cancionPendiente);
            JOptionPane.showMessageDialog(this, "Canción agregada a '" + p.getNombre() + "'", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            ventana.cancionPendiente = null; // Vaciamos el portapapeles
        }
        
        actualizarDatosVisuales();
    }

    // --- 3. ACTUALIZAR LA TABLA, TIEMPO Y PORTADA ---
    private void actualizarDatosVisuales() {
        if (playlistSeleccionadaActual == null) return;
        
        lblNombPlay.setText(playlistSeleccionadaActual.getNombre());
        List<Cancion> canciones = playlistSeleccionadaActual.getCanciones();
        
        // A) LLENAMOS LA TABLA
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tblPlaylist.getModel();
        modelo.setRowCount(0); 
        int contador = 1;
        int totalSegundos = 0;
        
        for (Cancion c : canciones) {
            modelo.addRow(new Object[]{ contador++, c.getTitulo(), c.getArtista(), c.getAlbum(), c.getGenero(), c.getDuracion(), c.getAnio() });
            
            // Calculamos el tiempo (viene en formato MM:SS)
            try {
                String[] partes = c.getDuracion().split(":");
                totalSegundos += (Integer.parseInt(partes[0]) * 60) + Integer.parseInt(partes[1]);
            } catch(Exception ignored) {}
        }
        
        // B) MOSTRAMOS LA DURACIÓN TOTAL (Ej: 2h 48m)
        int horas = totalSegundos / 3600;
        int mins = (totalSegundos % 3600) / 60;
        String tiempoTexto = canciones.size() + " canciones • " + (horas > 0 ? horas + "h " : "") + mins + "m";
        lblCantidadTime.setText(tiempoTexto);
        
        // C) EXTRAEMOS LA PORTADA DE LA PRIMERA CANCIÓN (Si hay canciones)
        if (!canciones.isEmpty()) {
            try {
                AudioFile audioFile = AudioFileIO.read(new File(canciones.get(0).getRutaArchivo()));
                Tag tag = audioFile.getTag();
                if (tag != null && tag.getFirstArtwork() != null) {
                    byte[] imageData = tag.getFirstArtwork().getBinaryData();
                    javax.swing.ImageIcon icon = new javax.swing.ImageIcon(imageData);
                    java.awt.Image img = icon.getImage().getScaledInstance(lblPortada.getWidth(), lblPortada.getHeight(), java.awt.Image.SCALE_SMOOTH);
                    lblPortada.setIcon(new javax.swing.ImageIcon(img));
                    lblPortada.setText("");
                } else {
                    lblPortada.setIcon(null); lblPortada.setText("Sin Portada");
                }
            } catch (Exception e) { lblPortada.setIcon(null); lblPortada.setText("Sin Portada"); }
        } else {
            lblPortada.setIcon(null); lblPortada.setText("Vacía");
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuOpciones = new javax.swing.JPopupMenu();
        itemRenombrar = new javax.swing.JMenuItem();
        itemExportar = new javax.swing.JMenuItem();
        itemEncriptar = new javax.swing.JMenuItem();
        itemEliminar = new javax.swing.JMenuItem();
        pnlDerecho = new javax.swing.JPanel();
        pnlIzquierdo = new javax.swing.JPanel();
        pnlCabeceraPlay = new javax.swing.JPanel();
        lblMisPlaylist = new javax.swing.JLabel();
        btnMas = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        pnlMisPlaylis = new javax.swing.JPanel();
        btnImportar = new javax.swing.JButton();
        pnlCabecera = new javax.swing.JPanel();
        lblPortada = new javax.swing.JLabel();
        lblNombPlay = new javax.swing.JLabel();
        lblCantidadTime = new javax.swing.JLabel();
        btnPlayLista = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPlaylist = new javax.swing.JTable();

        itemRenombrar.setText("RENOMBRAR");
        itemRenombrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRenombrarActionPerformed(evt);
            }
        });
        menuOpciones.add(itemRenombrar);

        itemExportar.setText("EXPORTAR");
        itemExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExportarActionPerformed(evt);
            }
        });
        menuOpciones.add(itemExportar);

        itemEncriptar.setText("ENCRIPTAR");
        menuOpciones.add(itemEncriptar);

        itemEliminar.setText("ELIMINAR");
        menuOpciones.add(itemEliminar);

        javax.swing.GroupLayout pnlDerechoLayout = new javax.swing.GroupLayout(pnlDerecho);
        pnlDerecho.setLayout(pnlDerechoLayout);
        pnlDerechoLayout.setHorizontalGroup(
            pnlDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlDerechoLayout.setVerticalGroup(
            pnlDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblMisPlaylist.setText("MIS PLAYLIST");

        btnMas.setText("+");
        btnMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCabeceraPlayLayout = new javax.swing.GroupLayout(pnlCabeceraPlay);
        pnlCabeceraPlay.setLayout(pnlCabeceraPlayLayout);
        pnlCabeceraPlayLayout.setHorizontalGroup(
            pnlCabeceraPlayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCabeceraPlayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMisPlaylist)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        pnlCabeceraPlayLayout.setVerticalGroup(
            pnlCabeceraPlayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCabeceraPlayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCabeceraPlayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMisPlaylist)
                    .addComponent(btnMas, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnImportar.setText("IMPORTAR");
        btnImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMisPlaylisLayout = new javax.swing.GroupLayout(pnlMisPlaylis);
        pnlMisPlaylis.setLayout(pnlMisPlaylisLayout);
        pnlMisPlaylisLayout.setHorizontalGroup(
            pnlMisPlaylisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMisPlaylisLayout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addComponent(btnImportar)
                .addGap(15, 15, 15))
        );
        pnlMisPlaylisLayout.setVerticalGroup(
            pnlMisPlaylisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMisPlaylisLayout.createSequentialGroup()
                .addContainerGap(264, Short.MAX_VALUE)
                .addComponent(btnImportar)
                .addGap(15, 15, 15))
        );

        jScrollPane3.setViewportView(pnlMisPlaylis);

        javax.swing.GroupLayout pnlIzquierdoLayout = new javax.swing.GroupLayout(pnlIzquierdo);
        pnlIzquierdo.setLayout(pnlIzquierdoLayout);
        pnlIzquierdoLayout.setHorizontalGroup(
            pnlIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCabeceraPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3)
        );
        pnlIzquierdoLayout.setVerticalGroup(
            pnlIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIzquierdoLayout.createSequentialGroup()
                .addComponent(pnlCabeceraPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        lblPortada.setText("PORTADA");

        lblNombPlay.setText("Favorita");

        lblCantidadTime.setText("42 canciones • 2h 48m");

        btnPlayLista.setText("PLAY LISTA");
        btnPlayLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayListaActionPerformed(evt);
            }
        });

        btnMenu.setText("⋮");
        btnMenu.setBorderPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenu.setFocusPainted(false);
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCabeceraLayout = new javax.swing.GroupLayout(pnlCabecera);
        pnlCabecera.setLayout(pnlCabeceraLayout);
        pnlCabeceraLayout.setHorizontalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblPortada, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombPlay)
                    .addComponent(lblCantidadTime))
                .addGap(48, 48, 48)
                .addComponent(btnPlayLista, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        pnlCabeceraLayout.setVerticalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPortada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCabeceraLayout.createSequentialGroup()
                        .addComponent(lblNombPlay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCantidadTime))
                    .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPlayLista, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tblPlaylist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "TITULO", "ARTISTA", "ALBUM", "GENERO", "DURACION", "AÑO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPlaylist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPlaylistMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPlaylist);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlIzquierdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDerecho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlIzquierdo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlDerecho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        // Esto hace que el menú aparezca justo debajo del botón de los 3 puntitos
        menuOpciones.show(btnMenu, 0, btnMenu.getHeight());
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasActionPerformed
        String nombre = JOptionPane.showInputDialog(this, "Ingresa el nombre de la nueva Playlist:", "Nueva Playlist", JOptionPane.PLAIN_MESSAGE);
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            Playlist nueva = new Playlist(nombre.trim());
            VentanaPrincipal ventana = (VentanaPrincipal) javax.swing.SwingUtilities.getWindowAncestor(this);
            ventana.gestor.misPlaylists.add(nueva);
            actualizarListaPlaylists(); // Redibujamos la lista
            seleccionarPlaylist(nueva, ventana); // La seleccionamos automáticamente
        }
    }//GEN-LAST:event_btnMasActionPerformed

    private void btnPlayListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayListaActionPerformed
        if (playlistSeleccionadaActual != null && !playlistSeleccionadaActual.getCanciones().isEmpty()) {
            VentanaPrincipal ventana = (VentanaPrincipal) javax.swing.SwingUtilities.getWindowAncestor(this);
            
            // Le mandamos TODA la lista a la ListaDobleCircular para que funcionen los Next/Back
            ventana.gestor.listaReproduccion.cargarLista(
                playlistSeleccionadaActual.getCanciones(), 
                playlistSeleccionadaActual.getCanciones().get(0)
            );
            
            // Le damos play a la primera canción de la lista
            ventana.reproducirDesdeBiblioteca(playlistSeleccionadaActual.getCanciones().get(0));
            
            JOptionPane.showMessageDialog(this, "¡Reproduciendo Playlist: " + playlistSeleccionadaActual.getNombre() + "!");
        } else {
            JOptionPane.showMessageDialog(this, "La Playlist está vacía. Agrégale canciones primero.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnPlayListaActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
       
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void tblPlaylistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPlaylistMouseClicked
        if (evt.getClickCount() == 2) {
            int filaSeleccionada = tblPlaylist.getSelectedRow();
            
            // Validamos que haya dado clic en una fila válida y haya canciones
            if (filaSeleccionada != -1 && playlistSeleccionadaActual != null && !playlistSeleccionadaActual.getCanciones().isEmpty()) {
                
                Cancion cancionClickeada = playlistSeleccionadaActual.getCanciones().get(filaSeleccionada);
                VentanaPrincipal ventana = (VentanaPrincipal) javax.swing.SwingUtilities.getWindowAncestor(this);
                
                // 1. Cargamos TODA la lista actual al motor
                ventana.gestor.listaReproduccion.cargarLista(
                        playlistSeleccionadaActual.getCanciones(), 
                        cancionClickeada
                );
                
                // 2. Le damos play y la resaltamos
                ventana.reproducirDesdeBiblioteca(cancionClickeada);
                ventana.resaltarFilaEnTabla(cancionClickeada);
            }
        }
    }//GEN-LAST:event_tblPlaylistMouseClicked

    private void itemExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExportarActionPerformed
        if (playlistSeleccionadaActual == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Primero selecciona una playlist para exportar.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        javax.swing.JFileChooser explorador = new javax.swing.JFileChooser();
        explorador.setDialogTitle("Exportar Playlist Encriptada");
        int seleccion = explorador.showSaveDialog(this);

        if (seleccion == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File archivo = explorador.getSelectedFile();
            // Le forzamos la extensión .txt si el usuario no la puso
            if (!archivo.getName().toLowerCase().endsWith(".txt")) {
                archivo = new java.io.File(archivo.getParentFile(), archivo.getName() + ".txt");
            }

            try (java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter(archivo))) {

                // 1. Guardamos el Nombre de la Playlist (Encriptado)
                pw.println(encriptarTexto(playlistSeleccionadaActual.getNombre()));

                // 2. Guardamos el TÍTULO de cada canción (Encriptado)
                for (Cancion c : playlistSeleccionadaActual.getCanciones()) {
                    pw.println(encriptarTexto(c.getTitulo()));
                }

                javax.swing.JOptionPane.showMessageDialog(this, "¡Playlist exportada y encriptada con éxito!\nRevisa el archivo TXT, es ilegible sin desencriptar.");

            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al exportar: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_itemExportarActionPerformed

    private void btnImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarActionPerformed
        javax.swing.JFileChooser explorador = new javax.swing.JFileChooser();
        explorador.setDialogTitle("Importar Playlist desde TXT");
        int seleccion = explorador.showOpenDialog(this);
        
        if (seleccion == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File archivo = explorador.getSelectedFile();
            VentanaPrincipal ventana = (VentanaPrincipal) javax.swing.SwingUtilities.getWindowAncestor(this);
            
            try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(archivo))) {
                
                // 1. Leemos y desencriptamos el nombre de la playlist (Primera línea)
                String nombreEncriptado = br.readLine();
                if (nombreEncriptado == null) return;
                
                String nombreOriginal = desencriptarTexto(nombreEncriptado);
                Playlist nuevaPlaylist = new Playlist(nombreOriginal + " (Recuperada)");
                
                // 2. Leemos los títulos línea por línea, los desencriptamos y los buscamos
                String lineaTitulo;
                int cancionesAgregadas = 0;
                
                while ((lineaTitulo = br.readLine()) != null) {
                    String tituloBuscado = desencriptarTexto(lineaTitulo);
                    
                    // Usamos la búsqueda visual de tu Tabla Hash por título
                    java.util.List<Cancion> resultados = ventana.gestor.tablaHash.buscar(tituloBuscado);
                    
                    // Si encuentra coincidencias, agregamos la primera a la playlist
                    if (!resultados.isEmpty()) {
                        nuevaPlaylist.agregarCancion(resultados.get(0));
                        cancionesAgregadas++;
                    }
                }
                
                // 3. Guardamos la playlist en el gestor y actualizamos la pantalla
                ventana.gestor.crearPlaylist(nuevaPlaylist);
                actualizarListaPlaylists(); 
                
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "¡Playlist desencriptada y cargada al sistema!\n\n" +
                    "Nombre: " + nuevaPlaylist.getNombre() + "\n" +
                    "Canciones recuperadas: " + cancionesAgregadas, 
                    "Importación Exitosa", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al importar. El archivo podría estar corrupto: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnImportarActionPerformed

    private void itemRenombrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRenombrarActionPerformed
                                             
        // 1. Verificamos que de verdad haya una playlist seleccionada en pantalla
        if (playlistSeleccionadaActual == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Primero selecciona la playlist que quieres renombrar.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Mostramos la ventanita pidiendo el nuevo nombre (y le ponemos el actual por defecto para que sea más fácil editar)
        String nombreActual = playlistSeleccionadaActual.getNombre();
        String nuevoNombre = javax.swing.JOptionPane.showInputDialog(this, "Ingresa el nuevo nombre para tu playlist:", nombreActual);
        
        // 3. Validamos que el usuario no haya dado "Cancelar" o lo dejara en blanco
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            
            // Le cambiamos el nombre internamente al objeto
            playlistSeleccionadaActual.setNombre(nuevoNombre.trim());
            
            // Actualizamos el título grande de la pantalla
            lblNombPlay.setText(playlistSeleccionadaActual.getNombre());
            
            // Refrescamos los botones de la barra izquierda para que ya salga con el nuevo nombre
            actualizarListaPlaylists(); 
            
            javax.swing.JOptionPane.showMessageDialog(this, "¡Nombre actualizado al cien!", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_itemRenombrarActionPerformed

    // --- GETTER PARA QUE VENTANA PRINCIPAL PUEDA VER ESTA TABLA ---
    public javax.swing.JTable getTablaPlaylist() {
        return tblPlaylist;
    }
    
    // --- ALGORITMO PROPIO DE ENCRIPTACIÓN / DESENCRIPTACIÓN (Cifrado César +3) ---
    private String encriptarTexto(String texto) {
        StringBuilder encriptado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            encriptado.append((char) (c + 3)); // Suma 3 posiciones a la letra
        }
        return encriptado.toString();
    }

    private String desencriptarTexto(String textoEncriptado) {
        StringBuilder desencriptado = new StringBuilder();
        for (char c : textoEncriptado.toCharArray()) {
            desencriptado.append((char) (c - 3)); // Resta 3 posiciones para recuperar la letra original
        }
        return desencriptado.toString();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImportar;
    private javax.swing.JButton btnMas;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnPlayLista;
    private javax.swing.JMenuItem itemEliminar;
    private javax.swing.JMenuItem itemEncriptar;
    private javax.swing.JMenuItem itemExportar;
    private javax.swing.JMenuItem itemRenombrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCantidadTime;
    private javax.swing.JLabel lblMisPlaylist;
    private javax.swing.JLabel lblNombPlay;
    private javax.swing.JLabel lblPortada;
    private javax.swing.JPopupMenu menuOpciones;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlCabeceraPlay;
    private javax.swing.JPanel pnlDerecho;
    private javax.swing.JPanel pnlIzquierdo;
    private javax.swing.JPanel pnlMisPlaylis;
    private javax.swing.JTable tblPlaylist;
    // End of variables declaration//GEN-END:variables
}
