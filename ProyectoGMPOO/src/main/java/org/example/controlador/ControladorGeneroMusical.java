package org.example.controlador;

import org.example.modelo.GeneroMusical;
import org.example.modelo.ModeloTablaGeneroMusical;
import org.example.vista.VentanaGeneroMusical;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;

public class ControladorGeneroMusical extends MouseAdapter {
    private VentanaGeneroMusical view;
    private ModeloTablaGeneroMusical modelo;

    public ControladorGeneroMusical(VentanaGeneroMusical view) {
        this.view = view;
        modelo = new ModeloTablaGeneroMusical();
        this.view.getTblGeneroMusical().setModel(modelo);
        this.view.getBtnCargar().addMouseListener(this);
        this.view.getBtnAgregar().addMouseListener(this);
        this.view.getTblGeneroMusical().addMouseListener(this);
        this.view.getBtnElminar().addMouseListener(this);
        this.view.getBtnActualizar().addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.view.getBtnCargar()) {
            modelo.cargarDatos();
            this.view.getTblGeneroMusical().setModel(modelo);
            this.view.getTblGeneroMusical().updateUI();
        }

        if (e.getSource() == this.view.getBtnElminar()) {
            System.out.println("Eliminar dato");

        }

        if (e.getSource() == this.view.getBtnActualizar()) {
            System.out.println("Actualizar datos");

        }

        if (e.getSource() == this.view.getBtnAgregar()) {
            System.out.println("evento sobre boton agregar");
            GeneroMusical generoMusical = new GeneroMusical();
            generoMusical.setId(0);
            generoMusical.setGenero(this.view.getTxtGenero().getText());
            generoMusical.setArtista(this.view.getTxtArtista().getText());
            generoMusical.setAlbum(this.view.getLblAlbum().getText());
            generoMusical.setPais(this.view.getTxtPais().getText());
            generoMusical.setUrl(this.view.getTxtUrl().getText());
            if (modelo.agregarGenero(generoMusical)) {
                JOptionPane.showMessageDialog(view, "Se agrego correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                this.view.getTblGeneroMusical().updateUI();
            } else {
                JOptionPane.showMessageDialog(view,
                        "No se pudo agregar a la base de datos. por favor revise su conexion",
                        "Error al insertar", JOptionPane.ERROR_MESSAGE);
            }
            this.view.limpiar();
        }
        if (e.getSource() == view.getTblGeneroMusical()) {
            System.out.println("Evento sobre tabla");
            int index = this.view.getTblGeneroMusical().getSelectedRow();
            GeneroMusical tmp = modelo.getGeneroAtIndex(index);
            try {
                this.view.getImaGenMus().setIcon(tmp.getImagen());

            } catch (MalformedURLException mfue) {
                System.out.println(e.toString());
            }

        }
    }

}




